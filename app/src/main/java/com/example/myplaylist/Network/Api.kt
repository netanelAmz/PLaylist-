package com.example.weatherapp.Network


import com.example.myplaylist.dataObjects.PlayListObject

import retrofit2.http.GET


interface Api {

    //get request to the api and getting back PlayListObject object as response
    @GET("playlists.json")
    suspend fun getPLayListSongs(): PlayListObject


}