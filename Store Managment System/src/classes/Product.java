package classes;

import java.io.Serializable;


public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int storePrice;
	private int customerPrice;
	private Customer customer;

	public Product(String name, int storePrice, int customerPrice, Customer customer) {
		this.name = name;
		this.storePrice = storePrice;
		this.customerPrice = customerPrice;
		this.customer = new Customer(customer);
	}

	public Product(Product other) {
		this.name = other.name;
		this.customerPrice = other.customerPrice;
		this.storePrice = other.storePrice;
		this.customer = new Customer(other.customer);
	}

	public Customer getCustomer() {
		return customer;
	}

	public int getStorePrice() {
		return storePrice;
	}

	public void setStorePrice(int storePrice) {
		this.storePrice = storePrice;
	}

	public int getCustomerPrice() {
		return customerPrice;
	}

	public void setCustomerPrice(int customerPrice) {
		this.customerPrice = customerPrice;
	}

	@Override
	public String toString() {
		return " Product name: " + name + " Store Price: " + storePrice + " Customer Price: " + customerPrice + "\n"
				+ customer.toString() + "\n";
	}

	public String getName() {
		return name;
	}
}
