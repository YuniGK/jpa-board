package org.test.board.demo_board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.test.board.demo_board.dto.response.UserAccountResponse;

@RequiredArgsConstructor
@RequestMapping("/management/user-accounts")
@Controller
public class UserAccountManagementController {
    @GetMapping
    public String userAccounts(Model model) {
        model.addAttribute(
                "userAccounts",
                userAccountManagementService.getUserAccounts().stream().map(UserAccountResponse::from).toList()
        );

        return "management/user-accounts";
    }

    @ResponseBody
    @GetMapping("/{userId}")
    public UserAccountResponse userAccount(@PathVariable String userId) {
        return UserAccountResponse.from(userAccountManagementService.getUserAccount(userId));
    }

    @PostMapping("/{userId}")
    public String deleteUserAccount(@PathVariable String userId) {
        userAccountManagementService.deleteUserAccount(userId);

        return "redirect:/management/user-accounts";
    }
}
