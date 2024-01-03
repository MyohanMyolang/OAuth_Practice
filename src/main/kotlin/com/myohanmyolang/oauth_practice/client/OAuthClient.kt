package com.myohanmyolang.oauth_practice.client

interface OAuthClient {
	fun getAccessToken(authorizationCode: String): String
}