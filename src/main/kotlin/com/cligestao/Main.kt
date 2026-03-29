package com.cligestao

import com.cligestao.exception.BusinessException
import com.cligestao.exception.InsufficientStockException
import com.cligestao.exception.NotFoundException
import com.cligestao.model.Customer
import com.cligestao.model.Product
import com.cligestao.service.CreateOrderItemRequest
import com.cligestao.service.OrderService
import java.math.BigDecimal

fun main() {
    try {
        val customers = listOf(
            Customer(
                id = 1,
                name = "Lucas",
                email = "lucas@email.com"
            ),
            Customer(
                id = 2,
                name = "Tiago",
                email = "tiago@email.com"
            )
        )

        val products = listOf(
            Product(
                id = 1,
                name = "Notebook",
                price = BigDecimal("3500.00"),
                stock = 10
            ),
            Product(
                id = 2,
                name = "Mouse",
                price = BigDecimal("150.00"),
                stock = 5
            )
        )

        val orderService = OrderService(
            customers = customers,
            products = products
        )

        val order = orderService.createOrder(
            customerId = 1,
            itemsRequest = listOf(
                CreateOrderItemRequest(productId = 1, quantity = 1),
                CreateOrderItemRequest(productId = 2, quantity = 2)
            )
        )

        println("Pedido criado com sucesso:")
        println(order)
        println("Total do pedido: ${order.total()}")

        println("\nEstoque atualizado:")
        products.forEach { product ->
            println("${product.name}: ${product.stock}")
        }

    } catch (e: InsufficientStockException) {
        println("Erro de estoque: ${e.message}")
    } catch (e: NotFoundException) {
        println("Erro de busca: ${e.message}")
    } catch (e: BusinessException) {
        println("Erro de negócio: ${e.message}")
    } catch (e: Exception) {
        println("Erro inesperado: ${e.message}")
    }
}