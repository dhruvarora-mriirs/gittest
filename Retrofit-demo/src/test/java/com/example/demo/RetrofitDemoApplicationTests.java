package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.mock.http.client.MockClientHttpResponse;
import org.springframework.mock.web.server.MockWebSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.gateway.ApiGateway;
import com.example.demo.models.User;
import com.example.demo.models.UserApiResponse;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceInterface;

import io.restassured.RestAssured;
import io.restassured.http.Method;

import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import retrofit2.Call;

import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class RetrofitDemoApplicationTests {

	@Test
	void contextLoads() {
		
	}
	@Autowired
	private MockMvc mockMvc;

	
	@Autowired
private ApiGateway gateway;	
@MockBean
UserServiceInterface apicall;


@Test
public void getRepositoryTest() throws IOException
{
	User user=new User(1,"dhruv","arora","dhruv@gmail.com");
	UserApiResponse res=new UserApiResponse();
	res.setUser(user);
	
////
//	 Call<UserApiResponse> callSync ;
//			when(apicall.getUser(1)).thenReturn("retrofit2.OkHttpCall@476d7de8");	
//	 retrofit2.Response<UserApiResponse> response = callSync.execute();
//	 
//		when(callSync.execute()).thenCallRealMethod();
 
	//assertThat(response.body().equals(res.getUser()));
}

@Test
void getUserDetailsTest() {

    RestAssured.baseURI = "https://reqres.in/api/user/";
    RequestSpecification httpRequest = RestAssured.given();


    Response response = httpRequest.request(Method.GET, "2");

    int statusCode = response.getStatusCode();
    Assert.assertEquals(statusCode, 200);
} 

@Test
public void unitTest() throws Exception
{
mockMvc.perform(get("/api/v1/user/1")).andExpect(status().isOk())
.andExpect(content().contentType(MediaType.APPLICATION_JSON))
.andExpect(jsonPath("$.id", is(1))).andDo(print());	
	
}


	
}

















//
//@Test
//public void test() throws IOException {
//    MockWebSession mockWebServer = new MockWebSession();
//
//    Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl(mockWebServer.url("https://reqres.in/"))
//          
//            .build();
//
//
//    mockWebServer.enqueue(new MockClientHttpResponse().setBody("your json body"));
//
//    UserService service = retrofit.create(UserService.class);
//
//    Call call = service.getYourObject();
//    assertTrue(call.execute() != null);
//
//    //Finish web server
//    mockWebServer.shutdown();
//}
