package view;

import java.util.Arrays;
import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.Address;
import domain.Customer;
import domain.Pizza;
import domain.State;
import domain.enums.PizzaType;

public class JPAWithoutSpringApp {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = entityManagerFactory.createEntityManager();
		Pizza pizza = new Pizza();
		pizza.setName("Margo");
		pizza.setPrice(120.3);
		pizza.setType(PizzaType.Sea);

		Address address = new Address("UA", new State("STATE"));

		Customer customer = new Customer("Roman", Arrays.asList(address));

		customer.setPhones(new HashSet<>(Arrays.asList(new String[] { "123", "456" })));

		address.setCustomer(customer);
		try {
			em.getTransaction().begin();
			em.getTransaction().commit();
			customer = em.find(Customer.class, 1);
		} finally {
			em.close();
			entityManagerFactory.close();
		}
		System.out.println(customer.getPhones());
	}

}
