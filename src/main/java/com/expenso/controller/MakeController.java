package com.expenso.controller;

import com.expenso.service.ExpenseService;
import com.expenso.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MakeController {

    @Resource
    ExpenseService service;

    @Resource
    UserService userService;

    @GetMapping({"/borrow", "/"})
    public String borrow(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nickname", user.getUsername());
        model.addAttribute("borrow_list", service.getBorrowList());
        model.addAttribute("Expense_count", service.getExpenseList().size());

        return "index";
    }

    @GetMapping("/add-borrow")
    public String addBorrow(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nickname", user.getUsername());
        model.addAttribute("expense_list", service.getActiveExpenseList());

        return "add-borrow";
    }

    @PostMapping("/add-borrow")
    public String addBorrow(int user, int Expense){
        service.addBorrow(user, Expense);
        return "redirect:/borrow";
    }

    @GetMapping("/return-Expense")
    public String returnExpense(String id){
        service.returnExpense(id);
        return "redirect:/borrow";
    }
}
