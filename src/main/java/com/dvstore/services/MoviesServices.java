package com.dvstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dvstore.dao.MoviesRepository;
import com.dvstore.dao.TvShowsRepository;
import com.dvstore.dao.UsersRepository;
import com.dvstore.model.Movies;
import com.dvstore.model.TvShows;
import com.dvstore.model.User;

@Service
public class MoviesServices {

	@Autowired
	MoviesRepository moviesRepository;
	
	public List<Movies> getAllMovies(){
		return moviesRepository.findAll();
	}
	
	public List<Movies> getAllMoviesFeatured(){
		return moviesRepository.findAllByIsFeatured(1);
	}
	
	public Optional<Movies> getMoviesById(String id){
		return moviesRepository.findById(id);
	}
	
}
