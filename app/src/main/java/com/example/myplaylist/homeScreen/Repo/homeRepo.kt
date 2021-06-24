package com.example.myplaylist.homeScreen.Repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myplaylist.dataObjects.PlayListObject
import com.example.weatherapp.Network.RetroService
import kotlinx.coroutines.*

object homeRepo {

    lateinit var job: Job
    var playListObject: MutableLiveData<PlayListObject> = MutableLiveData()
    var msg: MutableLiveData<String> = MutableLiveData()

    fun getPLayListSongs() {
        job = Job()
        job.let {
            CoroutineScope(Dispatchers.IO + it).launch {
                try {
                    //get the data using retrofit library
                    val songsObject = RetroService.api.getPLayListSongs()
                    if (songsObject != null) {
                        withContext(Dispatchers.Main) {
                            // show delay on purpose to show the user the simmer effect
                            delay(2500)
                            playListObject.value = songsObject
                            Log.d("RetrofitResult", songsObject.items.toString())
                        }

                    } else {
                        CoroutineScope(Dispatchers.Main).launch {
                            //in case error happened
                            msg.value = "Fail to load data"
                        }
                    }
                } catch (e: Exception) {
                    CoroutineScope(Dispatchers.Main).launch {
                        //in case exception happened
                        msg.value = "Fail to load data " + e.message
                    }
                }
            }
        }
    }
}