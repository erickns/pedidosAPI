package com.ens.pedidosAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ens.pedidosAPI.entities.Pedido;
import com.ens.pedidosAPI.requests.PedidoRequest;
import com.ens.pedidosAPI.services.PedidoService;

import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> criarPedidos(@RequestBody List<PedidoRequest> pedidoRequest) {
        try {
            pedidoService.processarPedidos(pedidoRequest);
            return ResponseEntity.ok("Pedidos processados com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> consultarPedidos(
            @RequestParam(required = false) Long numeroControle,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataCadastro) {
        List<Pedido> pedidos = pedidoService.buscarPedidos(numeroControle, dataCadastro);
        return ResponseEntity.ok(pedidos);
    }
}
