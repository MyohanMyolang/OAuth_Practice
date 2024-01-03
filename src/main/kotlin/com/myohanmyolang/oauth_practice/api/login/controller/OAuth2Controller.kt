package com.myohanmyolang.oauth_practice.api.login.controller

import com.myohanmyolang.oauth_practice.api.login.service.OAuth2Service
import com.myohanmyolang.oauth_practice.client.OAuthClientContainer
import com.myohanmyolang.oauth_practice.domain.entity.OAuth2Provider
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class OAuth2Controller (
	private val oAuthClientContainer: OAuthClientContainer,
	private val oAuthLoginService: OAuth2Service
){

	@GetMapping("/oauth2/login/{provider}")
	fun redirectLoginPage(response: HttpServletResponse, @PathVariable provider:OAuth2Provider){
		oAuthClientContainer.getClient(provider)
		response.sendRedirect("")
	}
}