package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class StockViewModel: ViewModel() {
    val stock: LiveData<MutableList<Shoe>>
        get() = _stock

    private val _stock: MutableLiveData<MutableList<Shoe>> = MutableLiveData<MutableList<Shoe>>()

    init {
        _stock.value = mutableListOf()
    }

    fun onShoeAdded() {
        val newItem = Shoe(
            name = "Air Force 1",
            size = 9.5,
            company = "Nike",
            description = "The radiance lives on in the Nike Air Force 1 '07, the b-ball OG that puts a fresh spin on what you know best: a zig-zag Swoosh logo, crisp leather, bold colour pops and the perfect amount of flash to make you shine."
        )
        _stock.value?.add(newItem)
        // Notify observers
        _stock.value = _stock.value
    }
}