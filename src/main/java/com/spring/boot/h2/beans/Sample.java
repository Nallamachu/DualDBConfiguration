package com.spring.boot.h2.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "SAMPLE")
@Component
public class Sample implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column
	private int id;
	@Column
	private String name;
	@Column
	private String location;

	public Sample() {

	}

	public Sample(int id, String name, String location) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Sample [id=" + id + ", name=" + name + ", location=" + location + "]";
	}

}