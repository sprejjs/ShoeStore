package com.udacity.shoestore.screens.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.StockListFragmentBinding

class StockListFragment: Fragment() {
    private lateinit var binding: StockListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.stock_list_fragment,
            container,
            false
        )

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            minimiseApplication()
        }

        binding.stockListAddItem.setOnClickListener {
            addContentView()
        }

        return binding.root
    }

    private fun minimiseApplication() {
        requireActivity().moveTaskToBack(true)
    }

    private fun addContentView() {
        val inflatedView = layoutInflater.inflate(R.layout.shoe_stock_item, binding.stockListContentHolder, false)
        inflatedView.findViewById<TextView>(R.id.stock_item_name).text = "Name: Air Force 1."
        inflatedView.findViewById<TextView>(R.id.stock_item_size).text = "Size: 9.5"
        inflatedView.findViewById<TextView>(R.id.stock_item_company).text = "Company: Nike"
        inflatedView.findViewById<TextView>(R.id.stock_item_description).text = "The radiance lives on in the Nike Air Force 1 '07, the b-ball OG that puts a fresh spin on what you know best: a zig-zag Swoosh logo, crisp leather, bold colour pops and the perfect amount of flash to make you shine."

        binding.stockListContentHolder.addView(inflatedView)
    }
}