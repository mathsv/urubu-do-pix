package com.urubuDoPix.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal amount, Long sender_id, Long receiver_id) {
}
