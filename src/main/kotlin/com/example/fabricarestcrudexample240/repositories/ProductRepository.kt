package com.example.fabricarestcrudexample240.repositories

import com.example.fabricarestcrudexample240.entities.ProductEntity
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface ProductRepository: ReactiveCrudRepository<ProductEntity, Long> {
    @Modifying
    @Query("UPDATE public.product SET title = :title, description = :description, cost = :cost where id = :id")
    fun updateProduct(id: Long, title: String, description: String, cost: Number): Mono<Int>
}