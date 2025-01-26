package org.study.kotlin_board.infrastructure.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.study.kotlin_board.infrastructure.entity.Users
import java.util.Optional

interface UsersRepository : JpaRepository<Users, Long> {
    fun findByEmail(email: String): Optional<Users>
    fun existsByEmail(email: String): Boolean
}
