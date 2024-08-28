package com.ens.pedidosAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ens.pedidosAPI.entities.Pedido;


import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long>{

    boolean existsByNumeroControle(Long numeroControle);

    @Query("SELECT p FROM Pedido p WHERE (:numeroControle IS NULL OR p.numeroControle = :numeroControle) " +
           "AND (:dataCadastro IS NULL OR p.dataCadastro = :dataCadastro)")
    List<Pedido> findPedidos(@Param("numeroControle") Long numeroControle, @Param("dataCadastro") Date dataCadastro);

}
