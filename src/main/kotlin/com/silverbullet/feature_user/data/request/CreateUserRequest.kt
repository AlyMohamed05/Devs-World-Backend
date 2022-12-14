package com.silverbullet.feature_user.data.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    val email: String,
    val username: String,
    val password: String
)

fun CreateUserRequest.hasBlankField() = email.isBlank() || username.isBlank() || password.isBlank()