package refactoring.service;

import java.util.ArrayList;
import java.util.List;

import refactoring.Customer;

public class CustomerService {

	private static final CustomerService instance = new CustomerService();
	List<Customer> customers;

	private CustomerService() {
		customers = new ArrayList<>();
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public Customer getCustomer(Object customerId) {
		for (Customer customer : customers) {
			if (customer.getCustomerID().equals(customerId)) {
				return customer;
			}
		}
		return null;
	}

	public boolean isEmpty() {
		return customers.isEmpty();
	}

	public void remove(Customer customer) {
		customers.remove(customer);
	}

	public Customer get(int index) {
		return customers.get(index);
	}

	public int indexOf(Customer customer) {
		return customers.indexOf(customer);
	}

	public int size() {
		return customers.size();
	}

	public static CustomerService getInstance() {
		return instance;
	}

}
