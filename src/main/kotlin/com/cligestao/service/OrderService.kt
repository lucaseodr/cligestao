package com.cligestao.service

import com.cligestao.exception.BusinessException
import com.cligestao.exception.NotFoundException
import com.cligestao.model.Customer
import com.cligestao.model.Order
import com.cligestao.model.OrderItem
import com.cligestao.model.Product

class OrderService(
    private val customers: List<Customer>,
    private val products: List<Product>
) {
    private var nextOrderId: Long = 1

    fun createOrder(
        customerId: Long,
        itemsRequest: List<CreateOrderItemRequest>
    ): Order {
        if (itemsRequest.isEmpty()) {
            throw BusinessException("O pedido precisa ter pelo menos um item")
        }

        val customer = findCustomerById(customerId)

        val orderItems = itemsRequest.map { itemRequest ->
            val product = findProductById(itemRequest.productId)

            product.removeStock(itemRequest.quantity)

            OrderItem(
                productId = product.id,
                quantity = itemRequest.quantity,
                unitPrice = product.price
            )
        }

        val order = Order(
            id = nextOrderId++,
            customerId = customer.id,
            items = orderItems
        )

        return order
    }

    private fun findCustomerById(customerId: Long): Customer {
        return customers.find { customer ->
            customer.id == customerId
        } ?: throw NotFoundException("Cliente com id $customerId nao encontrado")
    }

    private fun findProductById(productId: Long): Product {
        return products.find { product ->
            product.id == productId
        } ?: throw NotFoundException("Produto com id $productId nao encontrado")
    }
}