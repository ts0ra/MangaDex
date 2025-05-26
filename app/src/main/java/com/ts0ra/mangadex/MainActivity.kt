package com.ts0ra.mangadex

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.ts0ra.core.data.Resource
import com.ts0ra.core.ui.MangaAdapter
import com.ts0ra.mangadex.databinding.ActivityMainBinding
import com.ts0ra.mangadex.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setupViewBinding()

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mangaAdapter = MangaAdapter()

        setupRecyclerView(mangaAdapter)
        setupRecyclerViewContent(mangaAdapter)
        setupItemOnClick(mangaAdapter)
        navigateToFavorite()
    }

    private fun navigateToFavorite() {
        binding.btnFavorite.setOnClickListener {
            val uri = Uri.parse("mangadex://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    private fun setupItemOnClick(mangaAdapter: MangaAdapter) {
        mangaAdapter.onItemClick = { manga ->
            val detailIntent = Intent(this, DetailActivity::class.java)
            detailIntent.putExtra(DetailActivity.EXTRA_DATA, manga)
            startActivity(detailIntent)
        }
    }

    private fun setupViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupRecyclerView(mangaAdapter: MangaAdapter) {
        with(binding.rvManga) {
            layoutManager = GridLayoutManager(context, 2).apply {
                // Center items when there's not enough items to fill the row
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        val totalItems = mangaAdapter.itemCount
                        val lastRowStartPosition = totalItems - (totalItems % spanCount)
                        val itemsInLastRow = totalItems % spanCount
                        
                        // If this is the last row and it has fewer items than span count
                        if (position >= lastRowStartPosition && itemsInLastRow != 0) {
                            // Center the items by adjusting span size
                            return if (itemsInLastRow == 1) 2 else 1
                        }
                        return 1
                    }
                }
            }
            setHasFixedSize(true)
            adapter = mangaAdapter
        }
    }

    private fun setupRecyclerViewContent(mangaAdapter: MangaAdapter) {
        mainViewModel.manga.observe(this) { manga ->
            if (manga != null) {
                when (manga) {
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            manga.message ?: getString(R.string.something_wrong)
                    }
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        mangaAdapter.submitList(manga.data)
                    }
                }
            }
        }
    }
}