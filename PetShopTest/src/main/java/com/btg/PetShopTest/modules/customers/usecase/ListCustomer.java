package com.btg.PetShopTest.modules.customers.usecase;

import com.btg.PetShopTest.modules.customers.dto.CustomerResponse;
import com.btg.PetShopTest.modules.customers.repository.CustomerRepository;
import com.btg.PetShopTest.utils.CustomerConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListCustomer {
    private final CustomerRepository repository;

    public List<CustomerResponse> execute() {
        return CustomerConvert.toListResponse(repository.findAll());
    }
}
