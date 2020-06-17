package com.myhome.full_project.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myhome.full_project.entities.Categoria;
import com.myhome.full_project.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Categoria>> findById(@PathVariable Long id){
		Optional<Categoria> cat = categoriaService.findById(id);
		return ResponseEntity.ok().body(cat);
	}

}
