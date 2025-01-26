package org.study.kotlin_board.api.controller.dto

data class UserCreateCommand(
    val email: String,
    val password: String,
    val name: String,
)
