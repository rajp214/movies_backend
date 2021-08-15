package com.dvstore.cotroller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dvstore.dao.UsersRepository;
import com.dvstore.databean.JwtResponse;
import com.dvstore.databean.LoginRequest;
import com.dvstore.model.Movies;
import com.dvstore.model.TvShows;
import com.dvstore.model.User;
import com.dvstore.model.UserDetailsImpl;
import com.dvstore.utility.JwtUtils;
import com.dvstore.services.MoviesServices;
import com.dvstore.services.UserServices;
@RestController
//@CrossOrigin(origins = "http://localhost:3300",allowedHeaders = "*")
public class MovieController {

		@Autowired
	MoviesServices moviesServices;
	
	
	@GetMapping("/allmovies")
	public ResponseEntity<?> allMovies() {
		List<Movies> list = moviesServices.getAllMovies();
	return ResponseEntity.ok().body(list);	
	}
	

	@GetMapping("/allfeaturedmovies")
	public ResponseEntity<?> allFeaturedMovies() {
		List<Movies> list = moviesServices.getAllMoviesFeatured();
	return ResponseEntity.ok().body(list);	
	}
	
	@GetMapping("/getmovie/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable String id) {
		 Optional<Movies>  list = moviesServices.getMoviesById(id);
	return ResponseEntity.ok().body(list);	
	}
	
}
