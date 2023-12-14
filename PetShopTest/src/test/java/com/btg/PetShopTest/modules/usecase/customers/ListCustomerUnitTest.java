package com.btg.PetShopTest.modules.usecase.customers;

import com.btg.PetShopTest.modules.customers.dto.CustomerResponse;
import com.btg.PetShopTest.modules.customers.entity.Customer;
import com.btg.PetShopTest.modules.customers.repository.CustomerRepository;
import com.btg.PetShopTest.modules.customers.usecase.ListCustomer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class ListCustomerUnitTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private ListCustomer listCustomer;


    @Test
    public void testExecute() {

        Customer customer1 = new Customer();
        customer1.setIdTransaction("1");
        customer1.setName("Ana");
        customer1.setEmail("ana@example.com");

        Customer customer2 = new Customer();
        customer2.setIdTransaction("2");
        customer2.setName("Joana");
        customer2.setEmail("joana@example.com");

        List<Customer> customers = Arrays.asList(customer1, customer2);

        Mockito.when(repository.findAll()).thenReturn(customers);

        List<CustomerResponse> result = listCustomer.execute();

        List<CustomerResponse> expected = Arrays.asList(
                new CustomerResponse("1", "Ana", "ana@example.com"),
                new CustomerResponse("2", "Joana", "joana@example.com")
        );

        assertEquals(expected, result);
    }
}
