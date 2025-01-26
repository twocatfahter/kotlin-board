package org.study.kotlin_board.application.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.springframework.security.crypto.password.PasswordEncoder
import org.study.kotlin_board.api.controller.dto.UserCreateCommand
import org.study.kotlin_board.infrastructure.entity.Users

class UsersServiceTest : BehaviorSpec({
    val passwordEncoder = mockk<PasswordEncoder>()
    val usersService = mockk<UsersService>()

    given("User 엔티티 생성 시") {
        val command =
            UserCreateCommand(
                email = "test@test.com",
                password = "password123123",
                name = "test",
            )

        `when`("올바른 정보로 생성을 하면") {
            every { passwordEncoder.encode(command.password) } returns "encodedPassword"

            val user = Users.create(command, passwordEncoder)

            then("User 객체가 정상적으로 생성된다.") {
                user.email shouldBe command.email
                user.name shouldBe command.name
                user.passwordHash shouldBe "encodedPassword"
            }
        }
    }
    given("비밀번호 검증 시") {
        val user = Users(
            email = "test@test.com",
            name = "test",
            passwordHash = "encodedPassword"
        )

        `when`("올바른 비밀번호를 입력하면") {
            every { passwordEncoder.matches("password123", "encodedPassword") } returns true
            then("검증에 성공한다.") {
                user.validatePassword("password123", passwordEncoder) shouldBe true
            }
        }

        `when`("잘못된 비밀번호를 입력하면") {
            every { passwordEncoder.matches("wrongPassword", "encodedPassword") } returns false
            then("검증에 실패한다.") {
                user.validatePassword("wrongPassword", passwordEncoder) shouldBe false
            }
        }
    }

    given("이메일 변경 시") {
        val user = Users(
            email = "test@test.com",
            name = "test",
            passwordHash = "encodedPassword"
        )
        `when`("중복 되지 않은 이메일로 변경하면") {
            then("변경에 성공한다.") {

            }
        }

        `when`("중복되는 이메일로 변경을 하면") {
            then("변경에 실패한다.") {

            }
        }
    }
})
