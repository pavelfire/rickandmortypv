package com.vk.directop.rickandmortypv.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeDto
import com.vk.directop.rickandmortypv.databinding.EpisodeItemBinding

class EpisodeAdapter(
    private val actionListener: OnEpisodeListener
) : RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>(), View.OnClickListener {

    inner class EpisodeViewHolder(val binding: EpisodeItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val episodes: ArrayList<EpisodeDto> = arrayListOf()

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

    override fun onClick(view: View) {
        val episodeDTO = view.tag as EpisodeDto
        actionListener.onEpisodeClick(episodeDTO)
    }

    fun submitUpdate(update: List<EpisodeDto>) {
        val callback = EpisodesDiffCallback(episodes, update)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(callback)

        episodes.clear()
        episodes.addAll(update)
        diffResult.dispatchUpdatesTo(this)
    }

    class EpisodesDiffCallback(
        private val oldLocations: List<EpisodeDto>,
        private val newLocations: List<EpisodeDto>
    ) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldLocations.size
        }

        override fun getNewListSize(): Int {
            return newLocations.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldLocations[oldItemPosition].id == newLocations[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldLocations[oldItemPosition].url == newLocations[newItemPosition].url
        }
    }

    interface OnEpisodeListener {
        fun onEpisodeClick(episode: EpisodeDto)
    }

}