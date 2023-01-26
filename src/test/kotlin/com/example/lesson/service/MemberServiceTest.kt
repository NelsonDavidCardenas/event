package com.example.lesson.service

import com.example.lesson.model.Member
import com.example.lesson.repository.MemberRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.util.*


@SpringBootTest
class MemberServiceTest {
    @InjectMocks
    lateinit var memberService: MemberService

    @Mock
    lateinit var memberRepository: MemberRepository

    val memberMock = Member().apply {
        id=1
        fullname="Nelson Cardenas"
        email="Cuenca"
        age= 20
    }

    @Test
    fun saveMemberCorrect(){
        Mockito.`when`(memberRepository.save(Mockito.any(Member::class.java))).thenReturn(memberMock)
        val response = memberService.save(memberMock)
        Assertions.assertEquals(response.id, memberMock.id)
    }


    @Test
    fun saveMemberWhenDescriptionIsBlank(){

        Assertions.assertThrows(Exception::class.java) {
            memberMock.apply { fullname=" "}
            Mockito.`when`(memberRepository.save(Mockito.any(Member::class.java))).thenReturn(memberMock)
            memberService.save(memberMock)
        }

    }


}