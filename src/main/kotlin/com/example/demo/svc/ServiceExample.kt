package com.example.demo.svc

import com.example.demo.dto.JournalRequest
import com.example.demo.dto.JournalResponse
import com.example.demo.dto.ScientificArticleRequest
import com.example.demo.dto.ScientificArticleResponse
import com.example.demo.entity.Journal
import com.example.demo.entity.ScientificArticle

interface JournalService {
    fun addJournal(request: JournalRequest): JournalResponse
    fun getAllJournals(): MutableList<Journal>
    fun deleteJournalById(id: Long)
    fun addArticleToJournal(journalId: Long, articleID: Long): Journal?
}

interface ScientificArticleService {
    fun addScientificArticle(request: ScientificArticleRequest): ScientificArticleResponse
    fun getAllScientificArticles(): MutableList<ScientificArticle>
    fun deleteScientificArticleById(id: Long)
    fun getScientificArticleById(id: Long): ScientificArticleResponse

}