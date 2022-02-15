package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

/**
 * 
 * @author hondayuki
 *
 */
@Repository
public class ArticleRepository {
	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = new BeanPropertyRowMapper<>(Article.class);
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public List<Article> findAll() {
		String sql = "select * from articles order by id desc";
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		
		return articleList;
	}
	
	public void insert(Article article) {
		String sql = "insert into articles"
				+ "       ( name,  content)"
				+ " values(:name, :content)";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("name", article.getName()).addValue("content", article.getContent());
		
		template.update(sql, param);
	}
}
