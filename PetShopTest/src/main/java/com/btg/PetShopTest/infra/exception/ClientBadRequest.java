package com.btg.PetShopTest.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientBadRequest extends Exception{
    private String message;
}
