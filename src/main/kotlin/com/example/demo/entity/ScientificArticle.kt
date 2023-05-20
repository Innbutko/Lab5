package com.example.demo.entity

import com.example.demo.entity.Journal
import jakarta.persistence.*

//Сутність, що представляє наукову статтю з відповідними полями
@Entity
@Table(name = "scientific_articles")
data class ScientificArticle(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 1,
    val title:String, //заголовок статті
    val author:String, //автор статті
    val dateWritten : String, //дата написання статті
    val wordCount:Int, //кількість слів у статті
    val referenceCount:Int, //кількість посилань у статті
    val originalLanguage:Boolean, //оригінальна мова статті
    @ManyToOne()
    @JoinColumn(name = "journal_id")
    var journal: Journal? = null
) : Comparable<ScientificArticle> {
    override fun compareTo(other: ScientificArticle): Int {
        //Сортування за датою написання. Якщо збігається - за заголовком
        return if (this.dateWritten == other.dateWritten) {
            this.title.compareTo(other.title)
        } else {
            this.dateWritten.compareTo(other.dateWritten)
        }
    }

}

