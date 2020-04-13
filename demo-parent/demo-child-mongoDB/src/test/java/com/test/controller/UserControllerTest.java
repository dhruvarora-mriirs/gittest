package com.test.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.MongoDbApplication;
import com.example.controller.UserController;
import com.example.model.User;
import com.example.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value=UserController.class)
@ContextConfiguration(classes=MongoDbApplication.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@InjectMocks
	private UserController controller;
	
	User user = new User(1,"dhruv","male",new java.util.Date(1998-02-28),"912210101","dhruv@gmail.com");

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
 


	@Test 
	public void getAllUsers() throws Exception
	{
		
		Mockito.when(userService.getAllUsers(0,10,"id")).thenReturn(Stream.of(user).collect(Collectors.toList()));
	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/user").accept(MediaType.APPLICATION_JSON);
	
	MvcResult result =mockMvc.perform(requestBuilder).andReturn();
    String expected = "[{id:1,userName:\"dhruv\",gender:\"male\",dob:\"1970-01-01T00:00:01.968+0000\",phone:\"912210101\",emailId:\"dhruv@gmail.com\"}]";
	
	JSONAssert.assertEquals( expected, result.getResponse().getContentAsString(),false);
	
	}
	
	  @Test
	    public void findByUserName() throws Exception {
		 
	 
		  when(userService.getByUserName(user.getUserName())).thenReturn(Optional.of(user));
	 
	        mockMvc.perform(get("/api/v1/user/username/{userName}",user.getUserName()))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$.id", is(1)))
	                .andExpect(jsonPath("$.userName", is("dhruv")))
	                .andExpect(jsonPath("$.gender",is("male")))
	                .andExpect(jsonPath("$.dob",is("1970-01-01T00:00:01.968+0000")))
	                .andExpect(jsonPath("$.phone",is("912210101")))
	                .andExpect(jsonPath("$.emailId", is("dhruv@gmail.com")))
	                .andDo(print());
	      
	        verify(userService, times(2)).getByUserName(user.getUserName());
	        verifyNoMoreInteractions(userService);
	    }
	

@Test
public void addNewUserTest() throws Exception{
	
//	  String json = "{id:1,userName:\"dhruv\",gender:\"male\",dob:\"1970-01-01T00:00:01.968+0000\",phone:\"912210101\",emailId:\"dhruv@gmail.com\"}";
	User user1 = new User(2,"dhruv","male",new java.util.Date(1998-02-28),"912210101","dhruv@gmail.com"); 
	
    
       
	  when(userService.saveUser(user1)).thenReturn(user1);  
    
	  MvcResult result= mockMvc.perform( MockMvcRequestBuilders
		      .post("/api/v1/user")
		     .content(mapToJson(user1))
		      .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
		      .accept(MediaType.APPLICATION_JSON))
		      .andExpect(status().isCreated())
		      .andDo(print())
		      .andReturn()      
		      ;

		}
private String mapToJson(Object obj) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(obj);
 }

 private <T> T mapFromJson(String json, Class<T> clazz)
    throws JsonParseException, JsonMappingException, IOException {
    
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(json, clazz);
 }




@Test
public void deleteEmployeeAPI() throws Exception 
{	 
	User user1 = new User(1,"dhruv","male",new java.util.Date(1998-02-28),"912210101","dhruv@gmail.com");
	
  mockMvc.perform( MockMvcRequestBuilders.delete("/api/v1/user/{id}",1))
        .andExpect(status().isAccepted())
        .andDo(print());
}



}
	
	


