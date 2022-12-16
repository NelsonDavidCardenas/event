package com.example.lesson.service

import com.example.lesson.model.Register
import com.example.lesson.repository.MemberRepository
import com.example.lesson.repository.RegisterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import kotlin.random.Random


@Service
class RegisterService {

    @Autowired
    lateinit var registerRepository: RegisterRepository
    @Autowired
    lateinit var memberRepository : MemberRepository

    fun list():List<Register>{
        return registerRepository.findAll()
    }

    fun save(register: Register):Register{
        val response = registerRepository.save(register)
        randomCode(response)

        return response

    }

    fun update (register: Register):Register {
        try {
            registerRepository.findById(register.memberId)
                ?: throw Exception("Id no existe")
            return registerRepository.save(register)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }


    }
    //

    fun updateCode(register: Register): Register{
        try{
            val response = registerRepository.findById(register.id)
                ?: throw Exception("ID no existe")
            response.apply {
                code=register.code
            }
            return registerRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    //listId
    fun listById (id: Long?): Register{
        return registerRepository.findById(id)
    }

    ///codigo aleatorio
    fun randomCode (register: Register){
        val randomValues = List(6) { Random.nextInt (0, 10) }
        val newCode = randomValues.joinToString()
        val registerResponse = registerRepository.findById(register.id)
        registerResponse?.apply{
            code= newCode
        }

    }

    fun list (pageable: Pageable, register:Register): Page<Register> {
        val matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withMatcher(("fullname"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return registerRepository.findAll(Example.of(register, matcher), pageable)
    }

    fun delete (id: Long?):Boolean?{
        registerRepository.findById(id) ?:
        throw  Exception()
        registerRepository.deleteById(id!!)
        return true
    }


    
}