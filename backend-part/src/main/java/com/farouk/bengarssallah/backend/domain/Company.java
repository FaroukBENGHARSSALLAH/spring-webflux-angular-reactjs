package com.farouk.bengarssallah.backend.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Company extends AEntity {

	@Id
	private String symbol;

	private String name;
	
	private double volume;
	
	public Company() {}
	
	public Company(String symbol, String name, double volume, LocalDateTime creationDate) {
		this.symbol = symbol;
		this.name = name;
		this.volume = volume;
		this.creationDate = creationDate;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	

}
