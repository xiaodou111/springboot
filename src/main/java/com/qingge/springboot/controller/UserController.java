package com.qingge.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.config.Result;
import com.qingge.springboot.entity.User;
import com.qingge.springboot.mapper.UserMapper;
import com.qingge.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private  UserMapper userMapper;

    @Autowired
    private UserService userService;

    // 新增和修改
    @PostMapping
    public Boolean save(@RequestBody User user) {
        // 新增或者更新
        return userService.saveUser(user);
    }
//    登录接口
    @PostMapping("/login")
    public Result login(@RequestBody User user) {

    User res= userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()).eq(User::getPassword,user.getPassword()));
     if(res==null){
         return Result.error("-1","用户密码错误1");
     }
        return Result.success(res);
    }
    @PostMapping("/register")
    public Result register(@RequestBody User user) {

        User res= userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()));
        if(res!=null){
            return Result.error("-1","用户名已经被注册了");
        }
        if(user.getPassword()==null){
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success();
    }

    // 查询所有数据
    @GetMapping
    public List<User> findAll() {

        return userService.list();
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return userService.removeBatchByIds(ids);
    }
//    @RequestParam接受 ?pageNum=1&pageSize=10
//    @GetMapping("/page")
//    public Map<String, Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String username  ) {
//         pageNum = (pageNum - 1) * pageSize;
//        Integer total = userMapper.selectTotal(username);
//        List<User> data = userMapper.selectPage(pageNum, pageSize,username);
//        Map<String,Object> res= new HashMap<>();
//        res.put("data",data);
//        res.put("total",total);
//        return res;
//    }
@GetMapping("/page")
public IPage<User> findPage(@RequestParam Integer pageNum,
                            @RequestParam Integer pageSize,
                            @RequestParam(defaultValue = "") String username,
                            @RequestParam(defaultValue = "") String email,
                            @RequestParam(defaultValue = "") String address) {
    IPage<User> page= new Page<>(pageNum,pageSize);
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    if(!"".equals(username)){
        queryWrapper.like("username",username);
    }
    if(!"".equals(email)){
        queryWrapper.like("email",email);
    }
    if(!"".equals(address)){
        queryWrapper.like("address",address);
    }
    queryWrapper.orderByDesc("id");
    IPage<User> userPage = userService.page(page, queryWrapper);
    return userPage;

//    导出接口

}
}