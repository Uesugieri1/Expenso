package com.expenso.service;

import com.expenso.entity.Expense;
import com.expenso.entity.Make;

import java.util.List;
import java.util.Map;

public interface ExpenseService {
    List<Make> getBorrowList();
    Map<Expense, Boolean> getExpenseList();
    List<Expense> getActiveExpenseList();
    void addBorrow(int sid, int bid);
    void returnExpense(String id);
    void addExpense(String title, String type, double price);
    void deleteExpense(int bid);

    void queryExpenseByName(String title);

}
