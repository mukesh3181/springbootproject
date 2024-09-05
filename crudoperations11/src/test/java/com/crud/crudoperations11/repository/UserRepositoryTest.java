
package com.crud.crudoperations11.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import com.crud.crudoperations11.model.User;
import com.crud.crudoperations11.service.Userservice;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest 
{
	

    @Autowired
    private UserRepository userrepository;
   
    
     @Test
     public void testSaveUser() {
         User user = new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);
         User savedUser =userrepository.save(user);
         assertNotNull(savedUser);
         assertEquals("arjun",savedUser.getName());

     }
     @Test
     public void testGetListOfUsers() {
         userrepository.save(new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32));

         List<User> userData = userrepository.findAll();
         assertNotNull(userData);
     }
     @Test
     public void testGetUserById() {
         User user = new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);
         User userData = userrepository.findById(user.getId()).orElse(null);
         assertNotNull(userData);
         assertEquals(user.getId(), userData.getId());
     }
   
     
    
  
     @Test
     public void testUpdateUser() {
         User user = new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);

         user.setName("akhil");
         userrepository.save(user);
         User updatedUser = userrepository.findById(user.getId()).orElse(null);
         assertNotNull(updatedUser);
         assertEquals("akhil", updatedUser.getName());
     }
     @Test
     public void testDeleteUser() 
     {
         User user = new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);
         userrepository.deleteById(user.getId());
         User deletedUser = userrepository.findById(user.getId()).orElse(null);
         assertNull(deletedUser);
     }
     
     @Test
     public void testGetUserByName() {
         User user = new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);
         List<User>userData = userrepository.findByName(user.getName());
         assertNotNull(userData);
         assertEquals("arjun", userData.get(0).getName());
     }
     @Test
     public void testGetUserByLocation() {
         User user = new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);
         List<User>userData = userrepository.findByLocation(user.getLocation());
         assertNotNull(userData);
         assertEquals("hyderabad", userData.get(0).getLocation());
     }
     @Test
     public void testGetUserAgeLessThan() {
         User user = new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",28);
         List<User>userData = userrepository.findByAgeLessThan(user.getAge());
         assertNotNull(userData);
         assertTrue(userData.stream().allMatch(user1->user1.getAge()<30));

     }
     @Test
     public void testUserNameStartsWith() {
         User user = new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);
         List<User>userData = userrepository.findByNameStartsWith(user.getName());
         assertNotNull(userData);
         assertTrue( userData.get(0).getName().startsWith("ar"));
     }
   
     
     
     @Test
     public void testSaveUserFailure() {
         User user = new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);
       Exception thrown=assertThrows(DataIntegrityViolationException.class,()->userrepository.save(user));
      assertNotNull(thrown);
     }
     

     @Test
     public void testGetUserByIdNotFound() 
     {
    	 User user = new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);
         User userData = userrepository.findById(2L).orElse(null);
         assertNotNull(userData);
         assertEquals(user.getId(), userData.getId());
         
      }
     
     @Test
     public void testGetListOfUsersNotFound() 
     {
    	 userrepository.deleteAll();
         List<User> userData = userrepository.findAll();
         assertNotEquals(userData.size(),0);

     }
     @Test
     public void testGetUserByNameFailure() {
         List<User>userData = userrepository.findByName("ravi");
         assertNotNull(userData);
         assertEquals("arjun", userData.get(0).getName());
     }
     @Test
     public void testGetUserByLocationNotFound() {
         List<User>userData = userrepository.findByLocation("pune");
         assertNotNull(userData);
         assertEquals("hyderabad", userData.get(0).getLocation());
     }
     @Test
     public void testGetUserAgeLessThanNotFound() {
         List<User>userData = userrepository.findByAgeLessThan(40);
         assertNotNull(userData);
         assertTrue(userData.stream().allMatch(user1->user1.getAge()<30));

     }
     @Test
     public void testUserNameStartsWithNotFound() {
         List<User>userData = userrepository.findByNameStartsWith("ravi");
         assertNotNull(userData);
         assertTrue( userData.get(0).getName().startsWith("ar"));
     }
     
     @Test
     public void testUpdateUserFailure() {
         User user = new User((long) 1, "arjun","arjun23@gmail.com","123456789","hyderabad",32);

         user.setName("akhil");
         userrepository.save(user);
         User updatedUser = userrepository.findById(user.getId()).orElse(null);
         assertEquals("vijay", updatedUser.getName());
     }
     @Test
     public void testDeleteUserFailure() 
     {
    	 Long nonexistid=999L;
        Exception thrown=assertThrows(
        		EmptyResultDataAccessException.class,()->userrepository.deleteById(nonexistid));
        
        assertNotNull(thrown);
     } 
     
     
    
    

}
