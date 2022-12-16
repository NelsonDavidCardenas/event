package com.example.lesson.repository

import com.example.lesson.model.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface EventRepository:JpaRepository<Event, Long> {
    fun findById(id: Long?) : Event
}