package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * 
 * @author hondayuki
 *
 */
@Repository
public class CommentRepository {
	private static final RowMapper<Comment> COMMENT_ROW_MAPPER = new BeanPropertyRowMapper<>(Comment.class);
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	/**
	 * 
	 * @param articleId 記事ID
	 * @return 記事IDで検索したコメントリスト
	 */
	public List<Comment> findByArticleId(int articleId) {
		String sql = "select * from comments where article_id=:articleId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		List<Comment> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);
		
		return commentList;
	}
	/**
	 * 
	 * @param comment コメント情報
	 */
	public void insert(Comment comment) {
		String sql = "insert into comments"
				+ "       ( name,  content, article_id)"
				+ " values(:name, :content, :articleId)";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("name", comment.getName())
				.addValue("content", comment.getContent())
				.addValue("articleId", comment.getArticleId());
		
		template.update(sql, param);
	}
	/**
	 * 
	 * @param articleId 記事ID
	 */
	public void deleteByArticleId(int articleId) {
		String sql = "delete from comments where article_id=:articleId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		
		template.update(sql, param);
	}
}
