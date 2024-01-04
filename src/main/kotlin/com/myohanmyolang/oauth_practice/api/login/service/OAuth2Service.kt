package com.myohanmyolang.oauth_practice.api.login.service

import com.myohanmyolang.oauth_practice.client.OAuthClientContainer
import com.myohanmyolang.oauth_practice.common.JwtHelper
import com.myohanmyolang.oauth_practice.domain.entity.OAuth2Provider
import com.myohanmyolang.oauth_practice.domain.service.SocialMemberService
import org.springframework.stereotype.Service

@Service
class OAuth2Service(
	private val oAuthClientContainer: OAuthClientContainer,
	private val socialMemberService: SocialMemberService,
	private val jwtHelper: JwtHelper
) {
	fun login(authorizationCode: String, provider: OAuth2Provider) =
		oAuthClientContainer.getClient(provider){
			getUserInfoByAuthorizationCode(authorizationCode)
		}
			.let { socialMemberService.registerIfAbsent(it, provider) }
			.let { jwtHelper.generateAccessToken(it.nickname) }


}