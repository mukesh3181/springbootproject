package com.crud.crudoperations11.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.BDDMockito.*;
import org.springframework.http.MediaType;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crud.crudoperations11.model.User;
import com.crud.crudoperations11.service.Userservice;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(UserControllerTest.class)
public class UserControllerTest {

    
    private MockMvc mockMvc;

    @MockBean
    private Userservice userservice;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc=MockMvcBuilders.standaloneSetup(UserControllerTest.class).build();
    	user=new User((long) 1,"arjun","arjun23@gmail.com","123456789","hyderabad",32);

    }

    @Test
    public void testCreateUser() throws Exception
    {
        when(userservice.adduserdata(any(User.class))).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/add")
        		 .contentType(MediaType.APPLICATION_JSON_VALUE)
                 .content(objectMapper.writeValueAsString(user)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(user.getName()));
  
      }
    @Test
    public void testCreateUserFailure() throws Exception
    {
    	User user=new User((long) 1,"arjun","arjun23@gmail.com","123456789","hyderabad",32);
        when(userservice.adduserdata(user)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/add")
        		 .contentType(MediaType.APPLICATION_JSON_VALUE)
                 .accept(new ObjectMapper().writeValueAsString(user)))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  
     }
    
    @Test
    public void testGetUserById() throws Exception
    {
    	User user=new User();
    	user.setId(1L);
    	user.setName("arjun");
    	//User user=new User((long) 1,"arjun","arjun23@gmail.com","123456789","hyderabad",32);
        when(userservice.getuserbyid(1L)).thenReturn(Optional.of(user));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/all/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("arjun"));

               
    }
    @Test
    public void testGetUserByIdFailure() throws Exception 
    {
    	User user=new User((long) 1,"arjun","arjun23@gmail.com","123456789","hyderabad",32);
        when(userservice.getuserbyid(1L)).thenReturn(Optional.of(user));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/all/1"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());        
    }
    
    
    @Test
    public void testGetAllUsers() throws Exception {
    	 User user1=new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);
		  User user2=new User((long) 2, "arjunn","arjun233@gmail.com","124456789","hyderabad",32);
		  List<User>user3=Arrays.asList(user1,user2);
        when(userservice.getusersdata()).thenReturn(user3);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("arjun"));
                
    }
    
    @Test
    public void testGetAllUsersFailure() throws Exception {
   	 User user1=new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);
		  User user2=new User((long) 2, "arjunn","arjun233@gmail.com","124456789","hyderabad",32);
		  List<User>user3=Arrays.asList(user1,user2);
       when(userservice.getusersdata()).thenReturn(user3);

       mockMvc.perform(MockMvcRequestBuilders.get("/user/all"))
               .andExpect(MockMvcResultMatchers.status().isNotFound());
               
   }
    
     @Test
    public void testDeleteUser() throws Exception
    {
    	willDoNothing().given(userservice).deletebyid(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/1"))
        .andExpect(MockMvcResultMatchers.status().isNoContent());

    }
    @Test
    public void testDeleteUserFailure() throws Exception
    {
    	doThrow(new RuntimeException("data not found")).when(userservice).deletebyid(1L);
    	mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/1"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    }