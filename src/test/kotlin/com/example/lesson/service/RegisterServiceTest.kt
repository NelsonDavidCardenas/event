package com.example.lesson.service

import com.example.lesson.model.Conference
import com.example.lesson.model.Member
import com.example.lesson.model.Register
import com.example.lesson.repository.ConferenceRepository
import com.example.lesson.repository.MemberRepository
import com.example.lesson.repository.RegisterRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File


@SpringBootTest
class RegisterServiceTest {
    @InjectMocks
    lateinit var registerService: RegisterService
    @Mock
    lateinit var registerRepository: RegisterRepository
    @Mock
    lateinit var  memberRepository: MemberRepository
    @Mock
    lateinit var conferenceRepository: ConferenceRepository

    val jsonString = File("./src/test/resources/register.json").readText(Charsets.UTF_8)
    val registerMock = Gson().fromJson(jsonString, Register::class.java)

    val MemberjsonString = File("./src/test/resources/member.json").readText(Charsets.UTF_8)
    val memberMock = Gson().fromJson(MemberjsonString, Member::class.java)

    val ConferencejsonString = File("./src/test/resources/conference.json").readText(Charsets.UTF_8)
    val conferenceMock = Gson().fromJson(ConferencejsonString, Conference::class.java)


    @Test
    fun saveDetailWhenIsCorrect(){
        Mockito.`when`(memberRepository.findById(registerMock.memberId)).thenReturn(memberMock)
        Mockito.`when`(conferenceRepository.findById(registerMock.conferenceId)).thenReturn(conferenceMock)
        Mockito.`when`(registerRepository.save(Mockito.any(Register::class.java))).thenReturn(registerMock)
        val response = registerService.save(registerMock)
        Assertions.assertEquals(response.id, registerMock.id)
    }










}