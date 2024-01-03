package com.myohanmyolang.oauth_practice.client

import com.myohanmyolang.oauth_practice.client.dto.UserInfoResponse

interface OAuthClient {
	fun getAccessToken(authorizationCode: String): String
	fun retrieveUserInfo(accessToken: String): UserInfoResponse
	fun generateLoginPageUrl(): String
	fun getUserInfoByAuthorizationCode(authorizationCode: String): UserInfoResponse
}