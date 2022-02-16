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
	/**
	 * 
	 * @return 記事一覧
	 */
	public List<Article> findAll() {
		String sql = "select * from articles order by id desc";
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		
		return articleList;
	}
	/**
	 * 
	 * @param article 記事情報
	 */
	public void insert(Article article) {
		String sql = "insert into articles"
				+ "       ( name,  content)"
				+ " values(:name, :content)";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("name", article.getName()).addValue("content", article.getContent());
		
		template.update(sql, param);
	}
	/**
	 * 
	 * @param id 記事ID
	 */
	public void deleteById(int id) {
		String sql = "delete from articles where id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		template.update(sql, param);
	}
}
