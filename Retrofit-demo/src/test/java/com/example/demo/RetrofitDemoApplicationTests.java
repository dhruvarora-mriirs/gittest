package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mock.http.client.MockClientHttpResponse;
import org.springframework.mock.web.server.MockWebSession;

import com.example.demo.models.UserApiResponse;
import com.example.demo.service.UserService;

import okhttp3.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

@SpringBootTest
class RetrofitDemoApplicationTests {

	@Test
	void contextLoads() {
		
	}

	
	 private AnnotationConfigApplicationContext context;

	 
	 
	 @Test
	    public void testRetrofitAutoConfigured() {
	        Retrofit retrofit = context.getBean(Retrofit.class);

	        assertThat(retrofit).isNotNull();
	        assertThat(retrofit.baseUrl().toString()).isEqualTo("http://localhost/");
	    }
//	 @Test
//	 public void test() throws IOException {
//	     MockWebSession mockWebServer = new MockWebSession();
//
//	     Retrofit retrofit = new Retrofit.Builder()
//	             .baseUrl(mockWebServer.url("").toString())
//	           
//	             .build();
//
//	 
//	     mockWebServer.enqueue(new MockClientHttpResponse().setBody("your json body"));
//
//	     UserService service = retrofit.create(UserService.class);
//
//	     //With your service created you can now call its method that should 
//	     //consume the MockResponse above. You can then use the desired
//	     //assertion to check if the result is as expected. For example:
//	     Call call = service.getYourObject();
//	     assertTrue(call.execute() != null);
//
//	     //Finish web server
//	     mockWebServer.shutdown();
//	 }
	 
}
