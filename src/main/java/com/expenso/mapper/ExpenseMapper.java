package com.expenso.mapper;

import com.expenso.entity.Expense;
import com.expenso.entity.Make;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ExpenseMapper {

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "sid", property = "sid"),
            @Result(column = "bid", property = "bid"),
            @Result(column = "time", property = "time"),
            @Result(column = "name", property = "userName"),
            @Result(column = "title", property = "ExpenseName")
    })
    @Select("""
            select * from make left join user on make.sid = user.id
             left join expense on expense.id = make.bid
            """)
    List<Make> getBorrowList();

    @Insert("insert into make(sid, bid, time) values(#{sid}, #{bid}, NOW())")
    void addBorrow(@Param("sid") int sid, @Param("bid") int bid);

    @Delete("delete from make where id = #{id}")
    void deleteBorrow(String id);

    @Select("select * from expense")
    List<Expense> getExpenseList();

    @Delete("delete from expense where id = #{id}")
    void deleteExpense(int id);

    @Insert("insert into expense(title, `type`, price) values(#{title}, #{type}, #{price})")
    void addExpense(@Param("title") String title, @Param("type") String type, @Param("price") double price);

    @Select("select * from expense where title =#{title}")
    void queryExpenseByName(@Param("title") String title);
}
