package org.test.board.demo_board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.test.board.demo_board.dto.request.ArticleCommentRequest;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class ArticleCommentController {
    @PostMapping("/new")
    public String postNewArticleComment(
            ArticleCommentRequest articleCommentRequest
    ) {
        return "redirect:/articles/" + articleCommentRequest.articleId();
    }

    @PostMapping("/{commentId}/delete")
    public String deleteArticleComment(
            @PathVariable Long commentId,
            Long articleId
    ) {
        return "redirect:/articles/" + articleId;
    }
}
