package com.dvstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dvstore.dao.TvShowsRepository;
import com.dvstore.dao.UsersRepository;
import com.dvstore.model.TvShows;
import com.dvstore.model.User;

@Service
public class TvShowsServices {

	@Autowired
	TvShowsRepository tvShowsRepository;
	
	public List<TvShows> getAllTvShows(){
		return tvShowsRepository.findAll();
	}
	
	public List<TvShows> getAllTvShowsFeatured(){
		return tvShowsRepository.findAllByIsFeatured(1);
	}
	
	
	public Optional<TvShows> getTvShowById(String id){
		return tvShowsRepository.findById(id);
	}
}
