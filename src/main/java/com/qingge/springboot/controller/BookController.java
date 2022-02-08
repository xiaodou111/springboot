package com.qingge.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.config.Result;
import com.qingge.springboot.entity.Book;
import com.qingge.springboot.entity.User;
import com.qingge.springboot.mapper.BookMapper;
import com.qingge.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {


    @Autowired
    private  BookMapper bookMapper;

    @Autowired
    private BookService bookService;

    // 新增和修改
    @PostMapping
    public Boolean save(@RequestBody Book book) {
        // 新增或者更新
        return bookService.saveBook(book);
    }


    // 查询所有数据
    @GetMapping
    public List<Book> findAll() {

        return bookService.list();
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return bookService.removeById(id);
    }
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return bookService.removeBatchByIds(ids);
    }
//    @RequestParam接受 ?pageNum=1&pageSize=10
//    @GetMapping("/page")
//    public Map<String, Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String Bookname  ) {
//         pageNum = (pageNum - 1) * pageSize;
//        Integer total = BookMapper.selectTotal(Bookname);
//        List<Book> data = BookMapper.selectPage(pageNum, pageSize,Bookname);
//        Map<String,Object> res= new HashMap<>();
//        res.put("data",data);
//        res.put("total",total);
//        return res;
//    }
@GetMapping("/page")
    public IPage<Book> findPage(@RequestParam Integer pageNum,
                            @RequestParam Integer pageSize,
                            @RequestParam(defaultValue = "") String name)
                             {
    IPage<Book> page= new Page<>(pageNum,pageSize);
    QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
    if(!"".equals(name)){
        queryWrapper.like("name",name);
    }

    queryWrapper.orderByDesc("id");
    IPage<Book> bookPage = bookService.page(page, queryWrapper);
    return bookPage;

//    导出接口

}
}