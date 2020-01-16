package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

Book findByEmail(String email);
  
} 