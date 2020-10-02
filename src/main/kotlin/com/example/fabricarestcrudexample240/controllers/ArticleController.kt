package com.example.fabricarestcrudexample240.controllers

import com.example.fabricarestcrudexample240.entities.ArticleEntity
import com.example.fabricarestcrudexample240.entities.ProductEntity
import com.example.fabricarestcrudexample240.repositories.ArticleRepository
import com.example.fabricarestcrudexample240.repositories.ProductRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class ArticleController(val articles: ArticleRepository, val products: ProductRepository) {

    @GetMapping("/articles")
    fun getArticles(@RequestParam(required = false) where: Map<String, String>): Flux<Pair<ArticleEntity, ProductEntity>> {
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
            }.flatMap { article ->
                // TODO SELECT queries -> one JOIN
                products.findById(article.productId).map { product -> Pair(article, product) }
            }
    }
}