package br.com.fiap.epictask.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Usuarios {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O e-mail é obrigatório. Digite um e-mail válido.")
	@Email(message = "O e-mail é obrigatório. Digite um e-mail válido.")
	private String email;
	
	@Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
	@NotBlank(message = "A senha é obrigatória. Digite uma senha.")
	private String pass;
	
	@NotBlank(message = "O nome é obrigatório. Digite um nome.")
	private String nome;
	

}
