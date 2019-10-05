package com.farouk.bengarssallah.backend.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Company extends AEntity {

	private String symbol;

	private String name;
	
	private String volume;

}
