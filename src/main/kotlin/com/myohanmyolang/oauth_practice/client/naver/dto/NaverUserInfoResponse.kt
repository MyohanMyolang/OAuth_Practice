package com.myohanmyolang.oauth_practice.client.naver.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.myohanmyolang.oauth_practice.client.dto.UserInfoResponse
import com.myohanmyolang.oauth_practice.client.kakao.dto.KakaoUserPropertiesResponse

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class NaverUserInfoResponse(
	val resultcode: String,
	val message:String,
	val response: NaverUserInfoDataResponse
) : UserInfoResponse {
	override val id: String
		get() = response.id
	val nickname: String
		get() = response.nickname
	val profileImage: String
		get() = response.profileImage
}