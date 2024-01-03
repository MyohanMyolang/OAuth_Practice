package com.myohanmyolang.oauth_practice.client.kakao

import com.myohanmyolang.oauth_practice.client.OAuthClient
import com.myohanmyolang.oauth_practice.client.dto.UserInfoResponse
import com.myohanmyolang.oauth_practice.client.kakao.dto.KakaoTokenResponse
import com.myohanmyolang.oauth_practice.client.kakao.dto.KakaoUserInfoResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@Component
class KakaoOAuthClient(
	@Value("\${oauth.kakao.clientId}")
	private val clientId: String,

	@Value("\${oauth.kakao.redirectUrl}")
	private val redirectUrl: String,

	@Value("\${oauth.kakao.secretKey}")
	private val secretKey: String,

	private val restClient: RestClient
) : OAuthClient {

	companion object {
		private const val KAKAO_AUTH_BASE_URL = "https://kauth.kakao.com"
		private const val KAKAO_API_BASE_URL = "https://kapi.kakao.com"
	}

	override fun getAccessToken(authorizationCode: String): String {
		val requestData = mutableMapOf(
			"grant_type" to "authorization_code",
			"client_id" to clientId,
			"code" to authorizationCode,
			"client_secret" to secretKey,
		)
		return restClient.post()
			.uri("$KAKAO_AUTH_BASE_URL/oauth/token")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.body(LinkedMultiValueMap<String, String>().apply { this.setAll(requestData) })
			.retrieve()
			.body<KakaoTokenResponse>()
			?.accessToken
			?: throw RuntimeException("AccessToken 조회 실패")
	}

	override fun retrieveUserInfo(accessToken: String): UserInfoResponse {
		return restClient.get()
			.uri("$KAKAO_API_BASE_URL/v2/user/me")
			.header("Authorization", "Bearer $accessToken")
			.retrieve()
			.body<KakaoUserInfoResponse>()
			?: throw RuntimeException("UserInfo 조회 실패")
	}

	override fun getUserInfoByAuthorizationCode(authorizationCode: String) =
		retrieveUserInfo(getAccessToken(authorizationCode))


	override fun generateLoginPageUrl(): String = StringBuilder(KAKAO_AUTH_BASE_URL)
		.append("/oauth2.0/authorize")
		.append("?client_id=").append(clientId)
		.append("&redirect_uri=").append(redirectUrl)
		.append("&response_type=").append("code")
		.toString()
}