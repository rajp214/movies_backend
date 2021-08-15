package com.dvstore.cotroller;

import java.util.List;
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
import com.dvstore.model.User;
import com.dvstore.model.UserDetailsImpl;
import com.dvstore.utility.JwtUtils;
import com.dvstore.services.UserServices;

@RestController
@CrossOrigin(origins = "http://localhost:3300")
public class RegisterController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsersRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserServices userServices;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/employees/{email}")
	public ResponseEntity<User> Register(@PathVariable String email) {
		 User user = userRepository.findByEmail(email);
	return ResponseEntity.ok().body(user);	
	}
	
	 
	@GetMapping("/getpassword/{email}")
	public ResponseEntity<String> getPassword(@PathVariable String email) {
		return ResponseEntity.ok().body(passwordEncoder.encode(email));	
	}
	
	@PostMapping("/saveuser")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		System.out.println("sdvdc");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		 userServices.saveUser(user);
	return ResponseEntity.ok().body("saved successfully");	
	}
	
	@PostMapping("/auth/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		try {
			System.out.println("ekjdsnc");
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
			
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());

			return ResponseEntity.ok(new JwtResponse(jwt, 
													 userDetails.getId(), 
													 userDetails.getUsername(), 
													 userDetails.getEmail(), 
													 roles));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.badRequest().body("sign in failed");
		}
		
	}
	
}
