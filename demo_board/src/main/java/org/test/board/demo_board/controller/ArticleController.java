package org.test.board.demo_board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.test.board.demo_board.domain.constant.FormStatus;
import org.test.board.demo_board.dto.request.ArticleRequest;
import org.test.board.demo_board.dto.response.ArticleResponse;

@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleController {

    @GetMapping
    public String articles(){
        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId) {
        return "articles/detail";
    }

    @GetMapping("/search-hashtag")
    public String searchArticleHashtag(){
        return "articles/search-hashtag";
    }

    @GetMapping("/form")
    public String articleForm() {
        return "articles/form";
    }

    @PostMapping("/form")
    public String postNewArticle() {
        return "redirect:/articles";
    }

    @GetMapping("/{articleId}/form")
    public String updateArticleForm(@PathVariable Long articleId) {
        return "articles/form";
    }

    @PostMapping("/{articleId}/form")
    public String updateArticle(
            @PathVariable Long articleId
    ) {
        return "redirect:/articles/" + articleId;
    }

    @PostMapping("/{articleId}/delete")
    public String deleteArticle(
            @PathVariable Long articleId
    ) {
        return "redirect:/articles";
    }
}
