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

    fun addShoe(name: String,
                size: String,
                company: String,
                description: String) {
        size.toDoubleOrNull()?.let { sizeAsDouble ->
            val newItem = Shoe(name, sizeAsDouble, company, description)
            _stock.value?.add(newItem)
            // Notify observers
            _stock.value = _stock.value
        }
    }
}