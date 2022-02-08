package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingge.springboot.entity.Book;
import com.qingge.springboot.mapper.BookMapper;
import org.springframework.stereotype.Service;

@Service
public class BookService extends ServiceImpl<BookMapper,Book> {
    public  Boolean saveBook(Book book) {
//         if(book.getId()==null){
//           return save(book);
//         }else{
//            return updateById(book);
//         }
        return saveOrUpdate(book);
    }

}
