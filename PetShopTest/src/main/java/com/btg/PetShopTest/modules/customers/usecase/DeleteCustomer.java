package com.btg.PetShopTest.modules.customers.usecase;

import com.btg.PetShopTest.infra.exception.ClientBadRequest;
import com.btg.PetShopTest.modules.customers.entity.Customer;
import com.btg.PetShopTest.modules.customers.repository.CustomerRepository;
import com.mysql.cj.xdevapi.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCustomer {
    private final CustomerRepository repository;

    public void execute(String id) throws Exception {
        Customer customer = repository.findByIdTransaction(id);
        if (customer == null) throw new ClientBadRequest("Customer not found with ID: " + id);
        repository.delete(customer);
    }
}
