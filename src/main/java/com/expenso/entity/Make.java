package com.expenso.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Make {
    int id;
    int sid;
    int bid;
    Date time;
    String ExpenseName;
    String userName;
}
