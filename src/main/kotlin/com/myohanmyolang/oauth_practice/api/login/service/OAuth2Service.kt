package com.myohanmyolang.oauth_practice.api.login.service

import com.myohanmyolang.oauth_practice.client.OAuthClientContainer
import com.myohanmyolang.oauth_practice.domain.entity.OAuth2Provider
import com.myohanmyolang.oauth_practice.domain.service.MemberService
import org.springframework.stereotype.Service

@Service
class OAuth2Service(
	private val oAuthClientContainer: OAuthClientContainer,
	private val memberService: MemberService
) {

	/**
	 * NOTE:
	 *  1. provider로 Factory에서 해당하는 Client 얻기
	 *  2. 인가 코드로 AccessToken 얻기
	 *  3. AccessToken으로 유저 정보 조회
	 *  4. 유저 정보가 조회, 없다면 회원 가입 처리
	 *  5. 정보를 토대로 JwtToken 발행
	 */
	fun login(authorizationCode: String, provider: OAuth2Provider) =
		oAuthClientContainer.getClient(provider).let { client ->
			client.getAccessToken(authorizationCode).let { client.retrieveUserInfo(it) } }
			.let { memberService.registerIfAbsent(it, provider) }
			.let { jwtHelper.generateAcessToken(it.nickname) }


}