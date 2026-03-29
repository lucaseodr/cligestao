package com.cligestao.model

import com.cligestao.exception.BusinessException
import java.math.BigDecimal

data class OrderItem(
    val productId: Long,
    val quantity: Int,
    val unitPrice: BigDecimal,
) {
    init {
        if (quantity <= 0) {
            throw BusinessException("A quantidade do item deve ser maior que zero")
        }

        if (unitPrice <= BigDecimal.ZERO) {
            throw BusinessException("O preço unitario deve ser maior que zero")
        }
    }

    fun subTotal(): BigDecimal {
        return unitPrice.multiply(quantity.toBigDecimal())
    }
}