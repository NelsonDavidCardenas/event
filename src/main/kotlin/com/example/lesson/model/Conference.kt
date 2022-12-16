package com.example.lesson.model

import java.sql.Time
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name="conference")
class Conference {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    @NotBlank(message="Obligatorio")
    var title: String? = null
    @NotBlank(message="Obligatorio")
    var speaker: String?= null
    var hour: Time? = null
    var day: Date? = null
    @Column(name="total_attendees")
    var totalAttendees: Long? = null

    @Column(name="event_id")
    var eventId: Long? = null

}