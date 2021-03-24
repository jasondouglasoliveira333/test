package com.jdo.poc.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdo.poc.exception.ConsultaException;
import com.jdo.poc.model.Consulta;
import com.jdo.poc.model.Medico;
import com.jdo.poc.repository.ConsultaRepository;
import com.jdo.poc.repository.MedicoRepository;
import com.jdo.poc.uti.DateUtil;

@Service
public class ConsutaMedicaService {

	private static final long _12_HORAS = 12 * 60 * 60 * 1000;

	private static final String CIRURGIAO = "Cirurgiao";
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	public void incluir(Consulta consulta) throws ConsultaException {
		Date dataFim = DateUtil.add(consulta.getDataHora(), 15);
		consulta.setDataHoraFim(dataFim);

		Date dataInicioDia = DateUtil.getInicioDia(consulta.getDataHora());
		Date dataFimDia = DateUtil.getFimDia(consulta.getDataHora());
		List<Consulta> consultas = consultaRepository.findByDataHoraBetween(dataInicioDia, dataFimDia);

		//Consulta nesse horario
		List<Consulta> consultasNoHorario = consultas.stream()
				.filter(c ->{
			if (((c.getDataHora().getTime() >= consulta.getDataHora().getTime() 
						&& c.getDataHora().getTime() <= consulta.getDataHoraFim().getTime()) 
				|| (c.getDataHoraFim().getTime() >= consulta.getDataHora().getTime() 
							&& c.getDataHoraFim().getTime() <= consulta.getDataHoraFim().getTime()))
				 && c.getConsultorio().getId().equals(consulta.getConsultorio().getId())) {
					System.out.println(c.getConsultorio().getId() + " - " + consulta.getConsultorio().getId() + " - c.getConsultorio().getId().equals(consulta.getConsultorio().getId()):" + c.getConsultorio().getId().equals(consulta.getConsultorio().getId()));
					return true;
			}else {
				return false;
			}
		}).collect(Collectors.toList());
		if (consultasNoHorario.size() > 0) {
			throw new ConsultaException("Já tem consulta nesse horario");
		}

		//Paciente ja tem consulta nesse dia
		List<Consulta> consultasPaciente = consultas.stream().
				filter(c -> c.getNomePaciente().equals(consulta.getNomePaciente())).collect(Collectors.toList());
			
		if (consultasPaciente.size() > 0) {
			throw new ConsultaException("O paciente já tem consulta nesse dia");
		}
		
		//Periodo maximo de 12 horas no consultorio
		List<Consulta> consultasOrdenadas = consultas.stream()
				.filter(c -> c.getConsultorio().getId().equals(consulta.getConsultorio().getId()))
				.sorted((c1,c2) -> (int)(c1.getDataHora().getTime() - c2.getDataHora().getTime())).collect(Collectors.toList());
		if (consultasOrdenadas.size() > 0) {
			Consulta primeiraConsulta = consultasOrdenadas.get(0);
			Date ultimaHorarioConsulta = DateUtil.addHour(primeiraConsulta.getDataHora(), 12); 
			if (consulta.getDataHoraFim().getTime() > ultimaHorarioConsulta.getTime()) {
				throw new ConsultaException("Não tem horario nesse dia para esse consultorio");
			}
		}

		//Doutor ja esta atendendo em outro consultorio
		List<Consulta> consultaComMedicoOutroConsultorio = consultas.stream()
				.filter((c) -> c.getMedico().getNome().equals(consulta.getMedico().getNome()) &&
						!c.getConsultorio().getId().equals(consulta.getConsultorio().getId())).collect(Collectors.toList());
		if (consultaComMedicoOutroConsultorio.size() > 0) {
			throw new ConsultaException("Médico já esta atendendo em outro consultorio");
		}
		
		//Nao pode ter 2 medicos no mesmo consultario exceto cirurgiao
		if (!consulta.getEspecialidadeMedica().equals(CIRURGIAO)) {
			List<Consulta> consultasConsultorio = consultas.stream().
					filter(c -> c.getConsultorio().getId().equals(consulta.getConsultorio().getId())).collect(Collectors.toList());
			if (consultasConsultorio.size() > 0) {
				Medico medicoConsultorio = consultasConsultorio.get(0).getMedico();
				if (!medicoConsultorio.getNome().equals(consulta.getMedico().getNome())){
					throw new ConsultaException("Já tem medico nesse consultorio");
				}
			}
		}
		
		Medico medico = medicoRepository.findByNome(consulta.getMedico().getNome());
		consulta.setMedico(medico);
		consultaRepository.save(consulta);
	}

}
