package org.study.kotlin_board.application.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.study.kotlin_board.infrastructure.entity.Users
import java.nio.charset.StandardCharsets
import java.util.*

@Service
class JwtService() {
    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun generateToken(users: Users): String {
        val currentTimeMillis = System.currentTimeMillis()
        return Jwts.builder()
            .subject(users.email)
            .claim("role", "USER")
            .issuedAt(Date(currentTimeMillis))
            .expiration(Date(currentTimeMillis + 1000 * 60 * 60))
            .signWith(Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8)))
            .compact()
    }

    fun parseJwtClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8)))
            .build()
            .parseSignedClaims(token)
            .payload
    }
}
