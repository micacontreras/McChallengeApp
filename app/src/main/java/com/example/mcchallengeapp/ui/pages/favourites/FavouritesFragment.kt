package com.example.mcchallengeapp.ui.pages.favourites

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mcchallengeapp.databinding.FragmentFavouritesBinding
import com.example.mcchallengeapp.ui.adapter.FavouriteMoviesAdapter
import com.example.mcchallengeapp.ui.dialog.BottomSheetDialog
import com.example.mcchallengeapp.viewmodel.MoviesViewModel
import com.example.mcchallengeapp.ui.adapter.ListMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : Fragment() {
    private lateinit var binding: FragmentFavouritesBinding
    private val adapter = FavouriteMoviesAdapter()
    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRecyclerView()
        registerListeners()
    }

    override fun onResume() {
        super.onResume()
        moviesViewModel.getFavouriteMovies()
        registerObservers()
    }

    private fun initializeRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerView.adapter = adapter
    }

    private fun registerListeners() {
        adapter.deleteFav = {
            moviesViewModel.deleteMovieInDB(it.id)
            moviesViewModel.getFavouriteMovies()
        }
    }

    private fun registerObservers() {
        moviesViewModel.movies.observe(viewLifecycleOwner, Observer {
            adapter.addMovies(null)
            if(it.isNullOrEmpty()){
                binding.messageEmptyListLayout.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
                binding.loading.visibility = View.GONE
            } else {
                adapter.addMovies(it)
                binding.messageEmptyListLayout.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                binding.loading.visibility = View.GONE
            }
        })
    }

}