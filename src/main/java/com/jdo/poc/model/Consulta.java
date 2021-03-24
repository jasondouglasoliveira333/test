package com.jdo.poc.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Consulta {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String nomePaciente;
	private String especialidadeMedica;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dataHora;
	private Date dataHoraFim;

	@ManyToOne
	private Medico medico;
	@ManyToOne
	private Consultorio consultorio;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public String getEspecialidadeMedica() {
		return especialidadeMedica;
	}
	public void setEspecialidadeMedica(String especialidadeMedica) {
		this.especialidadeMedica = especialidadeMedica;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Date getDataHora() {
		return dataHora;
	}
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	public Date getDataHoraFim() {
		return dataHoraFim;
	}
	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}
	public Consultorio getConsultorio() {
		return consultorio;
	}
	public void setConsultorio(Consultorio consultorio) {
		this.consultorio = consultorio;
	}
	@Override
	public String toString() {
		return "Consulta [id=" + id + ", nomePaciente=" + nomePaciente + ", especialidadeMedica=" + especialidadeMedica
				+ ", dataHora=" + dataHora + ", medico=" + medico + ", consultorio=" + consultorio + "]";
	}
	
	
}
