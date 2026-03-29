package com.cligestao.model

import com.cligestao.exception.BusinessException

data class Customer(
    val id: Long,
    val name: String,
    val email: String
) {
    init {
        if (name.isBlank()) {
            throw BusinessException("Nome do cliente nao pode ser vazio")
        }

        if (email.isBlank() || !email.contains("@")) {
            throw BusinessException("Email do cliente e invalido")
        }
    }
}