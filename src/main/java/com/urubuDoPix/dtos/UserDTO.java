package com.urubuDoPix.dtos;

import com.urubuDoPix.enums.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstname, String lastname, BigDecimal balance, String document, String email, String password, UserType userType) {
}
