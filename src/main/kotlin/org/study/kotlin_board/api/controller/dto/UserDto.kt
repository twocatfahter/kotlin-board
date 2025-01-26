package org.study.kotlin_board.api.controller.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.study.kotlin_board.global.annotation.Password
import java.time.LocalDateTime

object UserDto {
    data class SignUpRequest(
        @field:Email
        @field:NotBlank
        val email: String,
        @field:Password
        val password: String,
        @field:NotBlank
        val name: String,
    ) {
        fun toCommand() =
            UserCreateCommand(
                email = email,
                password = password,
                name = name,
            )
    }

    data class Response(
        val id: Long,
        val email: String,
        val name: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime?,
    )

    data class LoginRequest(
        @field:Email
        val email: String,
        @field:NotBlank
        val password: String,
    )

    data class LoginResponse(
        val token: String,
    )

    data class UpdateRequest(
        @field:Email
        val email: String,
        @field:NotBlank
        val name: String,
        val password: String? = null,
    ) {
        fun toCommand() =
            UserUpdateCommand(
                email = email,
                name = name,
                password = password
            )
    }
}
