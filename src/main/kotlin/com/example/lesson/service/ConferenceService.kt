package com.example.lesson.service

import com.example.lesson.model.Conference
import com.example.lesson.model.Member
import com.example.lesson.repository.ConferenceRepository
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
class ConferenceService {

    @Autowired
    lateinit var conferenceRepository: ConferenceRepository
    @Autowired
    lateinit var eventRepository: EventRepository

    fun list():List<Conference>{
        return conferenceRepository.findAll()
    }

    fun save(conference: Conference):Conference{
        return conferenceRepository.save(conference)
    }

    fun update (conference: Conference): Conference {
        try {
            conferenceRepository.findById(conference.id)
                ?: throw Exception("Id no existe")
            return conferenceRepository.save(conference)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }


    }
    //

    fun updateTitle(conference: Conference): Conference {
        try{
            val response = conferenceRepository.findById(conference.id)
                ?: throw Exception("ID no existe")
            response.apply {
                title=conference.title
            }
            return conferenceRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }


    //listId
    fun listById (id: Long?): Conference{
        return conferenceRepository.findById(id)
    }



    fun list (pageable: Pageable, conference: Conference): Page<Conference> {
        val matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withMatcher(("tittle"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return conferenceRepository.findAll(Example.of(conference, matcher), pageable)
    }

    fun delete (id: Long?):Boolean?{
        conferenceRepository.findById(id) ?:
        throw  Exception()
        conferenceRepository.deleteById(id!!)
        return true
    }


}