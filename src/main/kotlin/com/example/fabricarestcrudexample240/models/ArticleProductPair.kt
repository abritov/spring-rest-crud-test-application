package com.example.fabricarestcrudexample240.models

import com.example.fabricarestcrudexample240.entities.ArticleEntity
import com.example.fabricarestcrudexample240.entities.ProductEntity

data class ArticleProductPair(val article: ArticleEntity, val product: ProductEntity)