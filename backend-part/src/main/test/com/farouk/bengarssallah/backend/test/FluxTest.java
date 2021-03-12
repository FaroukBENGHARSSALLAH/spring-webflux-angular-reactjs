package com.farouk.bengarssallah.backend.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.farouk.bengarssallah.backend.domain.Company;

//@RunWith(SpringRunner.class)
//@WebFluxTest
public class FluxTest {
	
	 /*@Autowired
	 private WebClient webClient;
	  	    
	  // GET Test-case
	  @Test
	    public void get() throws Exception {
	      
	        webClient.get().uri("/api/customer/{id}", 2).accept(MediaType.APPLICATION_JSON)
	            .exchange()
	            .expectStatus().isOk()
	            .expectBody(Company.class)
	            .isEqualTo(customerMap.get("Peter"));
	    }
	  
	  // POST Test-case
	  @Test
	  public void post throws Exception {
	    
	    webClient.post().uri("/api/customer/post")
	              .contentType(MediaType.APPLICATION_JSON)
	              .body(BodyInserters.fromObject(customerMap.get("Mary")))
	              .exchange()
	              .expectStatus().isCreated()
	              .expectBody(String.class)
	              .isEqualTo("Post Successfully!");
	  }
	  
	  // PUT Test-case
	  @Test
	  public void put() throws Exception {
	    
	    webClient.put().uri("/api/customer/put/{id}", 3)
	              .contentType(MediaType.APPLICATION_JSON)
	              .body(BodyInserters.fromObject(customerMap.get("Amy")))
	              .exchange()
	              .expectStatus().isCreated()
	              .expectBody(Company.class)
	              .isEqualTo(customerMap.get("Amy"));
	    
	  }
	  
	  // DELETE Test-case
	  @Test
	  public void delete() throws Exception {
	    
	    webClient.delete().uri("/api/customer/delete/{id}", 1)
	              .exchange()
	                  .expectStatus().isAccepted()
	                  .expectBody(String.class)
	                  .isEqualTo("Delete Succesfully!");
	    }
*/

}
