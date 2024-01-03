package com.myohanmyolang.oauth_practice.domain.repository

import com.myohanmyolang.oauth_practice.domain.entity.SocialMemberEntity
import com.myohanmyolang.oauth_practice.domain.entity.OAuth2Provider
import org.springframework.data.jpa.repository.JpaRepository

interface SocialMemberEntityRepository : JpaRepository<SocialMemberEntity, Long>{
	fun existsByProviderAndProviderId(provider: OAuth2Provider, toString: String): Boolean
	fun findByProviderAndProviderId(provider: OAuth2Provider, toString: String): SocialMemberEntity
}