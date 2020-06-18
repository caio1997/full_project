package com.myhome.full_project.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhome.full_project.entities.Categoria;
import com.myhome.full_project.repositories.CategoriaRepository;
import com.myhome.full_project.services.exceptions.IdIsNotfound;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public Optional<Categoria> findById(Long id) {
		 Optional<Categoria> obj = categoriaRepository.findById(id);
		 return Optional.ofNullable(obj.orElseThrow(() -> new IdIsNotfound("Id não existente"))); 
		}
	
	
}
