package com.example.lesson.controller

import com.example.lesson.model.Conference
import com.example.lesson.model.Member
import com.example.lesson.service.ConferenceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/conference")
class ConferenceController {

    @Autowired
    lateinit var conferenceService: ConferenceService



    //listId
    @GetMapping("/{id}")
    fun listById (@PathVariable ("id") id: Long):ResponseEntity<Conference>{
        return ResponseEntity(conferenceService.listById(id), HttpStatus.OK)
    }

    @GetMapping
    fun list (conference: Conference, pageable: Pageable):ResponseEntity<*>{
        val response= conferenceService.list(pageable,conference )
        return ResponseEntity(response, HttpStatus.OK)
    }


    @PostMapping
    fun save (@RequestBody @Valid conference: Conference):Conference{
        return conferenceService.save(conference)
    }


    @PutMapping
    fun update (@RequestBody conference:Conference): ResponseEntity<Conference> {
        return ResponseEntity(conferenceService.update(conference), HttpStatus.OK)
    }

    @PatchMapping

    fun updateTitle (@RequestBody conference: Conference):ResponseEntity<Conference>{
        return ResponseEntity(conferenceService.updateTitle(conference), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return conferenceService.delete(id)
    }

}