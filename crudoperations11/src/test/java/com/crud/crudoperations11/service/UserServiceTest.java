package com.crud.crudoperations11.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.crud.crudoperations11.model.User;
import com.crud.crudoperations11.repository.UserRepository;



public class UserServiceTest
{
	 
	    @InjectMocks
	    private Userservice userservice;

	    @Mock
	    private UserRepository userrepository;
	    
	    
	    
	    @BeforeEach
	    public void init() {
	        MockitoAnnotations.openMocks(this);
	    }

	  @Test
	  public void testGetAllUsers()
	  {
		  User user1=new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);
		  User user2=new User((long) 2, "arjunn","arjun233@gmail.com","124456789","hyderabad",32);
		  when(userrepository.findAll()).thenReturn(Arrays.asList(user1,user2));
	      assertEquals(2,userservice.getusersdata().size());


	  }
	  
	 
	  
	  @Test
	  public void testGetUserById()
	  {
		  User user=new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);
          when(userrepository.findById((long)1)).thenReturn(Optional.of(user));
           Optional<User>foundUser=userservice.getuserbyid((long)1);
           assertTrue(foundUser.isPresent());
           assertEquals("arjun23@gmail.com",foundUser.get().getEmailid());
	  }
	  @Test
	  public void testGetUserByIdFailure()
	  {
		  when(userrepository.findById((long)1)).thenReturn(Optional.empty());
          Optional<User>foundUser=userservice.getuserbyid((long)1);
          assertNull(foundUser);		  
	  }
	  @Test
	  public void testSaveUser()
	  {
		  User user=new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);
		  when(userrepository.save(user)).thenReturn(user);
		   User savedUser=userservice.adduserdata(user);
           assertEquals("arjun",savedUser.getName());
	  }
	  @Test
	  public void testDeleteUser()
	  {
		  User user=new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);
          doNothing().when(userrepository).deleteById(1L);
		  userservice.deletebyid(1L);
		  verify(userrepository,times(1)).deleteById(1L);
		  
		  
	}
	 
	@Test
	public void testGetUsersDataByName()
	{
		  User user=new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);
		  when(userrepository.findByName("arjun")).thenReturn(Arrays.asList(user));

		  List<User>founduser1=userservice.getusersdatabyname("arjun");
		  assertEquals(1,founduser1.size());

	}
	
	
	@Test
	public void testGetUsersDataByLocation()
	{
		  User user=new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);
		  when(userrepository.findByLocation("hyderabad")).thenReturn(Arrays.asList(user));

		  List<User>founduser1=userservice.getusersdatabylocation("hyderabad");
		  assertEquals(1,founduser1.size());

	}
	
	@Test
	public void testAgeLessThan()
	{
		  User user=new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);
		  when(userrepository.findByAgeLessThan(30)).thenReturn(Arrays.asList(user));

		  List<User>founduser1=userservice.getusersdatabyagelessthan(30);
		  assertEquals(1,founduser1.size());

	}

	@Test
	public void testFindByNameStartingWithPrefix()
	{
		  User user=new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);
		  when(userrepository.findByNameStartsWith("a")).thenReturn(Arrays.asList(user));

		  List<User>founduser1=userservice.getusersByNameStartsWith("a");
		  assertEquals(1,founduser1.size());

	}
	
 }
