package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Article;
import com.example.repository.ArticleRepository;

/**
 * 
 * @author hondayuki
 *
 */
@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	public List<Article> findAll() {
		return articleRepository.findAll();
	}
	
	public void insert(Article article) {
		articleRepository.insert(article);
	}
	
	public void deleteById(int id) {
		articleRepository.deleteById(id);
	}
}
