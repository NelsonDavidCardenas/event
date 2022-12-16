package com.example.lesson.repository

import com.example.lesson.model.Conference
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface ConferenceRepository:JpaRepository<Conference, Long> {
    fun findById(id: Long?) : Conference

//    @Query(nativeQuery =true)//Va a leer jpa-named.....
//      fun sumAttendees(@Param("eventId") eventId: Long?): Double?
}