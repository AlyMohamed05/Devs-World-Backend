package com.silverbullet.plugins

import com.silverbullet.feature_activity.route.configureActivitiesRoutes
import com.silverbullet.feature_comment.route.configureCommentRoutes
import com.silverbullet.feature_follow.route.configureFollowingRoutes
import com.silverbullet.feature_like.route.configureLikeRoutes
import com.silverbullet.feature_post.route.configurePostRoutes
import com.silverbullet.feature_search.route.configureSearchRoutes
import com.silverbullet.feature_user.route.configureUserRoute
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*

fun Application.configureRouting() {

    routing {
        configureUserRoute()
        configurePostRoutes()
        configureFollowingRoutes()
        configureLikeRoutes()
        configureCommentRoutes()
        configureActivitiesRoutes()
        configureSearchRoutes()

        static {
            resources("static")
        }
    }
}
