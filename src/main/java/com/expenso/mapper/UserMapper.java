package com.expenso.mapper;

import com.expenso.entity.Account;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from user where username = #{username}")
    Account findUserByName(String username);


}
