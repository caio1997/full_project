package com.myhome.full_project.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.myhome.full_project.entities.Categoria;
import com.myhome.full_project.repositories.CategoriaRepository;
import com.myhome.full_project.services.exceptions.DataIntegrityException;
import com.myhome.full_project.services.exceptions.IdIsNotfound;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public Optional<Categoria> findById(Long id) {
		 Optional<Categoria> obj = categoriaRepository.findById(id);
		 return Optional.ofNullable(obj.orElseThrow(() -> new IdIsNotfound("Id não existente"))); 
		}
	
	public Categoria insert(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	
	public Categoria update(Long id, Categoria cat) {
		Categoria cat1 = categoriaRepository.getOne(id);
		cat1.setNome(cat.getNome());
		return categoriaRepository.save(cat1);
	}
	
	public void delete(Long id) {
		try {
			categoriaRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria vinculada a algum produto!");
		}
	}
	
	
}
