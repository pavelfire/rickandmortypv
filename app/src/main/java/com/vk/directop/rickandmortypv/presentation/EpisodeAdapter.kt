package com.vk.directop.rickandmortypv.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeDTO
import com.vk.directop.rickandmortypv.databinding.EpisodeItemBinding

class EpisodeAdapter(
    private val actionListener: OnEpisodeListener
) : RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>(), View.OnClickListener {

    inner class EpisodeViewHolder(val binding: EpisodeItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<EpisodeDTO>() {
        override fun areItemsTheSame(oldItem: EpisodeDTO, newItem: EpisodeDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EpisodeDTO, newItem: EpisodeDTO): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var episodes: List<EpisodeDTO>
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

            cardView.setOnClickListener {
                actionListener.onEpisodeClick(episodeRM)
            }
        }
    }

    override fun getItemCount(): Int = episodes.size

    interface OnEpisodeListener {
        fun onEpisodeClick(episode: EpisodeDTO)
    }

    override fun onClick(view: View) {
        val episodeDTO = view.tag as EpisodeDTO
        actionListener.onEpisodeClick(episodeDTO)
    }

}