package com.example.fabricarestcrudexample240.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class ArticleController {

    @GetMapping("/articles")
    fun getArticles(): Flux<String> {
        return Flux.just("hello world")
    }
}