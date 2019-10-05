package com.devskiller.tasks.blog.service;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.farouk.bengarssallah.backend.domain.Transaction;
import com.farouk.bengarssallah.backend.repository.PostRepository;
import com.farouk.bengarssallah.backend.service.CompanyService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

	@Autowired
	PostRepository postRepository;

	@Autowired
	CompanyService commentService;

	@Test
	public void shouldAddComment() {
		Transaction post = createTestPost();

		NewCommentDto comment = new NewCommentDto();
		comment.setPostId(post.getId());
		comment.setAuthor("Author");
		comment.setContent("Content");
		String commentId = commentService.addComment(comment);

		assertThat("Comment id shouldn't be null", commentId, notNullValue());
	}

	private Transaction createTestPost() {
		Transaction post = new Transaction();
		post.setTitle("Test title");
		post.setContent("Test content");
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		post.setCreationDate(creationDate);
		postRepository.save(post);
		return post;
	}

	@Test
	public void shouldReturnAddedComment() {
		Transaction post = createTestPost();

		NewCommentDto comment = new NewCommentDto();
		comment.setPostId(post.getId());
		comment.setAuthor("Author");
		comment.setContent("Content");

		commentService.addComment(comment);

		List<CommentDto> comments = commentService.getCommentsForPost(post.getId());

		assertThat("There should be one comment", comments, hasSize(1));
		assertThat(comments.get(0).getAuthor(), equalTo("Author"));
		assertThat(comments.get(0).getComment(), equalTo("Content"));
	}
}