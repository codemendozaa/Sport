package com.example.sport.ui.adapter.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sport.core.BaseConcatHolder
import com.example.sport.databinding.ItemSportBinding
import com.example.sport.databinding.SpanishLigaRowBinding
import com.example.sport.ui.adapter.SportAdapter

class SpanishLigaConcatAdapter (private val sportAdapter: SportAdapter):
        RecyclerView.Adapter<BaseConcatHolder<*>>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
            val itemBinding =
                SpanishLigaRowBinding.inflate(LayoutInflater.from(parent.context), parent,false)
            return ConcatViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
            when(holder){
                is ConcatViewHolder ->holder.bind(sportAdapter)
            }
        }

    override fun getItemCount(): Int =1


    private inner class ConcatViewHolder(val binding: SpanishLigaRowBinding):
        BaseConcatHolder<SportAdapter>(binding.root){
        override fun bind(adapter: SportAdapter) {
            binding.rvSpanishLiga.adapter =adapter

        }
    }
}