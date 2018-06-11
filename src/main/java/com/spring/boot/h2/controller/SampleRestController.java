package com.spring.boot.h2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.h2.beans.Sample;
import com.spring.boot.h2.repository.SampleDao;


@RestController
@RequestMapping("/api")
public class SampleRestController {
	@Autowired
	SampleDao sample;

	@GetMapping(path = "/getalldata")
	@ResponseBody
	public List<Sample> getAllData() {
		return (List<Sample>) sample.findAll();
	}

	@GetMapping(path = "/getalldata/{id}")
	@ResponseBody
	public Optional<Sample> getDataById(@PathVariable(name = "id") int id) {
		return sample.findById(id);
	}

	@DeleteMapping(path = "/deletebyid/{id}")
	public void removebyId(@PathVariable(name = "id") int id) {
		sample.deleteById(id);
		System.out.println("Record has been deleted with the id: " + id);
	}

	@PostMapping(path = "/save", produces = "application/json")
	public void createData(@RequestBody Sample data) {
		sample.save(data);
		System.out.println("Sample data has been saved successfully: " + data);
	}

	@PostMapping(path = "/update", produces = "application/json")
	public void updateData(@RequestBody Sample data) {
		if (sample.existsById(data.getId())) {
			sample.save(data);
			System.out.println("Data has been updated successfully :" + data);
		} else {
			System.out.println("Record not exists with the Id: " + data.getId());
		}
	}
}
