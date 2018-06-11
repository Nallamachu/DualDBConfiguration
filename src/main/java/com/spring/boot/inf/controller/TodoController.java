package com.spring.boot.inf.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.inf.beans.TodoItems;
import com.spring.boot.inf.repository.TodoDao;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
	@Autowired
	TodoDao todo;

	@GetMapping(path = "/getalldata")
	public List<TodoItems> getAllData() {
		return (List<TodoItems>) todo.findAll();
	}

	@GetMapping(path = "/getalldata/{id}")
	public Optional<TodoItems> getDataById(@PathVariable(name = "id") int id) {
		return todo.findById(id);
	}

	@DeleteMapping(path = "/deletebyid/{id}")
	public void removebyId(@PathVariable(name = "id") int id) {
		todo.deleteById(id);
		System.out.println("Record has been deleted with the id: " + id);
	}

	@PostMapping(path = "/save", produces = "application/json")
	public void createData(@RequestBody TodoItems data) {
		todo.save(data);
		System.out.println("TodoItems data has been saved successfully: " + data);
	}

	@PostMapping(path = "/update", produces = "application/json")
	public void updateData(@RequestBody TodoItems data) {
		if (todo.existsById(data.getId())) {
			todo.save(data);
			System.out.println("Data has been updated successfully :" + data);
		} else {
			System.out.println("Record not exists with the Id: " + data.getId());
		}
	}
}