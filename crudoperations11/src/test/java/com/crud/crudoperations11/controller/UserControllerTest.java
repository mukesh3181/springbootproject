package com.crud.crudoperations11.controller;
 import com.crud.crudoperations11.model.User;
import com.crud.crudoperations11.service.Userservice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
 
@WebMvcTest(usercontroller.class)
public class UserControllerTest {
 
	@Autowired
	private MockMvc mockMvc;
 
	@MockBean
    private Userservice userService;
  
	@Autowired
	private ObjectMapper objectMapper;
 
	@Test

	public void testAddUserData() throws Exception {

		User user = new User(1L, "arjun", "arjun23@gmail.com", "123456789", "hyderabad", 32);

		when(userService.adduserdata(any(User.class))).thenReturn(user);
 
		mockMvc.perform(MockMvcRequestBuilders.post("/user/add").contentType(MediaType.APPLICATION_JSON)

				.content(objectMapper.writeValueAsString(user))).andExpect(MockMvcResultMatchers.status().isCreated())

				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("arjun"));

	}
	@Test
    public void testCreateUserFailure() throws Exception
    {
		User user = new User(1L, "arjun", "arjun23@gmail.com", "123456789", "hyderabad", 32);
		
        when(userService.adduserdata(any(User.class))).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/add")
        		 .contentType(MediaType.APPLICATION_JSON_VALUE)
                 .accept(new ObjectMapper().writeValueAsString(user)))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  
     }
	@Test

	public void testGetAllUsers() throws Exception {

		User user1 = new User(1L, "arjun", "arjun23@gmail.com", "123456789", "hyderabad", 32);

		User user2 = new User(2L, "arjunn", "arjun233@gmail.com", "124456789", "hyderabad", 32);

		List<User> users = Arrays.asList(user1, user2);

		when(userService.getusersdata()).thenReturn(users);
 
		mockMvc.perform(MockMvcRequestBuilders.get("/user/all")).andExpect(MockMvcResultMatchers.status().isOk())

				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("arjun"))

				.andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("arjunn"));

	}
	@Test
    public void testGetAllUsersFailure() throws Exception {
   	 User user1=new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);
		  User user2=new User((long) 2, "arjunn","arjun233@gmail.com","124456789","hyderabad",32);
		  List<User>user3=Arrays.asList(user1,user2);
       when(userService.getusersdata()).thenReturn(user3);
 
       mockMvc.perform(MockMvcRequestBuilders.get("/user/all"))
               .andExpect(MockMvcResultMatchers.status().isNotFound());
               
   }
		
 
	@Test
	public void testGetUserById() throws Exception {

		User user = new User(1L, "arjun", "arjun23@gmail.com", "123456789", "hyderabad", 32);

		when(userService.getuserbyid(1L)).thenReturn(Optional.of(user));
 
		mockMvc.perform(MockMvcRequestBuilders.get("/user/all/1")).andExpect(MockMvcResultMatchers.status().isOk())

				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("arjun"));

	}
	
	@Test
    public void testGetUserByIdFailure() throws Exception
    {
		User user = new User(1L, "arjun", "arjun23@gmail.com", "123456789", "hyderabad", 32);
        when(userService.getuserbyid(1L)).thenReturn(Optional.of(user));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/all/1"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());        
    }
 
	@Test

	public void testDeleteUser() throws Exception {

		willDoNothing().given(userService).deletebyid(1L);
 
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/1"))

				.andExpect(MockMvcResultMatchers.status().isNoContent());

	}
	
}

 
