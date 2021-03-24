package com.jdo.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdo.poc.model.Consulta;
import com.jdo.poc.service.ConsutaMedicaService;
import com.jdo.poc.uti.DateUtil;
import com.jdo.poc.view.ResponseError;

@RestController
@RequestMapping("/api/consultas")
@CrossOrigin
public class ConsultaMedicaController {
	
	@Autowired
	private ConsutaMedicaService consutaMedicaService;
	
	@PostMapping()
	private ResponseEntity<?> incluir(@RequestBody Consulta consulta) {
		System.out.println("IN incluir");
		try {
			consulta.setDataHora(DateUtil.addHour(consulta.getDataHora(), 3)); //Solve time zone
			System.out.println("consulta:" + consulta);
			consutaMedicaService.incluir(consulta);
			return ResponseEntity.ok(null);
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseError(e.getMessage()));
		}
	}
	
}
