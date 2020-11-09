package com.openbank.marvelheroes.view.comics

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.openbank.marvelheroes.R
import com.openbank.marvelheroes.databinding.ActivityComicsBinding
import com.openbank.marvelheroes.model.Comic
import com.openbank.marvelheroes.utils.Constants
import com.openbank.marvelheroes.viewmodel.ComicsViewModel

class ComicsActivity : AppCompatActivity() {

    private var comicsViewModel: ComicsViewModel? = ComicsViewModel()
    private var characterId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comics)

        initUI()
        initBindings()

        if (intent.hasExtra(Constants.EXTRA_CHARACTER_ID)) {
            characterId = intent.getLongExtra(Constants.EXTRA_CHARACTER_ID, 0)
        }
        else{
            Toast.makeText(this, getString(R.string.TR_NO_HAY_DATOS_SUPERHEROE), Toast.LENGTH_LONG)
                .show()
            finish()
            return
        }
        comicsViewModel?.callComics(characterId)

        //Observables
        comicsViewModel?.getComics()?.observe(this, {comics: List<Comic> ->
            comicsViewModel?.setComicsInRecyclerAdapter(comics)
        })
    }


    fun initUI(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(R.string.TR_COMICS)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    fun initBindings() {
        val binding: ActivityComicsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_comics)

        comicsViewModel = ViewModelProvider.NewInstanceFactory().create(ComicsViewModel::class.java)
        binding.model = comicsViewModel
        binding.rvComics.apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }
}