package com.example.mcchallengeapp.ui.pages.movies

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mcchallengeapp.R
import com.example.mcchallengeapp.databinding.FragmentMoviesBinding
import com.example.mcchallengeapp.ui.dialog.BottomSheetDialog
import com.example.mcchallengeapp.viewmodel.MoviesViewModel
import com.example.mcchallengeapp.ui.adapter.ListMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {
    private lateinit var binding: FragmentMoviesBinding
    private val adapter = ListMoviesAdapter()
    private val moviesViewModel: MoviesViewModel by viewModels()
    private val bottomSheet = BottomSheetDialog()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        initializeRecyclerView()
        registerListeners()
        registerObservers()
    }

    override fun onResume() {
        super.onResume()
        moviesViewModel.getTopMovies()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.search)

        searchItem.setShowAsAction(
            MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM
        )
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                newText.let { moviesViewModel.getSearchedMovie(newText.toString()) }
                return true
            }
        })
    }

    private fun initializeRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerView.adapter = adapter
    }

    private fun registerListeners() {
        adapter.onClick = {
            bottomSheet.show(parentFragmentManager, "BottomSheetList")
            bottomSheet.setupViewModel(moviesViewModel)
            bottomSheet.selectedMovie(it)
        }
    }

    private fun registerObservers() {
        moviesViewModel.movies.observe(viewLifecycleOwner, Observer {
            adapter.addMovies(null)
            adapter.addMovies(it)
            binding.recyclerView.visibility = View.VISIBLE
            binding.loading.visibility = View.GONE
        })
    }
}