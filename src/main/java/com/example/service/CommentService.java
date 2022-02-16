package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Comment;
import com.example.repository.CommentRepository;

/**
 * 
 * @author hondayuki
 *
 */
@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	
	public List<Comment> findByArticleId(int articleId) {
		return commentRepository.findByArticleId(articleId);
	}
}
