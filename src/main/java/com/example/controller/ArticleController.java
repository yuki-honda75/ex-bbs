package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.service.ArticleService;
import com.example.service.CommentService;

/**
 * 
 * @author hondayuki
 *
 */
@Controller
@RequestMapping("/bbs")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CommentService commentService;
	
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}
	/**
	 * 
	 * @param model 投稿情報
	 * @return トップ画面
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleService.findAll();
		for (Article article : articleList) {
			List<Comment> commentList = commentService.findByArticleId(article.getId());
			article.setCommentList(commentList);
		}
		model.addAttribute("articleList", articleList);
		
		return "bbs";
	}
	/**
	 * 
	 * @param form 投稿するパラメータ
	 * @param model
	 * @return トップ画面へリダイレクト
	 */
	@RequestMapping("/postArticle")
	public String insertArticle(ArticleForm form) {
		Article article = new Article();
		/*test
		form.setName("user1");
		form.setContent("ahhhh");
		article.setName("user");
		article.setContent("hello World");
		*/
		article.setName(form.getName());
		article.setContent(form.getContent());
		articleService.insert(article);
		
		return "redirect:/bbs";
	}
	
	@RequestMapping("/postComment")
	public String insertComment() {
		return "redirect:/bbs";
	}
}
