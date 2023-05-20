package com.example.demo.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.example.demo.entity.Journal
import com.example.demo.entity.ScientificArticle
import org.springframework.stereotype.Repository

@Repository
interface JournalRepository: JpaRepository<Journal, Long>
@Repository
interface ScientificArticleRepository: JpaRepository<ScientificArticle, Long>