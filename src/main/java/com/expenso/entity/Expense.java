package com.expenso.entity;

import lombok.Data;

@Data
public class Expense {
    int id;
    String title;
    String type;
    double price;
}
