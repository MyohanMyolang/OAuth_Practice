package com.myohanmyolang.oauth_practice.domain.service

import com.myohanmyolang.oauth_practice.client.dto.UserInfoResponse
import com.myohanmyolang.oauth_practice.domain.entity.SocialMemberEntity
import com.myohanmyolang.oauth_practice.domain.entity.OAuth2Provider
import com.myohanmyolang.oauth_practice.domain.repository.SocialMemberEntityRepository
import org.springframework.stereotype.Service

@Service
class SocialMemberService(
	private val socialMemberEntityRepository: SocialMemberEntityRepository
) {

	fun registerIfAbsent(userInfo: UserInfoResponse, provider: OAuth2Provider): SocialMemberEntity {
		return if (!socialMemberEntityRepository.existsByProviderAndProviderId(provider, userInfo.id)) {
			val socialMember = SocialMemberEntity.from(userInfo)
			socialMemberEntityRepository.save(socialMember)
		} else {
			socialMemberEntityRepository.findByProviderAndProviderId(provider, userInfo.id)
		}
	}
}