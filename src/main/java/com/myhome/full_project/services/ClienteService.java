package com.myhome.full_project.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhome.full_project.entities.Cliente;
import com.myhome.full_project.repositories.ClienteRepository;
import com.myhome.full_project.services.exceptions.IdIsNotfound;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Optional<Cliente> findById(Long id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return Optional.ofNullable(obj.orElseThrow(() -> new IdIsNotfound("Id não existente"))); 
	}

}
