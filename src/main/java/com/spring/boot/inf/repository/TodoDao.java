package com.spring.boot.inf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.inf.beans.TodoItems;

public interface TodoDao extends JpaRepository<TodoItems, Integer>{

}
