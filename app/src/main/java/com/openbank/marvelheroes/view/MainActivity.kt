package com.openbank.marvelheroes.view

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.openbank.marvelheroes.R
import com.openbank.marvelheroes.databinding.ActivityMainBinding
import com.openbank.marvelheroes.model.Character
import com.openbank.marvelheroes.utils.Constants
import com.openbank.marvelheroes.view.detail.DetailActivity
import com.openbank.marvelheroes.viewmodel.CharactersViewModel

class MainActivity : AppCompatActivity(){

    private var charactersViewModel: CharactersViewModel? = CharactersViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBindings()

        charactersViewModel?.callCharacters()

        //Observables
        charactersViewModel?.getCharacters()?.observe(this, {characters: List<Character> ->
            charactersViewModel?.setCharactersInRecyclerAdapter(characters)
        })

        // Click on a comic
        charactersViewModel?.getSelected()?.observe(this, {character: Character ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(Constants.EXTRA_CHARACTER, character as Parcelable)
            }
            startActivity(intent)
        })
    }


    fun initBindings() {
        val activityMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        charactersViewModel =
            ViewModelProvider.NewInstanceFactory().create(CharactersViewModel::class.java)
        activityMainBinding.model = charactersViewModel
        activityMainBinding.rvCharacters.apply {
            layoutManager = GridLayoutManager(context, 2)
        }
    }






}