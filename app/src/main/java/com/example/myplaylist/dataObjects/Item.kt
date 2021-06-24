package com.example.myplaylist.dataObjects

data class Item(
    val contentDetails: ContentDetails,
    val etag: String,
    val id: String,
    val kind: String,
    val playlistItems: PlaylistItems,
    val snippet: SnippetX
)