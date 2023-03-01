package com.vk.directop.rickandmortypv.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vk.directop.rickandmortypv.data.remote.data_transfer_object.episode.EpisodeRM
import com.vk.directop.rickandmortypv.databinding.EpisodeItemBinding

class EpisodeAdapter : RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    inner class EpisodeViewHolder(val binding: EpisodeItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<EpisodeRM>() {
        override fun areItemsTheSame(oldItem: EpisodeRM, newItem: EpisodeRM): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EpisodeRM, newItem: EpisodeRM): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var episodes: List<EpisodeRM>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpisodeAdapter.EpisodeViewHolder {
        return EpisodeViewHolder(
            EpisodeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EpisodeAdapter.EpisodeViewHolder, position: Int) {
        holder.binding.apply {
            val episodeRM = episodes[position]
            tvName.text = episodeRM.name
            tvEpisode.text = episodeRM.episode
            tvAirDate.text = episodeRM.air_date
        }
    }

    override fun getItemCount(): Int = episodes.size

}