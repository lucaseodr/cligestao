package com.cligestao.service

data class CreateOrderItemRequest(
    val productId: Long,
    val quantity: Int
)