package domain;

import javax.persistence.Entity;

@Entity
public class RegistratedCustomer extends Customer {

	private String registrated;

	public RegistratedCustomer(String registrated) {
		super();
		this.registrated = registrated;
	}

	public String getRegistrated() {
		return registrated;
	}

	public void setRegistrated(String registrated) {
		this.registrated = registrated;
	}
	
	

}
