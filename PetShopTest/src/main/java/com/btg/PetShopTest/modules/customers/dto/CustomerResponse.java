package com.btg.PetShopTest.modules.customers.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private String idTransaction;
    private String name;
    private String email;


}
