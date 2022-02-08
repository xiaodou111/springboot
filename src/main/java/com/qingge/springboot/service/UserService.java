package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingge.springboot.entity.User;
import com.qingge.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper,User> {
    public Boolean saveUser(User user) {
//         if(user.getId()==null){
//           return save(user);
//         }else{
//            return updateById(user);
//         }
        return saveOrUpdate(user);
    }
//    public Boolean login(User user){
//
//    }
//    @Autowired
//    private UserMapper userMapper;
//    public  int save(User user){
//        if(user.getId()==null){
//           return userMapper.insert(user);
//        }else {
//           return userMapper.update(user);
//        }
//    }
}
