package com.example.myplaylist.dataObjects

data class PlaylistItems(
    val etag: String,
    val items: List<ItemX>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo
)