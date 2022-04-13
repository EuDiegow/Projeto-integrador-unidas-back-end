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
import org.unidas.model.Postagem;
import org.unidas.repository.PostagemRepository;
import org.unidas.repository.TemaRepository;

@RestController

@RequestMapping(value = "/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class PostagemController {

	@Autowired
	private PostagemRepository repository;

	@Autowired
	private TemaRepository temaRepository;

	@GetMapping
	public ResponseEntity<List<Postagem>> findAllPostagem() {
		return ResponseEntity.ok(repository.findAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Postagem> findByIdPostagem(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());

	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> findByDescricaoTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));

	}

	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@RequestBody Postagem postagem) {

		if (temaRepository.existsById(postagem.getTema().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@PutMapping
	public ResponseEntity<Postagem> putPostagem(@RequestBody Postagem postagem) {

		if (repository.existsById(postagem.getId())) {

			if (temaRepository.existsById(postagem.getTema().getId()))
				return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/{id}")
	public  ResponseEntity<?>  deletePostagem(@PathVariable long id) {
		
		return repository.findById(id)
				.map(resposta -> {
					repository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}

}
