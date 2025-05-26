package com.ts0ra.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.ts0ra.core.ui.MangaAdapter
import com.ts0ra.favorite.databinding.ActivityFavoriteBinding
import com.ts0ra.mangadex.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setupViewBinding()

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadKoinModules(favoriteModule)

        val mangaAdapter = MangaAdapter()

        setupRecyclerView(mangaAdapter)
        setupRecyclerViewContent(mangaAdapter)
        setupItemOnClick(mangaAdapter)
        setupTopAppBar()
    }

    private fun setupTopAppBar() {
        binding.detailTopAppBar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupItemOnClick(mangaAdapter: MangaAdapter) {
        mangaAdapter.onItemClick = { manga ->
            val detailIntent = Intent(this, DetailActivity::class.java)
            detailIntent.putExtra(DetailActivity.EXTRA_DATA, manga)
            startActivity(detailIntent)
        }
    }

    private fun setupRecyclerViewContent(mangaAdapter: MangaAdapter) {
        favoriteViewModel.favoriteManga.observe(this) { manga ->
            mangaAdapter.submitList(manga)
            binding.viewEmpty.root.visibility =
                if (manga.isNotEmpty()) View.GONE else View.VISIBLE
        }
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

    private fun setupViewBinding() {
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}