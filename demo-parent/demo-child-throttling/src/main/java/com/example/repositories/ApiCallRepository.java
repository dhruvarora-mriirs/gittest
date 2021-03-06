package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ApiCall;

import java.util.List;

@Repository
public interface ApiCallRepository extends CrudRepository<ApiCall, String> {

    Long countAllByKeyy(String apiKey);

    List<ApiCall> findAllByTimeStampGreaterThanAndTimeStampLessThanAndKeyy(Long after, Long before, String apiKey);

}