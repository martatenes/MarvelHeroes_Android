package com.openbank.marvelheroes.view.detail

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.openbank.marvelheroes.R
import com.openbank.marvelheroes.databinding.ActivityDetailBinding
import com.openbank.marvelheroes.model.Character
import com.openbank.marvelheroes.utils.Constants
import com.openbank.marvelheroes.view.comics.ComicsActivity
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var character: Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()

        if (intent.hasExtra(Constants.EXTRA_CHARACTER)) {
                character = intent.getParcelableExtra(Constants.EXTRA_CHARACTER)!!
        }
        else{
            Toast.makeText(this, getString(R.string.TR_NO_HAY_DATOS_SUPERHEROE), Toast.LENGTH_LONG)
                .show()
            finish()
            return
        }

       setDataCharacter()

    }

    fun initUI(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.lifecycleOwner = this@DetailActivity
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    fun setDataCharacter(){
        supportActionBar?.title = character?.name
        val url = character!!.thumbnail.path + "." + character!!.thumbnail.extension
        Picasso.get().load(url).resize(520, 520).centerCrop().into( binding.characterImage)
        binding.characterDataName = character!!.name
        binding.characterDataDescription = character!!.description
        binding.characterDataNumComics = character!!.comics.items?.count().toString()
        binding.characterDataNumSeries = character!!.series.items?.count().toString()
        binding.characterDataNumEvents = character!!.events.items?.count().toString()
        binding.characterDataNumStories = character!!.stories.items?.count().toString()
        binding.activity =  this@DetailActivity
    }

    fun onComicClick(){
        val intent = Intent(this, ComicsActivity::class.java).apply {
            putExtra(Constants.EXTRA_CHARACTER_ID, character?.id)
        }
        startActivity(intent)
    }


}