package com.example.fabricarestcrudexample240.controllers

import com.example.fabricarestcrudexample240.entities.ArticleEntity
import com.example.fabricarestcrudexample240.entities.ProductEntity
import com.example.fabricarestcrudexample240.models.ArticleProductPair
import com.example.fabricarestcrudexample240.repositories.ArticleRepository
import com.example.fabricarestcrudexample240.repositories.ProductRepository
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class ArticleController(val articles: ArticleRepository, val products: ProductRepository) {

    @GetMapping("/articles")
    fun getArticles(@RequestParam(required = false) where: Map<String, String>): Flux<ArticleProductPair> {
        return articles.findAll().filter { article ->
                where.all { (fieldName, value) ->
                    when (fieldName) {
                        "id" -> article.id.toString() == value
                        "product_id" -> article.productId.toString() == value
                        "title" -> article.title == value
                        "content" -> article.content == value
                        "created_at" -> article.createdAt.toString() == value
                        else -> throw Exception("unsupported field filter $fieldName")
                    }
                }
            }.flatMap { article ->
                // TODO SELECT queries -> one JOIN
                products.findById(article.productId).map { product -> ArticleProductPair(article, product) }
            }
    }

    @PostMapping("/article")
    fun createArticle(@RequestBody article: ArticleEntity): Mono<ArticleEntity> {
        return articles.save(article)
    }

    @PutMapping("/article")
    fun updateArticle(@RequestBody article: ArticleEntity): Mono<Int> {
        return articles.updateArticle(article.id, article.productId, article.title, article.content)
    }
}