package com.jdo.poc.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Medico {
	
	@Id
	private Integer id;
	private String nome;
	private String crm;
	private Integer idade;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	@Override
	public String toString() {
		return "Medico [id=" + id + ", nome=" + nome + ", crm=" + crm + ", idade=" + idade + "]";
	}
	
	
}
