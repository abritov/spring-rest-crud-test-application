package com.example.fabricarestcrudexample240.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("public.product")
data class ProductEntity(
        @Id val id: Long,
        @Column("title") val title: String,
        @Column("description") val description: String,
        @Column("cost") val cost: Number
)