package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.Article;

/**
 * 
 * @author hondayuki
 *
 */
@Service
public class ArticleService {
	public List<Article> findAll() {
		return new ArrayList<>();
	}
}
