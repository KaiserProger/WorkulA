package com.swaptech.workula.presentation.utils

import android.util.Patterns

object Validator {
    private const val MIN_PASSWORD_LENGTH = 8
    //TODO: Temporary
    private const val MAX_NAME_LENGTH = 20

    private fun inputIsNotEmptyAnd(input: String, condition: Boolean) = !isEmpty(input) && condition

    fun isEmpty(input: String) = input.isEmpty() || input.isBlank() || input.any { it.isWhitespace() }

    fun isValidEmail(email: String) = inputIsNotEmptyAnd(email, Patterns.EMAIL_ADDRESS.matcher(email).matches())

    fun isValidPassword(password: String) = inputIsNotEmptyAnd(password, password.length >= MIN_PASSWORD_LENGTH)

    fun isValidName(name: String) = inputIsNotEmptyAnd(name, name.length <= MAX_NAME_LENGTH)
}