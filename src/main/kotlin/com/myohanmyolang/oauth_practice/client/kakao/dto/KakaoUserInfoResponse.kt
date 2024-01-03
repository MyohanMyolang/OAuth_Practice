package com.myohanmyolang.oauth_practice.client.kakao.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.myohanmyolang.oauth_practice.client.dto.UserInfoResponse

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class KakaoUserInfoResponse(
	override val id: String,
	val properties: KakaoUserPropertiesResponse
) : UserInfoResponse {
	val nickname: String
		get() = properties.nickname
	val profileImage: String
		get() = properties.profileImage
}