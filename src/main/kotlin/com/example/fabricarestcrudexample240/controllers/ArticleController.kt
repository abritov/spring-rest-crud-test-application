package com.example.fabricarestcrudexample240.controllers

import com.example.fabricarestcrudexample240.entities.ArticleEntity
import com.example.fabricarestcrudexample240.repositories.ArticleRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.util.*

@RestController
class ArticleController(val articles: ArticleRepository) {

    @GetMapping("/articles")
    fun getArticles(@RequestParam(required = false) where: Map<String, String>): Flux<ArticleEntity> {
        return articles.findAll().filter { article ->
                where.all { (fieldName, value) ->
                    when (fieldName) {
                        "id" -> article.id.toString() == value
                        "product_id" -> article.productId.toString() == value
                        "title" -> article.title == value
                        "content" -> article.content == value
                        "created_at" -> article.createAt.toString() == value
                        else -> throw Exception("unsupported field filter $fieldName")
                    }
                }
            }
    }
}