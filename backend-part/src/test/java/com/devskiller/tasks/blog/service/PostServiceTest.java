package com.devskiller.tasks.blog.service;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.devskiller.tasks.blog.model.dto.PostDto;
import com.farouk.bengarssallah.backend.domain.Transaction;
import com.farouk.bengarssallah.backend.repository.PostRepository;
import com.farouk.bengarssallah.backend.service.PostService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

	@Autowired
	PostRepository postRepository;

	@Autowired
	PostService postService;

	@Test
	public void shouldReturnCreatedPost() {
		Transaction post = new Transaction();
		post.setTitle("Test title");
		post.setContent("Test content");
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		post.setCreationDate(creationDate);
		postRepository.save(post);

		PostDto postDto = postService.getPost(post.getId());

		assertNotNull("Post shouldn't be null", postDto);
		assertThat(postDto.getContent(), equalTo("Test content"));
		assertThat(postDto.getTitle(), equalTo("Test title"));
		assertThat(postDto.getCreationDate(), equalTo(creationDate));
	}

	@Test
	public void shouldReturnNullForNotExistingPost() {
		PostDto postDto = postService.getPost("123");

		assertNull(postDto);
	}
}