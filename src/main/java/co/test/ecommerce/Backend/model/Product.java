package co.test.ecommerce.Backend.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
 
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	private String name;
	private String description;
	private double weight, price;	
	private long id_category;
	@Lob
	private String picture;
 
    public Product() {
        super();
    }
    public Product(String name, long id_category) {
        this.name = name;
        this.id_category = id_category;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
		return description;
	}
    public void setDescription(String description) {
		this.description = description;
	}
    public double getWeight() {
		return weight;
	}
    
    public void setWeight(double weight) {
		this.weight = weight;
	}
    
    public double getPrice() {
		return price;
	}
    
    public void setPrice(double price) {
		this.price = price;
	}
    
    public String getPicture() {
		return picture;
	}
    public void setPicture(String picture) {
		this.picture = picture;
	}
    
    public long getId_category() {
		return id_category;
	}
    public void setId_category(long id_category) {
		this.id_category = id_category;
	}
}


