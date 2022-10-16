package com.example.mcchallengeapp.ui.pages

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mcchallengeapp.ui.pages.favourites.FavouritesFragment
import com.example.mcchallengeapp.ui.pages.movies.MoviesFragment

class PagesAdapter(fragmentActivity: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentActivity, lifecycle) {
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> {
                FavouritesFragment()
            }
            else -> MoviesFragment()
        }
    }
}