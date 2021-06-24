package com.example.myplaylist.homeScreen

import android.app.AlertDialog
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myplaylist.R
import com.example.myplaylist.dataObjects.Item
import com.example.myplaylist.homeScreen.VM.homeVM
import com.example.myplaylist.homeScreen.adapters.PlayListRecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeScreenView : Fragment() , PlayListRecyclerView.OnItemClickListener {
    lateinit var viewModel: homeVM
    lateinit var navController: NavController
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var shimmerFrameLayout: ShimmerFrameLayout

    var arrayList: ArrayList<Item> = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        viewModel = ViewModelProvider(this).get(homeVM::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_screen_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        init(view)


        getPLayListSongs()
        observeFromVM()
        shimmerFrameLayout.startShimmerAnimation()




    }

    private fun init(view: View) {
        shimmerFrameLayout = view.findViewById(R.id.shimmerFrameLayout)
        recyclerView = view.findViewById(R.id.rv_home)
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

    }


    private fun observeFromVM() {
        viewModel.songsList.observe(viewLifecycleOwner,{

            shimmerFrameLayout.stopShimmerAnimation()
            shimmerFrameLayout.visibility = View.GONE
            arrayList = it.items as ArrayList<Item>
            recyclerView.adapter = PlayListRecyclerView(it.items as ArrayList<Item>,requireContext(),this)
        })

        viewModel.msg.observe(viewLifecycleOwner,{
            shimmerFrameLayout.stopShimmerAnimation()
            shimmerFrameLayout.visibility = View.GONE
            Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
        })
    }

    private fun getPLayListSongs() {
        CoroutineScope(Dispatchers.Main).launch {
            println("Debuging {${Thread.currentThread().name}} :+ getCatalog()")
            viewModel.getPLayListSongs()
        }
    }

    override fun onItemClick(i: Int ) {

    }

    override fun onSongClick(i: Int , pos:Int) {
        var chennelID = arrayList.get(pos).playlistItems.items.get(i).snippet.resourceId.videoId
        openDialog(chennelID)

    }

    private fun openDialog(chennelID: String) {

        val dialogBuilder = AlertDialog.Builder(requireContext())
        val inflater = this.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.customplayer, null)
        dialogBuilder.setView(dialogView)
        val youTubePlayerView: YouTubePlayerView = dialogView.findViewById(R.id.youtube_player_view)
        lifecycle.addObserver(youTubePlayerView)

        try {
            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = chennelID!!
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })
        } catch (e: Exception) {
            e.message;
        }

        dialogBuilder.setCancelable(true)
        var b : AlertDialog = dialogBuilder.create()
        b.show()
    }


}