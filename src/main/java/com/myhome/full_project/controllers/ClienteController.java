package com.myhome.full_project.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myhome.full_project.entities.Cliente;
import com.myhome.full_project.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Cliente>> findById(@PathVariable Long id){
		Optional<Cliente> obj = clienteService.findById(id);
		return ResponseEntity.ok(obj);
	}

}
