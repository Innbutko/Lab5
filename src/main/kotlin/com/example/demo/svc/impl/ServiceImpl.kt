package com.example.demo.svc.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.example.demo.dto.JournalRequest
import com.example.demo.dto.JournalResponse
import com.example.demo.dto.ScientificArticleRequest
import com.example.demo.dto.ScientificArticleResponse
import com.example.demo.entity.Journal
import com.example.demo.entity.ScientificArticle
import com.example.demo.repository.JournalRepository
import com.example.demo.repository.ScientificArticleRepository
import com.example.demo.svc.JournalService
import com.example.demo.svc.ScientificArticleService

@Service
class JournalServiceImpl @Autowired constructor (
    private val journalRepository: JournalRepository,
    private val scientificArticleRepository: ScientificArticleRepository
): JournalService {

    @Transactional
    @PreAuthorize("hasRole('EDITOR')")
    override fun addJournal(request: JournalRequest): JournalResponse {
        val list = mutableListOf<ScientificArticle>()
        val entity = Journal(name = request.name, foundingDate = request.foundingDate, topic = request.topic, language = request.language, issn = request.issn, price = request.price, isPeriodic = request.isPeriodic, articlesList = list)
        val result = journalRepository.save(entity)
        return JournalResponse(result.id, result.name, result.topic, result.price)
    }
    @Transactional
    @PreAuthorize("hasAnyRole('EDITOR', 'VIEWER')")
    override fun getAllJournals(): MutableList<Journal> {
        return journalRepository.findAll()
    }
    @Transactional
    @PreAuthorize("hasRole('EDITOR')")
    override fun deleteJournalById(id: Long) {
        journalRepository.deleteById(id)
    }
    @Transactional
    @PreAuthorize("hasRole('EDITOR')")
    override fun addArticleToJournal(journalId: Long, articleID: Long): Journal? {
        val journal = journalRepository.findById(journalId).orElse(null)
        return if (journal != null) {
            val article = scientificArticleRepository.findById(articleID).orElse(null)
            if (article != null) {
                journal.articlesList.add(article)
                article.journal = journal
                scientificArticleRepository.save(article)
                journalRepository.save(journal)
            } else {
                null
            }
        } else {
            null
        }
    }
}

@Service
class ScientificArticleServiceImpl @Autowired constructor (
    private val scientificArticleRepository: ScientificArticleRepository
): ScientificArticleService {

    @PreAuthorize("hasRole('EDITOR')")
    override fun addScientificArticle(request: ScientificArticleRequest): ScientificArticleResponse {
        val entity = ScientificArticle(title = request.title, author = request.author, dateWritten = request.dateWritten, wordCount = request.wordCount, referenceCount = request.referenceCount, originalLanguage = request.originalLanguage)
        val result = scientificArticleRepository.save(entity)
        return ScientificArticleResponse(result.id, result.title, result.author)
    }

    @PreAuthorize("hasAnyRole('EDITOR', 'VIEWER')")
    override fun getAllScientificArticles(): MutableList<ScientificArticle> {
        return scientificArticleRepository.findAll()
    }

    @PreAuthorize("hasRole('EDITOR')")
    override fun deleteScientificArticleById(id: Long) {
        scientificArticleRepository.deleteById(id)
    }

    @PreAuthorize("hasAnyRole('EDITOR', 'VIEWER')")
    override fun getScientificArticleById(id: Long): ScientificArticleResponse {
        val result = scientificArticleRepository.findById(id).orElse(null)
        return ScientificArticleResponse(result.id, result.title, result.author)
    }
}