package org.study.kotlin_board.infrastructure.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.crypto.password.PasswordEncoder
import org.study.kotlin_board.api.controller.dto.UserCreateCommand
import org.study.kotlin_board.api.controller.dto.UserDto
import org.study.kotlin_board.application.service.UsersService
import org.study.kotlin_board.global.entity.BaseEntity

@Entity
@Table(name = "users")
class Users(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = false)
    var email: String,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    @JsonIgnore val passwordHash: String,
) : BaseEntity() {

    fun validatePassword(rawPassword: String, passwordEncoder: PasswordEncoder): Boolean {
        return passwordEncoder.matches(rawPassword, passwordHash)
    }

    fun changeEmail(newEmail: String, usersService: UsersService) {
        if (usersService.existsByEmail(newEmail)) {
            throw IllegalArgumentException("이미 존재하는 이메일입니다.")
        }
        this.email = newEmail
    }

    fun toResponse() =
        UserDto.Response(
            id = id ?: 0,
            email = email,
            name = name,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )

    companion object {
        fun create(
            command: UserCreateCommand,
            passwordEncoder: PasswordEncoder,
        ) = Users(
            email = command.email,
            name = command.name,
            passwordHash = passwordEncoder.encode(command.password),
        )
    }
}
