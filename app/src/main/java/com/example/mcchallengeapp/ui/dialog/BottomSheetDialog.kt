package com.example.mcchallengeapp.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.mcchallengeapp.R
import com.example.mcchallengeapp.data.entity.MoviesResponse
import com.example.mcchallengeapp.databinding.BottomSheetBinding
import com.example.mcchallengeapp.utils.IMAGE_BASE_URL
import com.example.mcchallengeapp.viewmodel.MoviesViewModel
import com.google.android.material.R.id.design_bottom_sheet
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetDialog : BottomSheetDialogFragment() {
    private lateinit var dialog: BottomSheetDialog
    private lateinit var bottomSheet: FrameLayout
    lateinit var binding: BottomSheetBinding
    private lateinit var movie: MoviesResponse
    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.manageFavourite.text = getString(R.string.add_favourite)
        binding.manageFavourite.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.added), Toast.LENGTH_SHORT).show()
            viewModel.insertMovieInDB(movie)
            viewModel.getTopMovies()
            binding.manageFavourite.visibility = View.INVISIBLE
        }
        binding.text.text = movie.title
        val imageUrl = "$IMAGE_BASE_URL${movie.posterPath}"
        Glide.with(requireContext())
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_image_search)
            .error(R.drawable.ic_broken_image)
            .into(binding.image)

        binding.stars.text = "${movie.voteAverage}/10"
        binding.adult.visibility = if(movie.adult) View.VISIBLE else View.GONE
        binding.likes.text = getString(R.string.likes, movie.voteCount.toString())
        binding.back.setOnClickListener {
            dialog.dismiss()
        }
    }

    fun selectedMovie(movie: MoviesResponse) {
        this.movie = movie
    }

    fun setupViewModel(viewModel: MoviesViewModel) {
        this.viewModel = viewModel
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            bottomSheet = dialog.findViewById<View>(design_bottom_sheet) as FrameLayout
        }
        return dialog
    }

}
