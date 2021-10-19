package com.hackbyte.newsapplication.domain.models

data class NewsApiModel(
    val articles: List<Article>,
    val status: String, // ok
    val totalResults: Int // 6759
) {
    data class Article(
        val author: String, // Neha Tandon Sharma
        val content: String, // The space race was indeed made of exciting things, but the race happening right here on planet earth is far more interesting. Get hold of the popcorn tubs as we witness the constant back and forth be… [+1636 chars]
        val description: String?, // The space race was indeed made of exciting things, but the race happening right here on planet earth...The post Hogging all the limelight: Move over Bezos, Elon Musk is now worth as much as Bill Gates and Warren Buffett combined with $230 billion to call his…
        val publishedAt: String, // 2021-10-18T13:04:42Z
        val source: Source,
        val title: String, // Hogging all the limelight: Move over Bezos, Elon Musk is now worth as much as Bill Gates and Warren Buffett combined with $230 billion to call his own
        val url: String, // https://luxurylaunches.com/celebrities/elon-musk-the-richest-man-on-earth.php
        val urlToImage: String? // https://luxurylaunches.com/wp-content/uploads/2021/10/elon-musk-is-worth-as-much-as-bill-gates-and-warren-buffett-combined.jpg
    ) {
        data class Source(
            val id: Any?, // null
            val name: String // Luxurylaunches.com
        )
    }
}