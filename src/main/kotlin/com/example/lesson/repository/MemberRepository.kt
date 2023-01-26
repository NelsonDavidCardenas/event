package com.example.lesson.repository

import com.example.lesson.model.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface MemberRepository:JpaRepository<Member, Long> {
    fun findById(id: Long?) :Member

}