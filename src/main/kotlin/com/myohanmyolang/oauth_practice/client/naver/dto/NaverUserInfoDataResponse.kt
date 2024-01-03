package com.myohanmyolang.oauth_practice.client.naver.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class NaverUserInfoDataResponse(
	val id:String,
	val nickname:String,
	val profileImage: String
) {
}