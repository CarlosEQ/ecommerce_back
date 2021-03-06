package co.test.ecommerce.Backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.test.ecommerce.Backend.exception.ResourceNotFoundException;
import co.test.ecommerce.Backend.model.User;
import co.test.ecommerce.Backend.repository.UserRepository;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Get a list of users
	 * @return
	 */
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * get a user by his id
	 * @param userId user id
	 * @return the user if it is, else an exception
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		return ResponseEntity.ok().body(user);
	}

	
	/**
	 * Allow to save an user in the database
	 * 
	 * @param accesory user to save
	 * @return the storaged user
	 */
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		
		
		return userRepository.save(user);
	}

	/**
	 * Allow to update an user by his id
	 * 
	 * @param userId user's id
	 * @param userDetails the updated user
	 * @return response of the user
	 * @throws ResourceNotFoundException if the user is not storaged
	 */
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		user.setUser_name(userDetails.getUser_name());
		user.setEmail_user(userDetails.getEmail_user());
		user.setPassword(userDetails.getPassword());
		final User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	/**
	 * Allow to delete an user by his id
	 * @param userId user's id
	 * @return response of the user
	 * @throws ResourceNotFoundException if the user is not storaged
	 */
	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
