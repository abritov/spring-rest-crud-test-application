package com.example.fabricarestcrudexample240.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column

data class ArticleEntity(
        @Id val id: Long,
        @Column("product_id") val productId: Long,
        @Column("title") val title: String,
        @Column("content") val content: String,
        @Column("created_at") val createAt: String
)