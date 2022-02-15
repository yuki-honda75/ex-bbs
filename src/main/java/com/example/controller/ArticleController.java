package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.service.ArticleService;

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
	
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}
	
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleService.findAll();
		model.addAttribute("articleList", articleList);
		
		return "bbs";
	}
	
	@RequestMapping("/post")
	public String post(ArticleForm form, Model model) {
		Article article = new Article();
		/*test
		form.setName("user1");
		form.setContent("ahhhh");
		Article article = new Article();
		article.setName("user");
		article.setContent("hello World");
		*/
		article.setName(form.getName());
		article.setContent(form.getContent());
		articleService.insert(article);
		
		return index(model);
	}
}
