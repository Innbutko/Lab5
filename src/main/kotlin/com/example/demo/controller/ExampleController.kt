package com.example.demo.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.example.demo.dto.JournalRequest
import com.example.demo.dto.JournalResponse
import com.example.demo.dto.ScientificArticleRequest
import com.example.demo.dto.ScientificArticleResponse
import com.example.demo.entity.Journal
import com.example.demo.entity.ScientificArticle
import com.example.demo.svc.JournalService
import com.example.demo.svc.ScientificArticleService

@RestController
class RestApiController @Autowired constructor(
    private val journalService: JournalService,
    private val scientificArticleService: ScientificArticleService
) {

    @PostMapping("/journals")
    fun addJournal(@RequestBody request: JournalRequest): ResponseEntity<JournalResponse> {
        val response = journalService.addJournal(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/journals")
    fun getAllJournals(): ResponseEntity<MutableList<Journal>> {
        val response = journalService.getAllJournals()
        return ResponseEntity(response, HttpStatus.OK)
    }

    @DeleteMapping("/journals")
    fun deleteJournalById(@RequestBody id: Long): ResponseEntity<Any> {
        journalService.deleteJournalById(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PostMapping("/journals/articles")
    fun addArticleToJournal(@RequestBody journalID: Long, articleID: Long): ResponseEntity<Journal?> {
        val response = journalService.addArticleToJournal(journalID, articleID)
        return if (response != null) {
            ResponseEntity(response, HttpStatus.CREATED)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/scientific-articles")
    fun addScientificArticle(@RequestBody request: ScientificArticleRequest): ResponseEntity<ScientificArticleResponse> {
        val response = scientificArticleService.addScientificArticle(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/scientific-articles")
    fun getAllScientificArticles(): ResponseEntity<MutableList<ScientificArticle>> {
        val response = scientificArticleService.getAllScientificArticles()
        return ResponseEntity(response, HttpStatus.OK)
    }
    @DeleteMapping("/scientific-articles")
    fun deleteScientificArticleById(@RequestBody id: Long): ResponseEntity<Any> {
        scientificArticleService.deleteScientificArticleById(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping("/scientific-articles/id")
    fun getScientificArticleById(@RequestBody id: Long): ResponseEntity<ScientificArticleResponse> {
        val response = scientificArticleService.getScientificArticleById(id)
        return ResponseEntity(response, HttpStatus.OK)
    }
}