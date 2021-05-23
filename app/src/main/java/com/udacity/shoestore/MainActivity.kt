package com.udacity.shoestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: StockViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        viewModel = ViewModelProvider(this).get(StockViewModel::class.java)
    }
}
