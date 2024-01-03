package com.myohanmyolang.oauth_practice.domain.repository

import com.myohanmyolang.oauth_practice.domain.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberEntityRepository : JpaRepository<MemberEntity, Long>{
}