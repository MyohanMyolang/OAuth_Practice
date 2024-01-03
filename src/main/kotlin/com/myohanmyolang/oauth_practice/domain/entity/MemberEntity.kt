package com.myohanmyolang.oauth_practice.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "member")
class MemberEntity (
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null,

	val profileImageUrl: String? = null,
	val nickname:String,

	@Enumerated(EnumType.STRING)
	val provider: OAuth2Provider,
	val providerId: String,
){
}

enum class OAuth2Provider {
	kakao,
	naver
}