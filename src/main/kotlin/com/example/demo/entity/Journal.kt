package com.example.demo.entity

import com.example.demo.entity.ScientificArticle
import jakarta.persistence.*

//Сутність, що представляє журнал з відповідними полями
@Entity
@Table(name = "journals")
data class Journal(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    val name: String, //Назва журналу
    val topic: String, //тема журналу
    val language: String, //мова журналу
    val foundingDate: String, //дата заснування журналу
    val issn: String, //ідентифікаційний код журналу
    val price: Double, //ціна журналу
    val isPeriodic: Boolean, //періодичний чи ні журнал
    @OneToMany(mappedBy = "journal", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var articlesList: MutableList<ScientificArticle> = mutableListOf() //список наукових статей у журналі
) : Comparable<Journal> {
    override fun compareTo(other: Journal): Int {
        //Сортування за іменем журналу, а якщо імена різні - то за датою заснування.
        return if (this.name == other.name) {
            this.foundingDate.compareTo(other.foundingDate)
        } else {
            this.name.compareTo(other.name)
        }
    }
}