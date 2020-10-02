package com.example.fabricarestcrudexample240.controllers

import com.example.fabricarestcrudexample240.dto.DeleteArticleEntityByIdDto
import com.example.fabricarestcrudexample240.dto.DeleteProductEntityByIdDto
import com.example.fabricarestcrudexample240.entities.ArticleEntity
import com.example.fabricarestcrudexample240.entities.ProductEntity
import com.example.fabricarestcrudexample240.repositories.ProductRepository
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class ProductController(val products: ProductRepository) {

    @GetMapping("/products")
    fun getProducts(@RequestParam(required = false) where: Map<String, String>): Flux<ProductEntity> {
        return products.findAll().filter { product ->
            where.all { (fieldName, value) ->
                when (fieldName) {
                    "id" -> product.id.toString() == value
                    "title" -> product.title == value
                    "description" -> product.description == value
                    "cost" -> product.cost.toString() == value
                    else -> throw Exception("unsupported field filter $fieldName")
                }
            }
        }
    }

    @PostMapping("/product")
    fun createProduct(@RequestBody product: ProductEntity): Mono<ProductEntity> {
        return products.save(product)
    }

    @PutMapping("/product")
    fun updateProduct(@RequestBody product: ProductEntity): Mono<Int> {
        return products.updateProduct(product.id, product.title, product.description, product.cost)
    }

    @DeleteMapping("/product")
    fun deleteProduct(@RequestBody request: DeleteProductEntityByIdDto): Mono<Void> {
        return products.deleteById(request.productId)
    }
}