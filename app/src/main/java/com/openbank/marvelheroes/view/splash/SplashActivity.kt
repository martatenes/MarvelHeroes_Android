package com.openbank.marvelheroes.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.openbank.marvelheroes.R
import com.openbank.marvelheroes.databinding.ActivitySplashBinding
import com.openbank.marvelheroes.view.MainActivity
import com.openbank.marvelheroes.viewmodel.SplashViewModel

class SplashActivity : AppCompatActivity() {

    var viewModel: SplashViewModel? = SplashViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBindings()
        viewModel?.setIsLoading(true)


        Handler(Looper.getMainLooper()).postDelayed({
            viewModel?.setIsLoading(false)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }, 2000)


    }

    fun initBindings() {
        val binding: ActivitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        viewModel = ViewModelProvider.NewInstanceFactory().create(SplashViewModel::class.java)
        binding.model = viewModel
    }
}