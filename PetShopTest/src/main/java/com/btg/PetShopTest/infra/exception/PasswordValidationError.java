package com.btg.PetShopTest.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordValidationError extends Exception{
    private String message;
}
