package com.btg.PetShopTest.modules.customers.repository;

import com.btg.PetShopTest.modules.customers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query(value = "SELECT * FROM customers WHERE email = :email", nativeQuery = true)
    Customer findByEmail(String email);

    @Query(value = "SELECT * FROM customers WHERE id_transaction = :id", nativeQuery = true)
    Customer findByIdTransaction(String id);

    @Query(value = "SELECT * FROM customers WHERE name LIKE '%s:name%' = :name", nativeQuery = true)
    List<Customer> findByName(String name);
}
