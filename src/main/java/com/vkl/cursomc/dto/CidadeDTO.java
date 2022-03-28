package com.vkl.cursomc.dto;

import java.io.Serializable;

import com.vkl.cursomc.domain.Cidade;

public class CidadeDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	
	public CidadeDTO() {
		// TODO Auto-generated constructor stub
	}

	public CidadeDTO(Cidade obj) {
		this.id = obj.getId();
		this.name = obj.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	
}
