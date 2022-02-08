package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingge.springboot.entity.Book;
import com.qingge.springboot.entity.User;

//
//@Mapper
public interface BookMapper extends BaseMapper<Book> {
    void selectOne();

//    @Select("SELECT * from sys_user")
//    List<User> findAll();
//
//    @Insert("INSERT into sys_user(username, password,nickname,email,phone,address) VALUES (#{username}, #{password}," +
//            " #{nickname}, #{email},#{phone}, #{address})")
//    int insert(User user);
//
//    int update(User user);
//
//    @Delete("delete from sys_user where id = #{id}")
//    Integer deleteById(@Param("id") Integer id);
//    @Select("SELECT * from sys_user where username like concat('%',#{username},'%') LIMIT #{pageNum},#{pageSize}")
//    List<User>  selectPage(Integer pageNum, Integer pageSize,String username);
//    @Select("SELECT count(*) from sys_user where username like concat('%',#{username},'%')")
//    Integer selectTotal(String username);
}