package com.myohanmyolang.oauth_practice.client.naver

import com.myohanmyolang.oauth_practice.client.OAuthClient
import com.myohanmyolang.oauth_practice.client.dto.UserInfoResponse
import com.myohanmyolang.oauth_practice.client.naver.dto.NaverTokenResponse
import com.myohanmyolang.oauth_practice.client.naver.dto.NaverUserInfoResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@Component
class NaverOAuthClient(
	@Value("\${oauth.naver.clientId}")
	private val clientId: String,

	@Value("\${oauth.naver.redirectUrl}")
	private val redirectUrl: String,

	@Value("\${oauth.naver.secretKey}")
	private val secretKey: String,

	private val restClient: RestClient
) : OAuthClient {

	companion object {
		private const val NAVER_AUTH_BASE_URL = "https://nid.naver.com"
		private const val NAVER_API_BASE_URL = "https://openapi.naver.com"
	}

	override fun getAccessToken(authorizationCode: String): String {
		val url = StringBuilder(NAVER_AUTH_BASE_URL)
			.append("/oauth2.0/token")
			.append("?grant_type=").append("authorization_code")
			.append("&client_id=").append(clientId)
			.append("&code=").append(authorizationCode)
			.append("&client_secret=").append(secretKey)
			.append("&state=").append("practice")
			.toString()


		return restClient.get()
			.uri(url)
			.retrieve()
			.body<NaverTokenResponse>()
			?.accessToken
			?: throw RuntimeException("AccessToken 조회 실패")
	}

	override fun retrieveUserInfo(accessToken: String): UserInfoResponse {
		return restClient.get()
			.uri("${NAVER_API_BASE_URL}/v1/nid/me")
			.header("Authorization", "Bearer $accessToken")
			.retrieve()
			.body<NaverUserInfoResponse>()
			?: throw RuntimeException("UserInfo 조회 실패")
	}

	override fun generateLoginPageUrl(): String = StringBuilder(NAVER_AUTH_BASE_URL)
		.append("/oauth2.0/authorize")
		.append("?client_id=").append(clientId)
		.append("&redirect_uri=").append(redirectUrl)
		.append("&response_type=").append("code")
		.toString()

	override fun getUserInfoByAuthorizationCode(authorizationCode: String): UserInfoResponse =
		retrieveUserInfo(getAccessToken(authorizationCode))
}