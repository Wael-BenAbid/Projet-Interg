package Iset.com.ecommerce;

import Iset.com.ecommerce.category.CategoryRepository;
import Iset.com.ecommerce.product.Product;
import Iset.com.ecommerce.category.Category;
import Iset.com.ecommerce.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class ProductApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {  // Implémentation de la méthode run
		// Créer des catégories
		Category keyboardsCategory = new Category();
		keyboardsCategory.setName("Keyboards");
		keyboardsCategory.setDescription("Computer Keyboards");
		categoryRepository.save(keyboardsCategory);

		Category monitorsCategory = new Category();
		monitorsCategory.setName("Monitors");
		monitorsCategory.setDescription("Computer Monitors");
		categoryRepository.save(monitorsCategory);

		Category screensCategory = new Category();
		screensCategory.setName("Screens");
		screensCategory.setDescription("Display Screens");
		categoryRepository.save(screensCategory);

		Category miceCategory = new Category();
		miceCategory.setName("Mice");
		miceCategory.setDescription("Computer Mice");
		categoryRepository.save(miceCategory);

		Category accessoriesCategory = new Category();
		accessoriesCategory.setName("Accessories");
		accessoriesCategory.setDescription("Computer Accessories");
		categoryRepository.save(accessoriesCategory);

		// Ajouter des produits dans la catégorie "Keyboards"
		addProduct("Mechanical Keyboard 1", "Mechanical keyboard with RGB lighting", 10, new BigDecimal("99.99"), keyboardsCategory);
		addProduct("Wireless Compact Keyboard 1", "Wireless compact keyboard", 15, new BigDecimal("79.99"), keyboardsCategory);
		addProduct("Gaming Keyboard 1", "Backlit gaming keyboard with customizable keys", 20, new BigDecimal("129.99"), keyboardsCategory);
		addProduct("Ergonomic Keyboard 1", "Mechanical keyboard with wrist rest", 25, new BigDecimal("109.99"), keyboardsCategory);
		addProduct("Wireless Combo 1", "Wireless keyboard and mouse combo", 18, new BigDecimal("69.99"), keyboardsCategory);

		// Ajouter des produits dans la catégorie "Monitors"
		addProduct("4K Monitor 1", "27-inch IPS monitor with 4K resolution", 30, new BigDecimal("399.99"), monitorsCategory);
		addProduct("Ultra-wide Gaming Monitor 1", "Ultra-wide gaming monitor with HDR support", 25, new BigDecimal("499.99"), monitorsCategory);
		addProduct("Office Monitor 1", "24-inch LED monitor for office use", 22, new BigDecimal("179.99"), monitorsCategory);
		addProduct("Curved Monitor 1", "32-inch curved monitor with AMD FreeSync", 28, new BigDecimal("329.99"), monitorsCategory);
		addProduct("Portable Monitor 1", "Portable USB-C monitor for laptops", 35, new BigDecimal("249.99"), monitorsCategory);

		// Ajouter des produits dans la catégorie "Screens"
		addProduct("Curved OLED Gaming Screen 1", "Curved OLED gaming screen with 240Hz refresh rate", 15, new BigDecimal("799.99"), screensCategory);
		addProduct("QLED Monitor 1", "Flat QLED monitor with 1440p resolution", 18, new BigDecimal("599.99"), screensCategory);
		addProduct("Touch Screen Display 1", "27-inch touch screen display for creative work", 22, new BigDecimal("699.99"), screensCategory);
		addProduct("Ultra-slim 4K HDR Display 1", "Ultra-slim 4K HDR display for multimedia", 20, new BigDecimal("449.99"), screensCategory);
		addProduct("Gaming Projector 1", "Gaming projector with low input lag", 25, new BigDecimal("899.99"), screensCategory);

		// Ajouter des produits dans la catégorie "Mice"
		addProduct("RGB Gaming Mouse 1", "Wireless gaming mouse with customizable RGB lighting", 30, new BigDecimal("59.99"), miceCategory);
		addProduct("Ergonomic Wired Mouse 1", "Ergonomic wired mouse for productivity", 28, new BigDecimal("29.99"), miceCategory);
		addProduct("Ambidextrous Gaming Mouse 1", "Ambidextrous gaming mouse with high DPI", 32, new BigDecimal("69.99"), miceCategory);
		addProduct("Travel Mouse 1", "Travel-sized compact mouse for laptops", 26, new BigDecimal("19.99"), miceCategory);
		addProduct("Vertical Ergonomic Mouse 1", "Vertical ergonomic mouse for reduced strain", 35, new BigDecimal("39.99"), miceCategory);

		// Ajouter des produits dans la catégorie "Accessories"
		addProduct("Adjustable Laptop Stand 1", "Adjustable laptop stand with cooling fan", 25, new BigDecimal("34.99"), accessoriesCategory);
		addProduct("Wireless Charging Pad 1", "Wireless charging pad for smartphones", 20, new BigDecimal("24.99"), accessoriesCategory);
		addProduct("RGB Headset Stand 1", "Gaming headset stand with RGB lighting", 28, new BigDecimal("49.99"), accessoriesCategory);
		addProduct("Bluetooth Keypad 1", "Bluetooth mechanical keypad for tablets", 22, new BigDecimal("39.99"), accessoriesCategory);
		addProduct("External Hard Drive Enclosure 1", "External hard drive enclosure with USB-C", 30, new BigDecimal("29.99"), accessoriesCategory);

		System.out.println("Categories and Products have been created successfully!");
	}

	// Méthode utilitaire pour ajouter des produits
	private void addProduct(String name, String description, int quantity, BigDecimal price, Category category) {
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setAvailableQuantity(quantity);
		product.setPrice(price);
		product.setCategory(category);
		productRepository.save(product);
	}
}
