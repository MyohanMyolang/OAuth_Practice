package com.myohanmyolang.oauth_practice.client

import com.myohanmyolang.oauth_practice.domain.entity.OAuth2Provider

class OAuthClientContainer (
	private val clientList: Map<OAuth2Provider, OAuthClient>
){
	fun getClient(provider: OAuth2Provider) = clientList[provider] ?: TODO("FrontEnd 요청 확인 또는 Configuration 확인")
}