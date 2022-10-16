package com.example.mcchallengeapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mcchallengeapp.R
import com.example.mcchallengeapp.databinding.ActivityMainBinding
import com.example.mcchallengeapp.ui.pages.PagesAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: PagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        adapter = PagesAdapter(supportFragmentManager, lifecycle)
        binding.pager.adapter = adapter
        tabConfiguration()
    }

    private fun tabConfiguration() {
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.top_movies_title)
                1 -> tab.text = getString(R.string.favourite_movies_title)
            }
        }.attach()
    }
}