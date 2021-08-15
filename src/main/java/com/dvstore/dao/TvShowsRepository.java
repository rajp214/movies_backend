package com.dvstore.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dvstore.model.TvShows;
import com.dvstore.model.User;

public interface TvShowsRepository extends MongoRepository<TvShows, String> {

	 List<TvShows> findAllByIsFeatured(int featured);
}