package com.example.demo.dto

import com.example.demo.entity.ScientificArticle

data class JournalRequest(
    val name: String,
    val topic: String,
    val language: String,
    val foundingDate: String,
    val issn: String,
    val price: Double,
    val isPeriodic: Boolean,
    var articlesList: MutableList<ScientificArticle>
)

data class JournalResponse(
    var id: Long,
    var name: String,
    val topic: String,
    val price: Double
)

data class ScientificArticleRequest(
    val title:String,
    val author:String,
    val dateWritten : String,
    val wordCount:Int,
    val referenceCount:Int,
    val originalLanguage:Boolean,
)

data class ScientificArticleResponse(
    var id: Long,
    val title:String,
    val author:String,
)