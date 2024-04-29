package org.test.board.demo_board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.test.board.demo_board.dto.response.UserAccountResponse;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserAccountController {
    @GetMapping("/admin/members")
    public String members() {
        return "admin/members";
    }

    @ResponseBody
    @GetMapping("/api/admin/members")
    public List<UserAccountResponse> getMembers() {
        return List.of();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    @DeleteMapping("/api/admin/members/{userId}")
    public void delete(@PathVariable String userId) {
    }
}
