package com.expenso.service.impl;

import com.expenso.entity.Expense;
import com.expenso.entity.Make;
import com.expenso.mapper.ExpenseMapper;
import com.expenso.service.ExpenseService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Resource
    ExpenseMapper mapper;

    @Override
    public Map<Expense, Boolean> getExpenseList() {
        Set<Integer> set = new HashSet<>();
        this.getBorrowList().forEach(make -> set.add(make.getBid()));
        Map<Expense, Boolean> map = new LinkedHashMap<>();
        mapper.getExpenseList().forEach(expense -> map.put(expense, set.contains(expense.getId())));
        return map;
    }

    @Override
    public List<Expense> getActiveExpenseList() {
        Set<Integer> set = new HashSet<>();
        this.getBorrowList().forEach(make -> set.add(make.getBid()));
        return mapper.getExpenseList()
                .stream()
                .filter(expense -> !set.contains(expense.getId()))
                .toList();
    }

    @Override
    public List<Make> getBorrowList() {
        return mapper.getBorrowList();
    }

    @Override
    public void addBorrow(int sid, int bid) {
        mapper.addBorrow(sid, bid);
    }

    @Override
    public void returnExpense(String id) {
        mapper.deleteBorrow(id);
    }

    @Override
    public void addExpense(String title, String type, double price) {
        mapper.addExpense(title, type, price);
    }

    @Override
    public void deleteExpense(int bid) {
        mapper.deleteExpense(bid);
    }

    @Override
    public void queryExpenseByName(String title){mapper.queryExpenseByName(title);}


}
