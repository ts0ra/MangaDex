package com.ts0ra.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ts0ra.core.R
import com.ts0ra.core.databinding.ItemListMangaBinding
import com.ts0ra.core.domain.model.Manga

class MangaAdapter : ListAdapter<Manga, MangaAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Manga) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            ItemListMangaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(private var binding: ItemListMangaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Manga) {
            val imageUrl = "https://uploads.mangadex.org/covers/${data.mangaId}/${data.coverArtFile}"
            Glide.with(itemView.context)
                .load(imageUrl)
                .error(R.drawable.cover_error)
                .into(binding.imageCoverArt)
            binding.textTitle.text = data.title
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Manga> =
            object : DiffUtil.ItemCallback<Manga>() {
                override fun areItemsTheSame(oldItem: Manga, newItem: Manga): Boolean {
                    return oldItem.mangaId == newItem.mangaId
                }

                override fun areContentsTheSame(oldItem: Manga, newItem: Manga): Boolean {
                    return oldItem == newItem
                }
            }
    }
}