package com.myohanmyolang.oauth_practice.client.naver.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class NaverTokenResponse(
	val accessToken: String,
	val refreshToken: String,
	val tokenType: String
) {
}