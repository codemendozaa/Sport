package com.example.sport.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.sport.R
import com.example.sport.core.BaseViewHolder
import com.example.sport.data.model.Teams
import com.example.sport.databinding.ItemSportBinding

class SportAdapter (private val teamList: List<Teams>, private val itemClickListener:OnMovieClickListener):
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMovieClickListener{
        fun onMovieClick(team:Teams)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemSportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = TeamsViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf {it != DiffUtil.DiffResult.NO_POSITION}
                ?: return@setOnClickListener
            itemClickListener.onMovieClick(teamList[position])
        }

        return holder
    }


    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is TeamsViewHolder -> holder.bind(teamList[position])
        }
    }



    override fun getItemCount(): Int =teamList.size


    private inner class TeamsViewHolder(val binding: ItemSportBinding,val contex: Context):BaseViewHolder<Teams>(binding.root){
        override fun bind(item: Teams) {
            Glide.with(contex)
                .load(item.strTeamBadge)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_cloud_download)
                .error(R.drawable.ic_baseline_broken_image)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        // log exception
                        Log.e("TAG", "Error loading image", e)
                        return false // important to return false so the error placeholder can be placed
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable?>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .into(binding.logo)


            binding.Name.text = item.strTeam
            binding.stadium.text = item.strStadium

        }

    }
}