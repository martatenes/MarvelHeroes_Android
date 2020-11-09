package com.openbank.marvelheroes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.openbank.marvelheroes.model.Character
import com.openbank.marvelheroes.viewmodel.MainViewModel



class CharactersAdapter(var charactersViewModel: MainViewModel, var resource: Int): RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>(){

    var characters: List<Character>? = null



    class CharacterViewHolder(binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root){
        private var binding: ViewDataBinding? = null

        init {
            this.binding = binding
        }

        fun setDataCard(charactersViewModel: MainViewModel, position: Int){
            binding?.setVariable(com.openbank.marvelheroes.BR.model, charactersViewModel)
            binding?.setVariable(com.openbank.marvelheroes.BR.position, position)
            binding?.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false)

        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.setDataCard(charactersViewModel, position)
    }
    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun getLayoutIdForPosition(position: Int): Int{
        return resource
    }
    override fun getItemCount(): Int {
        return characters?.size ?: 0
    }

    fun setCharacterList(characters: List<Character>) {
        this.characters = characters;
    }
}