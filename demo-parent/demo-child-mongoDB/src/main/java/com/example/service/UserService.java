package com.example.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.model.AggregatorResponse;
import com.example.model.CustomResponse;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.mongodb.client.result.UpdateResult;

@Service
public class UserService {

	@Autowired
	UserRepository repo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	public List<User> getMaleUser()
	{
		
		Query query=new Query()
				.addCriteria(Criteria.where("gender").is("male"))
				.with(Sort.by(Sort.Order.desc("userName")));
		
		return mongoTemplate.find(query, User.class); //sorted by userName in descending and where gender=male;
	
	}
	
	public List<CustomResponse> getNoOfUser()  //count based on custom group
	{
		Aggregation aggregate;
		
		aggregate = newAggregation(
				group("userName","gender").count().as("noOfUsers"),
				sort(Sort.Direction.DESC, "noOfUsers")
				);
	
	AggregationResults<CustomResponse> groupResults = mongoTemplate.aggregate(aggregate, User.class, CustomResponse.class);
	List<CustomResponse> result = groupResults.getMappedResults();
	return result;
	
	}
	
	public AggregatorResponse getMaxUserId()
	{
		GroupOperation sumUID = group("id").count().as("userCount");
		SortOperation sortByCount = sort(Direction.DESC, "userCount");
		GroupOperation groupFirstAndLast = group().first("_id").as("maxUID")
		  .first("userCount").as("maxUIDCount").last("_id").as("minUID")
		  .last("userCount").as("minUIDCount");
		 
		Aggregation aggregation = newAggregation(sumUID, sortByCount, groupFirstAndLast);
		 
		AggregationResults<AggregatorResponse> result = mongoTemplate
		  .aggregate(aggregation, User.class, AggregatorResponse.class);
		AggregatorResponse document= result.getUniqueMappedResult();
		return document;

	}
	
	public List<User> updateMulti(String userName,String gender)
	{
		Query query=new Query().addCriteria(Criteria.where("userName").is(userName));
		 Update update = new Update();
		 update.set("gender",gender);
		 
		
		 mongoTemplate.updateMulti(query,update,User.class);


			List<User> result = mongoTemplate.find(query,User.class);
		return result;
		
	}
	
	
	public List<User> getAllUser() {
		return repo.findAll();
	
	}


	public Optional<User> getUser(@Positive long id) {

		return repo.findById(id);

	}

	public User saveUser(User user) {
		
	
		return repo.save(user);

	}

	public void Delete(@Positive long id) {
		repo.deleteById(id);
	}

		
	public void updateUser(User requestuser, User dbUser) {
       
		User users = User.UserBuilder.createUser(requestuser, dbUser);
			repo.save(users);

	}

	
	public List<User> getAllUsers(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
 
        Page<User> pagedResult = repo.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<User>();
        }
	}
	
	public Optional<User> getByUserName(String userName) {
		
		return repo.findByUserName(userName);
	}
	
	
	
}
