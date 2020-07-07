package app.service;

import app.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceImpl implements ICustomerService {
	private static CustomerServiceImpl instance;
	private static Map<Long, Customer> customers;
	private static Long id = 3L;

	static {
		customers = new HashMap<>();
		Customer customer = new Customer(1L,"Toan", "toan@gmail.com", "vinh phuc");
		Customer customer2 = new Customer(2L,"Nam", "nam@gmail.com", "vinh phuc");

		customers.put(1L, customer);
		customers.put(2L, customer2);
	}

	public CustomerServiceImpl(){
	}

	@Override
	public List<Customer> findAll() {
		List<Customer> list = new ArrayList<>(customers.values());
		return list;
	}

	@Override
	public Customer findById(Long id) {
		return customers.get(id);
	}

	@Override
	public void save(Customer customer) {
		if (customer.getId() == null || customer.getId() == 0L){
			Long newId = id++;
			customer.setId(newId);
			customers.put(newId, customer);
		}else{
			customers.put(customer.getId(), customer);
		}
	}

	@Override
	public void delete(Long id) {
		customers.remove(id);
	}
}
