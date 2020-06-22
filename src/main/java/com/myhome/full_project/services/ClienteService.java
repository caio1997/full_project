package com.myhome.full_project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.myhome.full_project.dto.ClienteDTO;
import com.myhome.full_project.entities.Cliente;
import com.myhome.full_project.repositories.ClienteRepository;
import com.myhome.full_project.services.exceptions.DataIntegrityException;
import com.myhome.full_project.services.exceptions.IdIsNotfound;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Optional<Cliente> findById(Long id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return Optional.ofNullable(obj.orElseThrow(() -> new IdIsNotfound("Id não existente"))); 
	}
	
	public Cliente update(Long id, Cliente cat) {
		Cliente cli1 = clienteRepository.getOne(id);
		cli1.setNome(cat.getNome());
		cli1.setEmail(cat.getEmail());
		cli1.setTipo(cli1.getTipo());
		return clienteRepository.save(cli1);
	}
	
	public void delete(Long id) {
		try {
			clienteRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir esse cliente porque há entidades relacionadas!");
		}
	}
	
	public List<Cliente> findAll(){
		List<Cliente> list = clienteRepository.findAll();
		return list;
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDto(ClienteDTO clienteDto) {
		return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(), null, null);
	}
}
