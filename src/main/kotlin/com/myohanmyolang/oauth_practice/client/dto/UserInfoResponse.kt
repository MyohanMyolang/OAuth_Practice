package com.myohanmyolang.oauth_practice.client.dto

// 모든 MemberEntity에 접근하는 Response는 이 Response를 상속받아야 한다.
interface UserInfoResponse {
	val id:String
}