package com.btg.PetShopTest.modules.customers.usecase;

import com.btg.PetShopTest.infra.exception.ClientBadRequest;
import com.btg.PetShopTest.infra.exception.PasswordValidationError;
import com.btg.PetShopTest.modules.customers.dto.CustomerResponse;
import com.btg.PetShopTest.modules.customers.entity.Customer;
import com.btg.PetShopTest.modules.customers.repository.CustomerRepository;
import com.btg.PetShopTest.utils.CustomerConvert;
import com.btg.PetShopTest.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterCustomer {
    private final CustomerRepository repository;
    private final PasswordEncoder passwordEncoder;

    public CustomerResponse execute(Customer customer) throws Exception {
        if(!Validator.name(customer.getName()))
            throw new Exception("length must be between 3 and 35");

        if(!Validator.passwordValidate(customer.getPassword()))
            throw new PasswordValidationError("Senha deve seguir o padrão");

        checkEmailAvailability(customer.getEmail());

        String encodePassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodePassword);

        repository.save(customer);
        return CustomerConvert.toResponse(customer);
    }

    private void checkEmailAvailability(String email) throws Exception {
        if(!Validator.emailValidate(email))
            throw new Exception("must be a well-formed email address");

        Customer emailExist = repository.findByEmail(email);
        if  (emailExist != null ) {
            throw new ClientBadRequest("Email já está em uso");
        }
    }
}
