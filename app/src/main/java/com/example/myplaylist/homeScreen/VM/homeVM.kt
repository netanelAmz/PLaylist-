package com.example.myplaylist.homeScreen.VM

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myplaylist.dataObjects.PlayListObject
import com.example.myplaylist.homeScreen.Repo.homeRepo

class homeVM : ViewModel() {

    //call to the func in repo to start getting the data
    fun getPLayListSongs() {
        homeRepo.getPLayListSongs()
    }

    //observe changes in repository
    //return the songs from repo back to the view
    var songsList: LiveData<PlayListObject> = Transformations.map(homeRepo.playListObject) {
        it
    }

    //observe changes in repository
    //if some error happened
    var msg: LiveData<String> = Transformations.map(homeRepo.msg) {
        it
    }


}