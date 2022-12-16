package com.example.lesson.controller

import com.example.lesson.model.Event
import com.example.lesson.model.Member
import com.example.lesson.service.EventService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/event")
class EventController {

    @Autowired
    lateinit var eventService: EventService



    @GetMapping
    fun list (event: Event, pageable: Pageable):ResponseEntity<*>{
        val response= eventService.list(pageable,event )
        return ResponseEntity(response, HttpStatus.OK)
    }


    //listId
    @GetMapping("/{id}")
    fun listById (@PathVariable ("id") id: Long):ResponseEntity<Event>{
        return ResponseEntity(eventService.listById(id), HttpStatus.OK)
    }

    @PostMapping
    fun save (@RequestBody @Valid event: Event):Event{
        return eventService.save(event)
    }

    @PutMapping
    fun update (@RequestBody event:Event): ResponseEntity<Event> {
        return ResponseEntity(eventService.update(event), HttpStatus.OK)
    }
    //
    @PatchMapping
    fun updateDescription (@RequestBody event: Event):ResponseEntity<Event>{
        return ResponseEntity(eventService.updateDescription(event), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return eventService.delete(id)
    }
}