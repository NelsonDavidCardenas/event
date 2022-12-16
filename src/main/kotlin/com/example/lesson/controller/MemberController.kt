package com.example.lesson.controller

import com.example.lesson.model.Member
import com.example.lesson.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/member")
class MemberController {

    @Autowired
    lateinit var memberService: MemberService

    ///findId
    @GetMapping("/{id}")
    fun listById (@PathVariable ("id") id: Long):ResponseEntity<Member>{
        return ResponseEntity(memberService.listById(id), HttpStatus.OK)
    }

    @GetMapping
    fun list (member:Member, pageable: Pageable):ResponseEntity<*>{
        val response= memberService.list(pageable,member )
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping
    fun save (@RequestBody @Valid member: Member):Member{
        return memberService.save(member)
    }

    @PutMapping
    fun update (@RequestBody member:Member): ResponseEntity<Member> {
        return ResponseEntity(memberService.update(member), HttpStatus.OK)
    }

    @PatchMapping

    fun updateMember (@RequestBody member: Member):ResponseEntity<Member>{
        return ResponseEntity(memberService.updateMember(member), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return memberService.delete(id)
    }

}