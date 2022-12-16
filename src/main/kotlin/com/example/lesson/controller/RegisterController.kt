package com.example.lesson.controller

import com.example.lesson.model.Register
import com.example.lesson.service.RegisterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/register")
class RegisterController {

    @Autowired
    lateinit var registerService: RegisterService

    @GetMapping
    fun list (register:Register, pageable: Pageable):ResponseEntity<*>{
        val response= registerService.list(pageable,register )
        return ResponseEntity(response, HttpStatus.OK)
    }

    //listId
    @GetMapping("/{id}")
    fun listById (@PathVariable ("id") id: Long):ResponseEntity<Register>{
        return ResponseEntity(registerService.listById(id), HttpStatus.OK)
    }

    @PostMapping
    fun save (@RequestBody @Valid register: Register):Register{
        return registerService.save(register)
    }

    @PutMapping
    fun update (@RequestBody register:Register): ResponseEntity<Register> {
        return ResponseEntity(registerService.update(register), HttpStatus.OK)
    }

    @PatchMapping

    fun updateCode (@RequestBody register: Register):ResponseEntity<Register>{
        return ResponseEntity(registerService.updateCode(register), HttpStatus.OK)
    }



    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return registerService.delete(id)
    }

}