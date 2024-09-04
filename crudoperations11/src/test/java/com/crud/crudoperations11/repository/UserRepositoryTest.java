package com.crud.crudoperations11.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.crud.crudoperations11.model.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userrepository;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setName("Arjun");
        user.setEmailid("arjun23@gmail.com");
        user.setPhno("123456789");
        user.setLocation("Hyderabad");
        user.setAge(32);

        User savedUser = userrepository.save(user);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    public void testFindByName() {
        User user = new User();
        user.setName("Arjun");
        user.setEmailid("arjun23@gmail.com");
        user.setPhno("123456789");
        user.setLocation("Hyderabad");
        user.setAge(32);
        userrepository.save(user);

        List<User> users = userrepository.findByName("Arjun");
        assertThat(users).isNotEmpty();
        assertThat(users.get(0).getName()).isEqualTo("Arjun");
    }

    @Test
    public void testFindByLocation() {
        User user = new User();
        user.setName("Arjun");
        user.setEmailid("arjun23@gmail.com");
        user.setPhno("123456789");
        user.setLocation("Hyderabad");
        user.setAge(32);
        userrepository.save(user);

        List<User> users = userrepository.findByLocation("Hyderabad");
        assertThat(users).isNotEmpty();
        assertThat(users.get(0).getLocation()).isEqualTo("Hyderabad");
    }

    @Test
    public void testFindByAgeLessThan() {
        User user = new User();
        user.setName("Arjun");
        user.setEmailid("arjun23@gmail.com");
        user.setPhno("123456789");
        user.setLocation("Hyderabad");
        user.setAge(32);
        userrepository.save(user);

        List<User> users = userrepository.findByAgeLessThan(35);
        assertThat(users).isNotEmpty();
        assertThat(users.get(0).getAge()).isEqualTo(32);
    }

    @Test
    public void testFindByNameStartsWith() {
        User user = new User();
        user.setName("Arjun");
        user.setEmailid("arjun23@gmail.com");
        user.setPhno("123456789");
        user.setLocation("Hyderabad");
        user.setAge(32);
        userrepository.save(user);

        List<User> users = userrepository.findByNameStartsWith("Ar");
        assertThat(users).isNotEmpty();
        assertThat(users.get(0).getName()).isEqualTo("Arjun");
    }
}
