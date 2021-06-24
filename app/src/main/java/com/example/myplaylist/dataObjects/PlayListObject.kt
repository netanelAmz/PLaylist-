package com.example.myplaylist.dataObjects

data class PlayListObject(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val pageInfo: PageInfoX
)