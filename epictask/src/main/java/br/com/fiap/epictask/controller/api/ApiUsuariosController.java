package br.com.fiap.epictask.controller.api;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.epictask.model.Usuarios;
import br.com.fiap.epictask.repository.UsuariosRepository;

@RestController
@RequestMapping("/api/task")
public class ApiUsuariosController {
	
	@Autowired
	private UsuariosRepository repository;

	@GetMapping()
	@Cacheable("usuarios")
	public Page<Usuarios> index(
			@RequestParam(required = false) String email,
			@PageableDefault(size = 20) Pageable pageable) {
		
		if (email == null) 
			return repository.findAll(pageable);
		
		return repository.findByEmailLike("%" + email + "%", pageable);
	}
	
	@PostMapping()
	@CacheEvict(value = "usuarios", allEntries = true)
	public ResponseEntity<Usuarios> create(@RequestBody @Valid Usuarios usuarios, UriComponentsBuilder uriBuilder) {
		repository.save(usuarios);
		URI uri = uriBuilder
				.path("/api/usuarios/{id}")
				.buildAndExpand(usuarios.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Usuarios> get(@PathVariable Long id) {
		return ResponseEntity.of(repository.findById(id));
	}
	
	@DeleteMapping("{id}")
	@CacheEvict(value = "usuarios", allEntries = true)
	public ResponseEntity<Usuarios> delete(@PathVariable Long id){
		Optional<Usuarios> usuarios = repository.findById(id);
		
		if(usuarios.isEmpty()) 
			return ResponseEntity.notFound().build();
		
		repository.deleteById(id);	
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("{id}")
	@CacheEvict(value = "tasks", allEntries = true)
	public ResponseEntity<Usuarios> update(@RequestBody Usuarios newUsuario, @PathVariable Long id){
		Optional<Usuarios> optional = repository.findById(id);
		
		if(optional.isEmpty()) 
			return ResponseEntity.notFound().build();
		
		Usuarios usuarios = optional.get();
		
		usuarios.setEmail(newUsuario.getEmail());
		usuarios.setPass(newUsuario.getPass());
		usuarios.setNome(newUsuario.getNome());
		
		repository.save(usuarios);
		
		return ResponseEntity.ok(usuarios);
	}
	
	
}

