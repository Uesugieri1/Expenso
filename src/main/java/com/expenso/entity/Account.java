package com.expenso.entity;

import lombok.Data;

@Data
public class Account {
    int sid;
    String username;
    String password;
    String role;
}
