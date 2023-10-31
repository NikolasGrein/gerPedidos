package com.gerenciamentoPedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciamentoPedidos.entities.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Long>{

}
