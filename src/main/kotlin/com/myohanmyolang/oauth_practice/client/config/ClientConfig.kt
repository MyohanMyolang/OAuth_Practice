package com.myohanmyolang.oauth_practice.client.config

import com.myohanmyolang.oauth_practice.client.kakao.KakaoOAuthClient
import com.myohanmyolang.oauth_practice.client.OAuthClient
import com.myohanmyolang.oauth_practice.client.OAuthClientContainer
import com.myohanmyolang.oauth_practice.client.naver.NaverOAuthClient
import com.myohanmyolang.oauth_practice.domain.entity.OAuth2Provider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class ClientConfig {

	@Bean
	fun oAuthFactoryConfiguration(
		kakaoOAuthClient: KakaoOAuthClient,
		naverOAuthClient: NaverOAuthClient
	): OAuthClientContainer{
		val clientList = HashMap<OAuth2Provider, OAuthClient>()

		clientList[OAuth2Provider.kakao] = kakaoOAuthClient
		clientList[OAuth2Provider.naver] = naverOAuthClient

		return OAuthClientContainer(clientList)
	}

	@Bean
	fun restClient(): RestClient {
		return RestClient.builder()
			.build()
	}
}