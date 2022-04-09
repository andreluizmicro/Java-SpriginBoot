package com.algaworks.algafood.domain.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;
import lombok.EqualsAndHashCode;

//@JsonRootName("cozinha") Muda o retorno para serialização de xml
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "kitchen")
public class Kitchen {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//@JsonIgnore //Não retorna mais na serialização
	//@JsonProperty("titulo") // Muda a representação quando devolve na serialização
	@Column(nullable = false)
	private String name;
}
