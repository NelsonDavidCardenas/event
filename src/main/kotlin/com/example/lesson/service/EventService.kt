package com.example.lesson.service

import com.example.lesson.model.Event
import com.example.lesson.model.Member
import com.example.lesson.repository.EventRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class EventService {

    @Autowired
    lateinit var eventRepository: EventRepository

    fun list():List<Event>{
        return eventRepository.findAll()
    }

    //listId
    fun listById (id: Long?): Event{
        return eventRepository.findById(id)
    }

    fun save(event: Event):Event{
        try {
            event.description?.takeIf{ it.trim().isNotEmpty()}
                ?:throw Exception("No debe ser vacio")

            return eventRepository.save(event)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun update (event: Event): Event {
        try {
            eventRepository.findById(event.id)
                ?: throw Exception("Id no existe")
            return eventRepository.save(event)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }


    }
    //
    fun updateDescription(event: Event): Event {
        try{
            val response = eventRepository.findById(event.id)
                ?: throw Exception("ID no existe")
            response.apply {
                description=event.description
            }
            return eventRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    //

    fun list (pageable: Pageable, event:Event): Page<Event> {
        val matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withMatcher(("desciption"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return eventRepository.findAll(Example.of(event, matcher), pageable)
    }


    fun delete (id: Long?):Boolean?{
        eventRepository.findById(id) ?:
        throw  Exception()
        eventRepository.deleteById(id!!)
        return true
    }


}