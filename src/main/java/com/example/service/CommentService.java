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
	/**
	 * 
	 * @param articleId 記事ID
	 * @return コメントリスト
	 */
	public List<Comment> findByArticleId(int articleId) {
		return commentRepository.findByArticleId(articleId);
	}
	/**
	 * 
	 * @param comment コメント情報
	 */
	public void insert(Comment comment) {
		commentRepository.insert(comment);
	}
	/**
	 * 
	 * @param articleId 記事ID
	 */
	public void deleteByArticleId(int articleId) {
		commentRepository.deleteByArticleId(articleId);
	}
}
