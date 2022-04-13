package org.unidas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unidas.model.Tema;
import org.unidas.repository.TemaRepository;

@RestController

@RequestMapping(value="/temas")
@CrossOrigin(origins="*",allowedHeaders ="*")



public class TemaController {

	@Autowired
	private TemaRepository repository;
	
	
	
	@GetMapping
	public ResponseEntity<List<Tema>> findAllTema(){
		return ResponseEntity.ok(repository.findAll());
		
		
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> findByIdTema(@PathVariable long id){
	
		return repository.findById(id).map(resp-> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
		
	}

	
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Tema>> findByDescricaoPostagem(@PathVariable String nome ){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
		
		
	}
	
	
	@PostMapping
	public ResponseEntity<Tema> postTema(@RequestBody Tema tema){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}
	
	@PutMapping
public ResponseEntity<Tema> putTema(@RequestBody Tema tema){
		return repository.findById(tema.getId())
				.map(resposta -> {
					return ResponseEntity.ok().body(repository.save(tema));
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTema(@PathVariable long id) {
		return repository.findById(id)
				.map(resposta -> {
					repository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}

}
