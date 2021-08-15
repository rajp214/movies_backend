package com.dvstore.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dvstore.model.Movies;
import com.dvstore.model.TvShows;
import com.dvstore.model.User;

public interface MoviesRepository extends MongoRepository<Movies, String> {
	List<Movies> findAllByIsFeatured(int featured);
}