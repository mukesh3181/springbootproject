package com.crud.crudoperations11.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.crudoperations11.Exceptions.BadRequestException;
import com.crud.crudoperations11.Exceptions.ResourceNotFoundException;
import com.crud.crudoperations11.model.User;
import com.crud.crudoperations11.repository.UserRepository;


@Service
public class Userservice 
{   
	@Autowired
	private UserRepository userrepository;

	public User adduserdata(User user) 
	{
		
		if(user.getName().isEmpty()||user.getEmailid().isEmpty()||user.getLocation().isEmpty()||user.getPhno().isEmpty())
		{
			throw new BadRequestException("user cannot be null");
		}
		return userrepository.save(user);
	}

	public List<User> getusersdata() 
	{
		return userrepository.findAll();
	}
	public List<User> getusersdatabylocation(String location) 
	{
		if(location==null||location.isEmpty())
		{ throw new BadRequestException("location cannot be null");
        }
		return userrepository.findByLocation(location);
	}
	
	
	public List<User> getusersdatabyagelessthan(Integer age) 
	{
		if(age==0||age<0)
		{
			throw new BadRequestException("age is not valid");
		}
		return userrepository.findByAgeLessThan(age);
	}
	
	
	public Optional<User> getuserbyid(Long id) 
	{
		
          return userrepository.findById(id).orElseThrow(()->new UserNotFoundException("user not found for given id"+id));

	}
		//return userrepository.findById(id).orElseThrow(()->new UserNotFoundException("user not found for given id"+id));

	
	
	

		public void deletebyid(Long id) 
		{
			
		if(!userrepository.existsById(id))
		{
			throw new ResourceNotFoundException("user not found with id"+id);
		}
		userrepository.deleteById(id);
	}


	
	public User getuserrbyid(Long id)
	{
	Optional<User>user=userrepository.findById(id);
	
	return user.get();
		
	}
	public User edituserdata(Long id, User userdetails) 
	{
		if(userdetails.getName().isEmpty()||userdetails.getEmailid().isEmpty()||userdetails.getLocation().isEmpty()||userdetails.getPhno().isEmpty())
		{			throw new BadRequestException("user cannot be null");
        }
		if(!userrepository.existsById(id))
		{
			throw new ResourceNotFoundException("user not found with id"+id);
		}
		User user = getuserrbyid(id);
		 user.setName(userdetails.getName());
		 user.setEmailid(userdetails.getEmailid());
		 user.setPhno(userdetails.getPhno());
		 user.setAge(userdetails.getAge());
		 user.setLocation(userdetails.getLocation());
		return userrepository.save(user);
	}

	public List<User>getusersdatabyname(String name)
	{
		if(name==null||name.isEmpty())
		{ throw new BadRequestException("name cannot be null");
        }

		return userrepository.findByName(name);
	}

	public List<User> getusersByNameStartsWith(String prefix)
	{
		if(prefix==null||prefix.isEmpty())
		{ throw new BadRequestException("prefix cannot be null");
        }
		return userrepository.findByNameStartsWith(prefix);
	}

	

}
