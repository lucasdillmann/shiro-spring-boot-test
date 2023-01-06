package br.com.dillmann.shirotest.exceptionhandling

data class ErrorResponseDto(
    val type: String,
    val message: String?,
    val cause: String?,
)