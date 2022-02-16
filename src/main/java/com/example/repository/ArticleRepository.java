package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * 
 * @author hondayuki
 *
 */
@Repository
public class ArticleRepository {
	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = new BeanPropertyRowMapper<>(Article.class);
	
	private static final ResultSetExtractor<List<Article>> ARTICLE_COMMNT_RESULT_SET_EXTRACTOR = (rs) -> {
		List<Article> articleList = new ArrayList<>();
		List<Comment> commentList = null;
		int beforeId = 0;
		
		while (rs.next()) {
			int nowId = rs.getInt("id");
			
			if (beforeId != nowId) {
				Article article = new Article();
				article.setId(nowId);
				article.setName(rs.getString("name"));
				article.setContent(rs.getString("content"));
				commentList = new ArrayList<>();
				article.setCommentList(commentList);
				articleList.add(article);
			}
			
			if (rs.getInt("com_id") > 0) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("com_id"));
				comment.setArticleId(nowId);
				comment.setName(rs.getString("com_name"));
				comment.setContent(rs.getString("com_content"));
				commentList.add(comment);
			}
			beforeId = nowId;
		}
		
		return articleList;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	/**
	 * 
	 * @return 記事一覧
	 */
	public List<Article> findAll() {
		//String sql = "select * from articles order by id desc";
		String sql = "select a.id, a.name, a.content,"
				+ " c.id as com_id, c.name as com_name, c.content as com_content, c.article_id"
				+ " from articles as a left outer join comments as c"
				+ " on a.id = c.article_id order by a.id desc";
		List<Article> articleList = template.query(sql, ARTICLE_COMMNT_RESULT_SET_EXTRACTOR);
		
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
