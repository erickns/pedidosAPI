package com.ens.pedidosAPI.requests;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class PedidoRequest {
    private Long numeroControle;
    private Date dataCadastro;
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    private BigDecimal valorTotal;
    private Long codigoCliente;
}

