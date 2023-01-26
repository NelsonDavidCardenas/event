package com.example.lesson.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
@Table(name="register")
class Register {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    @Column(name="member_id")
    var memberId: Long? = null

    @Column(name="conference_id")
    var conferenceId: Long? = null
    var code: String? = null
    @Column(name="registered_at")
    var registerAt: String? = null
    var assited: Boolean? = null



}