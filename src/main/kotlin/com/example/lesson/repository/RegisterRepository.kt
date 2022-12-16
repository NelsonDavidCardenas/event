package com.example.lesson.repository

import com.example.lesson.model.Register
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface RegisterRepository:JpaRepository<Register, Long> {
    fun findById(id: Long?) :Register
}