package domain;

import java.util.List;

public class Order {
	private Long id;
	private static Long nextId = 0l;
	private Customer customer;
	private List<Pizza> listOfPizzas;

	public Order() {
		this.id = ++nextId;
	}

	public Order(Customer customer, List<Pizza> listOfPizzas) {
		this();
		this.customer = customer;
		this.listOfPizzas = listOfPizzas;
	}


	public Order(Long id, Customer customer, List<Pizza> listOfPizzas) {
		super();
		this.id = id;
		this.customer = customer;
		this.listOfPizzas = listOfPizzas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Pizza> getListOfPizzas() {
		return listOfPizzas;
	}

	public void setListOfPizzas(List<Pizza> listOfPizzas) {
		this.listOfPizzas = listOfPizzas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((listOfPizzas == null) ? 0 : listOfPizzas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (listOfPizzas == null) {
			if (other.listOfPizzas != null)
				return false;
		} else if (!listOfPizzas.equals(other.listOfPizzas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", listOfPizzas=" + listOfPizzas + "]";
	}

	
	
	

}
