package com.ens.pedidosAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ens.pedidosAPI.entities.Cliente;
import com.ens.pedidosAPI.entities.Pedido;

public interface ClienteRepository extends  JpaRepository<Cliente,Long>{

}
