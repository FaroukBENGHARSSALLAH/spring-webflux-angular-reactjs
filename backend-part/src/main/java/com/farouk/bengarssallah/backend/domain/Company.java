package com.farouk.bengarssallah.backend.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Company extends AEntity {

	@Id
	private String symbol;

	private String name;
	
	private String exchange;
	
	private double volume;
	
	private double day1Flow;
	
	public Company() {}
	
	public Company(String symbol, String name, String exchange, double volume, double day1Flow, LocalDateTime creationDate) {
		this.symbol = symbol;
		this.name = name;
		this.exchange = exchange;
		this.day1Flow = day1Flow;
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

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getDay1Flow() {
		return day1Flow;
	}

	public void setDay1Flow(double day1Flow) {
		this.day1Flow = day1Flow;
	}
	
	

}
