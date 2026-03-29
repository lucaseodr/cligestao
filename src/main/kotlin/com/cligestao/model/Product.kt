package com.cligestao.model

import com.cligestao.exception.BusinessException
import com.cligestao.exception.InsufficientStockException
import java.math.BigDecimal

data class Product(
    val id: Long,
    val name: String,
    val price: BigDecimal,
    var stock: Int
) {
    init {
        if (name.isBlank()) {
            throw BusinessException("Nome do produto nao pode ser vazio")
        }

        if (price <= BigDecimal.ZERO) {
            throw BusinessException("Preço do produto deve ser maior que zero")
        }

        if (stock < 0) {
            throw BusinessException("Estoque nao pode ser negativo")
        }
    }

    fun removeStock(quantity: Int) {
        if (quantity <= 0) {
            throw BusinessException("Quantidade para remover do estoque deve ser maior que zero")
        }

        if (quantity > stock) {
            throw InsufficientStockException(
                "Estoque insuficiente para o produto '$name'. Estoque atual: $stock, quantidade solicitada: $quantity"
            )
        }

        stock -= quantity
    }
}