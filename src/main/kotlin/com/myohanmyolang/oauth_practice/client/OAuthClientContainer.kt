package com.myohanmyolang.oauth_practice.client

import com.myohanmyolang.oauth_practice.domain.entity.OAuth2Provider

class OAuthClientContainer (
	private val clientList: Map<OAuth2Provider, OAuthClient>
){
	fun getClient(provider: OAuth2Provider) = clientList[provider] ?: TODO("잘못된 Provider 요청 처리")
}