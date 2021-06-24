package com.example.myplaylist.dataObjects

data class SnippetX(
    val channelId: String,
    val channelTitle: String,
    val defaultLanguage: String,
    val description: String,
    val localized: Localized,
    val publishedAt: String,
    val tags: List<String>,
    val thumbnails: ThumbnailsX,
    val title: String
)