package com.expenso.controller;

import com.expenso.service.ExpenseService;
import jakarta.annotation.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class ExpenseController {

    @Resource
    ExpenseService service;

    @GetMapping("/sheet")
    public String Expenses(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nickname", user.getUsername());
        model.addAttribute("expense_list", service.getExpenseList().keySet());
        model.addAttribute("expense_list_status", new ArrayList<>(service.getExpenseList().values()));
        return "sheet";
    }

    @GetMapping("/add-expense")
    public String addExpense(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nickname", user.getUsername());
        return "add-expense";
    }

    @PostMapping("/add-expense")
    public String addExpense(String title, String type, double price){
        service.addExpense(title, type, price);
        return "redirect:/sheet";
    }

    @GetMapping("/delete")
    public String deleteExpense(int bid){
        service.deleteExpense(bid);
        return "redirect:/sheet";
    }

    @GetMapping("/query")
    public String queryExpenseByName(String title){
        service.queryExpenseByName(title);
        return "redirect:/sheet";
    }
}
