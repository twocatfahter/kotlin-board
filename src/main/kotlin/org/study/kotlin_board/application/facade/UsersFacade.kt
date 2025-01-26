package org.study.kotlin_board.application.facade

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.study.kotlin_board.api.controller.dto.UserDto
import org.study.kotlin_board.application.service.JwtService
import org.study.kotlin_board.application.service.UsersService

@Component
class UsersFacade(
    private val usersService: UsersService,
    private val jwtService: JwtService,
) {
    @Transactional
    fun signUp(request: UserDto.SignUpRequest): UserDto.Response {
        return usersService.signUp(request.toCommand())
    }

    fun login(request: UserDto.LoginRequest): UserDto.LoginResponse {
        val generateToken = jwtService.generateToken(usersService.login(request))
        return UserDto.LoginResponse(generateToken)
    }

    fun updateUser(id: Long, request: UserDto.UpdateRequest): UserDto.Response {
        return usersService.updateUser(id, request.toCommand())
    }
}
