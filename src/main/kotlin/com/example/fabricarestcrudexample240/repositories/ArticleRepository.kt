package com.example.fabricarestcrudexample240.repositories

import com.example.fabricarestcrudexample240.entities.ArticleEntity
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveSortingRepository
import reactor.core.publisher.Mono
import java.time.ZonedDateTime


interface ArticleRepository: ReactiveSortingRepository<ArticleEntity, Long> {
    @Modifying
    @Query("UPDATE public.article SET product_id = :productId, title = :title, content = :content where id = :id")
    fun updateArticle(id: Long, productId: Long, title: String, content: String): Mono<Int>
}