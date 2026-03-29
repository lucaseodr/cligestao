package com.cligestao.model

import com.cligestao.exception.BusinessException
import java.math.BigDecimal

data class Order(
    val id: Long,
    val customerId: Long,
    val items: List<OrderItem>,
    var status: OrderStatus = OrderStatus.CREATED
) {
    init {
        if (items.isEmpty()) {
            throw BusinessException("Pedido deve ter pelo menos um item")
        }
    }

    fun total(): BigDecimal {
        return items.fold(BigDecimal.ZERO) { acc, item ->
            acc.add(item.subTotal())
        }
    }

    fun canCancel(): Boolean {
        return when (status) {
            OrderStatus.CREATED -> true
            OrderStatus.PAID -> true
            OrderStatus.CANCELLED -> false
        }
    }

    fun cancel() {
        if (!canCancel()) {
            throw BusinessException("Pedido ja esta cancelado e nao pode ser cancelado novamente")
        }

        status = OrderStatus.CANCELLED
    }

    fun pay() {
        if (status == OrderStatus.CANCELLED) {
            throw BusinessException("Pedido cancelado nao pode ser pago")
        }

        if (status == OrderStatus.PAID) {
            throw BusinessException("Pedido já foi pago")
        }

        status = OrderStatus.PAID
    }
}