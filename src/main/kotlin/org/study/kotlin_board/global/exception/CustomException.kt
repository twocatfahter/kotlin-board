package org.study.kotlin_board.global.exception

import org.springframework.http.HttpStatus

sealed class CustomException {
    class DuplicateEmailException(message: String) : BusinessException(
        message = message,
        status = HttpStatus.CONFLICT,
    )

    class UnauthorizedAccessException(message: String) : BusinessException(
        message = message,
        status = HttpStatus.UNAUTHORIZED,
    )

    class UserNotFoundException(message: String) : BusinessException(
        message = message,
        status = HttpStatus.NOT_FOUND,
    )
}
