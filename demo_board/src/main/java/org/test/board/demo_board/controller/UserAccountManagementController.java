package org.test.board.demo_board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.test.board.demo_board.dto.response.UserAccountResponse;
import org.test.board.demo_board.service.UserAccountManagementService;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@RequestMapping("/management/user-accounts")
@Controller
public class UserAccountManagementController {

    private final UserAccountManagementService userAccountManagementService;

    @GetMapping
    public String userAccounts(Model model) {
        return "management/user-accounts";
    }

    @ResponseBody
    @GetMapping("/{userId}")
    public UserAccountResponse userAccount(@PathVariable String userId) {
        return UserAccountResponse.from(null);
    }

    @PostMapping("/{userId}")
    public String deleteUserAccount(@PathVariable String userId) {
        return "redirect:/management/user-accounts";
    }
}
