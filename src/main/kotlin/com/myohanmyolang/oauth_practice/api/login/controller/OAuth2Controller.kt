package com.myohanmyolang.oauth_practice.api.login.controller

import com.myohanmyolang.oauth_practice.api.login.service.OAuth2Service
import com.myohanmyolang.oauth_practice.client.OAuthClientContainer
import com.myohanmyolang.oauth_practice.domain.entity.OAuth2Provider
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/oauth2")
class OAuth2Controller(
	private val oAuthClientContainer: OAuthClientContainer,
	private val oAuthLoginService: OAuth2Service
) {

	@GetMapping("/login/{provider}")
	fun redirectLoginPage(response: HttpServletResponse, @PathVariable provider: OAuth2Provider) {
		oAuthClientContainer.getClient(provider) {
			response.sendRedirect(generateLoginPageUrl())
		}
	}

	@GetMapping("/callback/{provider}")
	fun login(
		@RequestParam(name = "code") authorizationCode: String,
		@PathVariable provider: OAuth2Provider
	) = oAuthLoginService.login(authorizationCode, provider)


}