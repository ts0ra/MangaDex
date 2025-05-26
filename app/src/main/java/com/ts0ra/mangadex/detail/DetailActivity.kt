package com.ts0ra.mangadex.detail

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat.getParcelableExtra
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ts0ra.core.domain.model.Manga
import com.ts0ra.mangadex.R
import com.ts0ra.mangadex.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setupViewBinding()

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mangaDetail = getParcelableExtra(intent, EXTRA_DATA, Manga::class.java)

        setupMangaDetail(mangaDetail)
        setupFavoriteButton(mangaDetail)
        setupTopAppBar()
    }

    private fun setupTopAppBar() {
        binding.detailTopAppBar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupFavoriteButton(manga: Manga?) {
        if (manga == null) {
            binding.root.visibility = View.GONE
            binding.viewError.root.visibility = View.VISIBLE
            binding.viewError.tvError.text = getString(R.string.something_wrong)

            return
        }

        var statusFavorite = detailViewModel.mangaDetail?.isFavorite
        setStatusFavorite(statusFavorite)
        binding.btnFavorite.setOnClickListener {
            statusFavorite = !statusFavorite!!
            detailViewModel.setFavoriteManga(manga, statusFavorite)
            detailViewModel.mangaDetail?.isFavorite = statusFavorite
            setStatusFavorite(statusFavorite)
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean?) {
        if (statusFavorite == true) {
            binding.btnFavorite.setImageResource(R.drawable.baseline_favorite_24)
        } else {
            binding.btnFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
        }
    }

    private fun setupMangaDetail(manga: Manga?) {
        if (manga == null) {
            binding.root.visibility = View.GONE
            binding.viewError.root.visibility = View.VISIBLE
            binding.viewError.tvError.text = getString(R.string.something_wrong)

            return
        }

        binding.tvTitle.text = manga.title
        val imageUrl = "https://uploads.mangadex.org/covers/${manga.mangaId}/${manga.coverArtFile}"
        val cropOptions = RequestOptions()
            .error(R.drawable.cover_error)
            .centerCrop()
        Glide.with(this)
            .load(imageUrl)
            .apply(cropOptions)
            .into(binding.imageCoverArt)
        binding.tvDescription.text = manga.description
        detailViewModel.mangaDetail = manga
    }

    private fun setupViewBinding() {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}