package Iset.com.ecommerce;

import Iset.com.ecommerce.customer.Customer;
import Iset.com.ecommerce.customer.CustomerRepository;
import Iset.com.ecommerce.customer.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Création d'un nouvel Address
		Address address = new Address("Main Street", "101", "12345");

		// Création d'un nouveau customer avec un Address
		Customer customer1 = new Customer();
		customer1.setFirstname("John");
		customer1.setLastname("Doe");
		customer1.setEmail("john.doe@example.com");
		customer1.setAddress(address);

		// Sauvegarde du customer dans la base de données
		customerRepository.save(customer1);

	}
}
