package com.silverbullet.feature_user.route

import com.silverbullet.core.data.interfaces.UserRepository
import com.silverbullet.feature_user.data.request.CreateUserRequest
import com.silverbullet.feature_user.data.request.hasBlankField
import com.silverbullet.feature_user.service.UserService
import com.silverbullet.utils.ApiResponses
import com.silverbullet.utils.failureBasicResponse
import com.silverbullet.utils.parsingFailureResponse
import com.silverbullet.utils.successfulBasicResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.createUserRoute() {

    val userService: UserService by inject()

    post("/create") {
        kotlin.runCatching {
            call.receive<CreateUserRequest>()
        }.apply {
            onSuccess { request: CreateUserRequest ->
                if(request.hasBlankField()){
                    call.failureBasicResponse<Unit>(
                        statusCode = HttpStatusCode.BadRequest,
                        message = ApiResponses.FIELDS_EMPTY
                    )
                    return@post
                }
                when (userService.createUser(request)) {
                    UserRepository.CreateUserResult.SUCCESS -> {
                        call.successfulBasicResponse<Unit>(message = "CREATED")
                    }

                    UserRepository.CreateUserResult.FAILED -> {
                        call.failureBasicResponse<Unit>(statusCode = HttpStatusCode.InternalServerError)
                    }

                    UserRepository.CreateUserResult.EMAIL_ALREADY_EXITS -> {
                        call.failureBasicResponse<Unit>(
                            message = ApiResponses.EMAIL_ALREADY_USED
                        )
                    }
                }
            }
            onFailure {
                call.parsingFailureResponse()
            }
        }
    }
}