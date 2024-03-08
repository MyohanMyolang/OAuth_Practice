package com.myohanmyolang.oauth_practice.common

import org.springframework.stereotype.Component

@Component
class JwtHelper {
    fun generateAccessToken(nickname: String) = "Token ${nickname}"
}