package com.myohanmyolang.oauth_practice.client.config

import com.myohanmyolang.oauth_practice.client.KakaoOAuthClient
import com.myohanmyolang.oauth_practice.client.OAuthClient
import com.myohanmyolang.oauth_practice.client.OAuthClientContainer
import com.myohanmyolang.oauth_practice.domain.entity.OAuth2Provider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class ClientConfig {

	@Bean
	fun oAuthFactoryConfiguration(): OAuthClientContainer{
		val clientList = HashMap<OAuth2Provider, OAuthClient>()

		clientList.put(OAuth2Provider.kakao, KakaoOAuthClient())

		return OAuthClientContainer(clientList)
	}

	@Bean
	fun restClient(): RestClient {
		return RestClient.builder()
			.build()
	}
}