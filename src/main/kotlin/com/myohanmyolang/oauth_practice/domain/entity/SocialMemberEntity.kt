package com.myohanmyolang.oauth_practice.domain.entity

import com.myohanmyolang.oauth_practice.client.dto.UserInfoResponse
import com.myohanmyolang.oauth_practice.client.kakao.dto.KakaoUserInfoResponse
import com.myohanmyolang.oauth_practice.client.naver.dto.NaverUserInfoResponse
import jakarta.persistence.*

@Entity
@Table(name = "social_member")
class SocialMemberEntity(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "social_member_id")
	val id: Long? = null,

	val profileImageUrl: String? = null,
	val nickname: String,

	@Enumerated(EnumType.STRING)
	val provider: OAuth2Provider,
	val providerId: String,
) {

	companion object {
		fun from(response: UserInfoResponse): SocialMemberEntity {
			return when (response) {
				is KakaoUserInfoResponse -> SocialMemberEntity(
					providerId = response.id,
					provider = OAuth2Provider.kakao,
					profileImageUrl = response.profileImage,
					nickname = response.nickname
				)

				is NaverUserInfoResponse -> SocialMemberEntity(
					providerId = response.id,
					provider = OAuth2Provider.naver,
					profileImageUrl = response.profileImage,
					nickname = response.nickname
				)

				else -> TODO("만들어라아아아아아아아아아아")
			}
		}
	}


}

enum class OAuth2Provider {
	kakao,
	naver
}