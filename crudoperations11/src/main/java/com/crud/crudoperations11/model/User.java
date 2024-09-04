package com.crud.crudoperations11.model;

import org.springframework.stereotype.Component;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
@Component
public class User
{
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY) 
private Long id;
private String name;
private String emailid;
private String phno;
private String location;
private Integer age;

public User() {
}

public String getLocation() {
	return location;
}



public void setLocation(String location) {
	this.location = location;
}
public Integer getAge() {
	return age;
}
public void setAge(Integer age) {
	this.age = age;
}

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmailid() {
	return emailid;
}
public void setEmailid(String emailid) {
	this.emailid = emailid;
}

public String getPhno() {
	return phno;
}

public void setPhno(String phno) {
	this.phno = phno;
}


public User(Long id, String name, String emailid, String phno, String location, Integer age) {
	super();
	this.id = id;
	this.name = name;
	this.emailid = emailid;
	this.phno = phno;
	this.location = location;
	this.age = age;
}











}
