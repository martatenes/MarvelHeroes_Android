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
import com.openbank.marvelheroes.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(){

    private var mainViewModel: MainViewModel? = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBindings()

        mainViewModel?.callCharacters()

        //Observables
        mainViewModel?.getCharacters()?.observe(this, {characters: List<Character> ->
            mainViewModel?.setCharactersInRecyclerAdapter(characters)
        })

        // Click on a comic
        mainViewModel?.getSelected()?.observe(this, {character: Character ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(Constants.EXTRA_CHARACTER, character as Parcelable)
            }
            startActivity(intent)
        })
    }


    fun initBindings() {
        val activityMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel =
            ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
        activityMainBinding.model = mainViewModel
        activityMainBinding.rvCharacters.apply {
            layoutManager = GridLayoutManager(context, 2)
        }
    }






}