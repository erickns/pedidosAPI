package com.ens.pedidosAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ens.pedidosAPI.entities.Cliente;
import com.ens.pedidosAPI.entities.Pedido;
import com.ens.pedidosAPI.repositories.ClienteRepository;
import com.ens.pedidosAPI.repositories.PedidoRepository;
import com.ens.pedidosAPI.requests.PedidoRequest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public void processarPedidos(List<PedidoRequest> listaPedidos) throws Exception {

        List<Pedido> pedidos = new ArrayList();

        for(PedidoRequest item : listaPedidos) {
            Cliente cliente = clienteRepository.findById(item.getCodigoCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

            Pedido pedido = new Pedido();
            pedido.setNumeroControle(item.getNumeroControle());
            pedido.setDataCadastro(item.getDataCadastro());
            pedido.setNome(item.getNome());
            pedido.setValor(item.getValor());
            pedido.setQuantidade(item.getQuantidade());
            pedido.setValorTotal(item.getValorTotal());
            pedido.setCliente(cliente);

            pedidos.add(pedido);
        }

        

        if (pedidos.size() > 10) {
            throw new Exception("Número máximo de pedidos excedido.");
        }

        for (Pedido pedido : pedidos) {
            if (pedidoRepository.existsByNumeroControle(pedido.getNumeroControle())) {
                throw new Exception("Número de controle já cadastrado: " + pedido.getNumeroControle());
            }

            if (pedido.getDataCadastro() == null) {
                pedido.setDataCadastro(new Date());
            }

            if (pedido.getQuantidade() == 0) {
                pedido.setQuantidade(1);
            }

            BigDecimal valorTotal = pedido.getValor().multiply(BigDecimal.valueOf(pedido.getQuantidade()));
            if (pedido.getQuantidade() > 10) {
                valorTotal = valorTotal.multiply(BigDecimal.valueOf(0.9));
            } else if (pedido.getQuantidade() > 5) {
                valorTotal = valorTotal.multiply(BigDecimal.valueOf(0.95));
            }

            pedido.setValorTotal(valorTotal);
            pedidoRepository.save(pedido);
        }
    }

    public List<Pedido> buscarPedidos(Long numeroControle, Date dataCadastro) {
        return pedidoRepository.findPedidos(numeroControle, dataCadastro);
    }
}
