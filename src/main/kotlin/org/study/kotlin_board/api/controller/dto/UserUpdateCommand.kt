package org.study.kotlin_board.api.controller.dto

data class UserUpdateCommand(
    val email: String,
    val name: String,
    val password: String? = null,
)