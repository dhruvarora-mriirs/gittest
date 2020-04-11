import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.CustomResponse;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceUnitTest {
	
@Autowired
UserService userService;
	


        @Test
        public void testGetUserById() {

               List<User> mangasByTitle = userService.getAllUser();

               assertThat(mangasByTitle).isNotNull().isNotEmpty();

        }
   

        
        @Test
        public void testGetMaleUser()
        {
        	List<User> maleUsers= userService.getMaleUser();
        	assertThat(maleUsers).isNotEmpty();
        }
}