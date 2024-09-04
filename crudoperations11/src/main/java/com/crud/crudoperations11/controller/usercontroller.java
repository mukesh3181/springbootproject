package com.crud.crudoperations11.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crudoperations11.model.User;
import com.crud.crudoperations11.service.Userservice;




@RestController
@RequestMapping("/user")
public class usercontroller 
{
	@Autowired
	private Userservice userservice;
	

 @PostMapping("/add")
  public ResponseEntity<User> adduserdata(@RequestBody User user)
  {
	User userData=userservice.adduserdata(user);
	return ResponseEntity.status(HttpStatus.CREATED).body(userData);
}
      

 
  @GetMapping("/all")
 public ResponseEntity<List<User>>getusersdata()
 {
	  List<User>users=userservice.getusersdata();
		 if(users.isEmpty())
		 {
			 return ResponseEntity.noContent().build();
		 }
		 
			 return ResponseEntity.ok(users);

 }
 
 @GetMapping("/all/{id}")
 public ResponseEntity<User>getusersdatabyid(@PathVariable Long id)
 {
	 Optional<User>user=userservice.getuserbyid(id);
	
		 return ResponseEntity.ok(user.get());
	 
	 
	 
 }
 
 @DeleteMapping("/delete/{id}")
 
 public ResponseEntity<Void> deletebyid(@PathVariable Long id)
 {
		 userservice.deletebyid(id);
		return ResponseEntity.noContent().build();
}
 @PutMapping("/edit/{id}")
 public ResponseEntity<User> editbyid(@PathVariable Long id,@RequestBody User userdetails)
 {
	 User editedData=userservice.edituserdata(id, userdetails);
	
	 return ResponseEntity.ok(editedData);
 }
 
 
 @GetMapping("/name/{name}")
 public ResponseEntity<List<User>>getusersdatabyname(@PathVariable String name)
 {
	 
	 List<User>users=userservice.getusersdatabyname(name);
	
	 return ResponseEntity.ok(users);
  }
 @GetMapping("/location/{location}")
 public ResponseEntity<List<User>>getusersdatabylocation(@PathVariable String location)
 {
	 
	 List<User>users=userservice.getusersdatabylocation(location);
	
	 return ResponseEntity.ok(users);
 }
 
 @GetMapping("/age/{age}")
 public ResponseEntity< List<User>>getusersdatabyagelessthan(@PathVariable Integer age)
 {
	 
	 List<User>users=userservice.getusersdatabyagelessthan(age);
	 
	 return ResponseEntity.ok(users);
 }
 @GetMapping("/prefix/{prefix}")
 public ResponseEntity<List<User>>getusersByNameStartsWith(@PathVariable String prefix)
 {
	
	 List<User>users=userservice.getusersdatabylocation(prefix);
	 
	 return ResponseEntity.ok(users);
 }
 }
