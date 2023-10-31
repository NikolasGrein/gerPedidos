package com.gerenciamentoPedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciamentoPedidos.entities.Pedidos;
import com.gerenciamentoPedidos.repository.PedidosRepository;

@Service
public class PedidosService {

    private final PedidosRepository pedidosRepository;

    @Autowired
    public PedidosService(PedidosRepository pedidosRepository) {
        this.pedidosRepository = pedidosRepository;
    }

    public List<Pedidos> buscaTodosPedidos() {
        return pedidosRepository.findAll();
    }

    public Pedidos buscaPedidosId(Long id) {
        Optional<Pedidos> pedidos = pedidosRepository.findById(id);
        return pedidos.orElse(null);
    }

    public Pedidos salvaPedidos(Pedidos pedidos) {
        return pedidosRepository.save(pedidos);
    }

    public Pedidos alterarPedidos(Long id, Pedidos alterarP) {
        Optional<Pedidos> existePedidos = pedidosRepository.findById(id);
        if (existePedidos.isPresent()) {
            alterarP.setId(id);
            return pedidosRepository.save(alterarP);
        }
        return null;
    }

    public boolean apagarPedidos(Long id) {
        Optional<Pedidos> existePedidos = pedidosRepository.findById(id);
        if (existePedidos.isPresent()) {
            pedidosRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
