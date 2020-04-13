package com.test.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.MongoDbApplication;
import com.example.model.CustomResponse;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {MongoDbApplication.class,UserService.class})
public class ServiceUnitTest {
	
@Autowired
UserService service;

@MockBean
UserRepository repository;	

@Test
public void getAllUserTest()
{
when(repository.findAll()).thenReturn(Stream.of(new User(1,"dhruv","male",new java.util.Date(1998-02-28),"912210101","dhruv@gmail.com"),
		new User(2,"dhruvv","male",new java.util.Date(1998-03-28),"9145210101","dhruv1@gmail.com")).collect(Collectors.toList()));	
        assertEquals(2, service.getAllUser().size());
}


@Test
public void saveUserTest()
{
	User user = new User(1,"dhruv","male",new java.util.Date(1998-02-28),"912210101","dhruv@gmail.com");
	when(repository.save(user)).thenReturn(user);
        assertEquals(user, service.saveUser(user));
}

@Test
public void deleteUserTest()
{User user = new User(1,"dhruv","male",new java.util.Date(1998-02-28),"912210101","dhruv@gmail.com");
 service.Delete(user.getId());
 verify(repository, times(1)).deleteById(user.getId());
}


@Test
public void findByUserNameTest() {
String userName="dhruv";
User user=new User(1,"dhruv","male",new java.util.Date(1998-02-28),"912210101","dhruv@gmail.com");
when(repository.findByUserName(userName)).thenReturn(Optional.of(user));
	assertEquals(user,service.getByUserName(userName).get());
	
}

}