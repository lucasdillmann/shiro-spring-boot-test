package br.com.dillmann.shirotest.exceptionhandling

import org.apache.shiro.lang.ShiroException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionController {

    @ExceptionHandler(ShiroException::class)
    fun handle(ex: ShiroException) =
        buildResponse(ex, 403)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handle(ex: MethodArgumentNotValidException) =
        buildResponse(ex, 400)

    @ExceptionHandler(Throwable::class)
    fun handle(ex: Throwable) =
        buildResponse(ex, 500)

    private fun buildResponse(ex: Throwable, statusCode: Int): ResponseEntity<ErrorResponseDto> {
        val payload = ErrorResponseDto(ex.javaClass.name, ex.message, ex.cause?.message)
        return ResponseEntity.status(statusCode).body(payload)
    }
}