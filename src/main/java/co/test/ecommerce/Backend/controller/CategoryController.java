package co.test.ecommerce.Backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
import co.test.ecommerce.Backend.model.Category;
import co.test.ecommerce.Backend.model.Product;
import co.test.ecommerce.Backend.repository.CategoryRepository;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;
	@PersistenceContext
	   private EntityManager em;

	/**
	 * Get a list of the categories storaged
	 * 
	 * @return list of categories
	 */
	@RequestMapping("/categories")
	public List getAllCategories() {
		List lista = categoryRepository.findAll();
		return lista;
	}

	/**
	 * Allow get a category by his id
	 * 
	 * @param categoryId accesory's id
	 * @return the category if it is, else an exception
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/categories/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long categoryId)
			throws ResourceNotFoundException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));
		return ResponseEntity.ok().body(category);
	}
	

	/**
	 * Allow to save a category in the database
	 * 
	 * @param category accesory to save
	 * @return the storaged category
	 */
	@PostMapping("/categories")
	public Category createCategory(@Valid @RequestBody Category category) {
		return categoryRepository.save(category);
	}

	/**
	 * Allow to update a category by his id
	 * 
	 * @param categoryyId      category's id
	 * @param categoryDetails the updated category
	 * @return response of the category
	 * @throws ResourceNotFoundException if the category is not storaged
	 */
	@PutMapping("/categories/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long categoryId,
			@Valid @RequestBody Category categoryDetails) throws ResourceNotFoundException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));

		category.setName(categoryDetails.getName());
		final Category updatedCategory = categoryRepository.save(category);
		return ResponseEntity.ok(updatedCategory);
	}

	/**
	 * Allow to delete a category by her id
	 * 
	 * @param categoryId category's id
	 * @return response of the category
	 * @throws ResourceNotFoundException if the category is not storaged
	 */
	@DeleteMapping("/categories/{id}")
	public Map<String, Boolean> deleteCategory(@PathVariable(value = "id") Long categoryId)
			throws ResourceNotFoundException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));

		categoryRepository.delete(category);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}