package com.gerenciamentoPedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciamentoPedidos.entities.Pedidos;
import com.gerenciamentoPedidos.service.PedidosService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {
	
	private final PedidosService pedidosService;
	
	@Autowired
    public PedidosController(PedidosService pedidosService) {
        this.pedidosService = pedidosService;
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Pedidos> buscaPedidosControlId(@PathVariable Long id) {
		Pedidos pedidos = pedidosService.buscaPedidosId(id); 
        if (pedidos != null) {
            return ResponseEntity.ok(pedidos);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
        
    @GetMapping("/")
    public ResponseEntity<List<Pedidos>> buscaTodosPedidosControl() { 
        List<Pedidos> pedidos = pedidosService.buscaTodosPedidos();
        return ResponseEntity.ok(pedidos);
    }
        
    @PostMapping("/")
    public ResponseEntity<Pedidos> salvaPedidosControl(@RequestBody Pedidos pedidos) { 
        Pedidos salvaPedidos = pedidosService.salvaPedidos(pedidos);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvaPedidos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedidos> alteraPedidosControl(@PathVariable Long id, @RequestBody Pedidos pedidos) { 
        Pedidos alteraPedidos = pedidosService.alterarPedidos(id, pedidos);
        if (alteraPedidos != null) {
            return ResponseEntity.ok(pedidos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> apagaPedidosControl(@PathVariable Long id) { 
        boolean apagar = pedidosService.apagarPedidos(id);
        if (apagar) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

