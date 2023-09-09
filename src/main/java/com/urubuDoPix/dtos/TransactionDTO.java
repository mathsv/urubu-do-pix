package com.urubuDoPix.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDTO(UUID sender_id, UUID receiver_id, BigDecimal amount) {
}
