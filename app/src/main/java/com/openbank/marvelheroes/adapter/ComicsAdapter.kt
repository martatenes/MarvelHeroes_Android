package com.openbank.marvelheroes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.openbank.marvelheroes.model.Comic
import com.openbank.marvelheroes.viewmodel.ComicsViewModel

class ComicsAdapter(var comicsViewModel: ComicsViewModel, var resource: Int): RecyclerView.Adapter<ComicsAdapter.ComicViewHolder>(){

    var comics: List<Comic>? = null;

    class ComicViewHolder(binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root){
        private var binding: ViewDataBinding? = null

        init {
            this.binding = binding
        }

        fun setDataComic(comicsViewModel: ComicsViewModel, position: Int){
            binding?.setVariable(com.openbank.marvelheroes.BR.model, comicsViewModel)
            binding?.setVariable(com.openbank.marvelheroes.BR.position, position)
            binding?.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false)

        return ComicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.setDataComic(comicsViewModel, position);
    }
    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun getLayoutIdForPosition(position: Int): Int{
        return resource
    }
    override fun getItemCount(): Int {
        return comics?.size ?: 0
    }

    fun setComicList(comics: List<Comic>) {
        this.comics = comics;
    }

}