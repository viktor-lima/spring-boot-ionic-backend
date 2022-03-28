package com.vkl.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vkl.cursomc.domain.Cidade;
import com.vkl.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;
	
	public List<Cidade> findByEstados(Integer estadoId){
		return repo.findCidades(estadoId);
	}
	
}
