package com.myhome.full_project.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhome.full_project.entities.Pedido;
import com.myhome.full_project.repositories.PedidoRepository;
import com.myhome.full_project.services.exceptions.IdIsNotfound;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	public Optional<Pedido> findById(Long id) {
		 Optional<Pedido> obj = pedidoRepository.findById(id);
		 return Optional.ofNullable(obj.orElseThrow(() -> new IdIsNotfound("Id não existente"))); 
		}
	
	
}
