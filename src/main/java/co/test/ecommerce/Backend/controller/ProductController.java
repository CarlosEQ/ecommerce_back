package co.test.ecommerce.Backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import co.test.ecommerce.Backend.model.Product;
import co.test.ecommerce.Backend.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@PersistenceContext
	private EntityManager em;

	/**
	 * Get a list of products
	 * 
	 * @return list of products
	 */
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	/**
	 * Allow get an product by his id
	 * 
	 * @param productId product's id
	 * @return the product if it is, else an exception
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getUserById(@PathVariable(value = "id") Long productId)
			throws ResourceNotFoundException {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
		return ResponseEntity.ok().body(product);
	}

	/**
	 * Get a list of products by his category id
	 * @param id_category category's id
	 * @return list of products
	 */
	@GetMapping("/products/category/{id}")
	public List<Product> findProductByCategoryId(@PathVariable(value = "id") Long id_category) {

		List products = (List<Product>) productRepository.findProductByCategoryId(id_category);

		return products;
	}

	/**
	 * Allow to save a product in the database
	 * 
	 * @param product accesory to save
	 * @return the storaged product
	 */
	@PostMapping("/products")
	public Product createProduct(@Valid @RequestBody Product product) {

		return productRepository.save(product);
	}

	/**
	 * Allow to update a product by his id
	 * 
	 * @param producId product's id
	 * @param productDetails the updated product
	 * @return response of the product
	 * @throws ResourceNotFoundException if the product is not storaged
	 */
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateEmployee(@PathVariable(value = "id") Long productId,
			@Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));

		product.setDescription(productDetails.getDescription());
		product.setName(productDetails.getName());
		product.setPrice(productDetails.getPrice());
		product.setWeight(productDetails.getWeight());
		final Product updatedProduct = productRepository.save(product);
		return ResponseEntity.ok(updatedProduct);
	}

	/**
	 * Allow to delete a product by his id
	 * 
	 * @param productId accesory's id
	 * @return response of the product
	 * @throws ResourceNotFoundException if the product is not storaged
	 */
	@DeleteMapping("/products/{id}")
	public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId)
			throws ResourceNotFoundException {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));

		productRepository.delete(product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
