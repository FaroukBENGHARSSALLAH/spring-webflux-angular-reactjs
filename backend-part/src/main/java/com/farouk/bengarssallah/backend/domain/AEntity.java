package com.farouk.bengarssallah.backend.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AEntity implements Serializable {

	
	protected LocalDateTime creationDate;

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}


	

}
