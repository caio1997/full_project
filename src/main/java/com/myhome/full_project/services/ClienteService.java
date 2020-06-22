package com.myhome.full_project.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.myhome.full_project.dto.ClienteDTO;
import com.myhome.full_project.dto.ClienteNewDTO;
import com.myhome.full_project.entities.Cidade;
import com.myhome.full_project.entities.Cliente;
import com.myhome.full_project.entities.Endereco;
import com.myhome.full_project.entities.enums.TipoCliente;
import com.myhome.full_project.repositories.CidadeRepository;
import com.myhome.full_project.repositories.ClienteRepository;
import com.myhome.full_project.repositories.EnderecoRepository;
import com.myhome.full_project.services.exceptions.DataIntegrityException;
import com.myhome.full_project.services.exceptions.IdIsNotfound;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Optional<Cliente> findById(Long id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return Optional.ofNullable(obj.orElseThrow(() -> new IdIsNotfound("Id não existente"))); 
	}
	
	@Transient
	public Cliente insert(Cliente obj) {
		
		obj = clienteRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
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
	
	public Cliente fromDto(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCfpOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = cidadeRepository.getOne(objDto.getCidadeId());
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
}
