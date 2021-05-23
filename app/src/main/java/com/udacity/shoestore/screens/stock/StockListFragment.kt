package com.udacity.shoestore.screens.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.StockListFragmentBinding
import com.udacity.shoestore.getHtmlSpannedString
import com.udacity.shoestore.models.Shoe

class StockListFragment: Fragment() {
    private lateinit var binding: StockListFragmentBinding
    private lateinit var viewModel: StockListViewModel

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

        viewModel = ViewModelProvider(this).get(StockListViewModel::class.java)
        binding.lifecycleOwner = this

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            minimiseApplication()
        }

        binding.stockListAddItem.setOnClickListener {
            viewModel.onShoeAdded()
        }

        viewModel.stock.observe(viewLifecycleOwner, { list ->
            binding.stockListContentHolder.removeAllViews()
            binding.stockListEmptyListTextView.isVisible = list.isEmpty()
            list.forEach { shoe ->
                addContentView(shoe)
            }
        })

        return binding.root
    }

    private fun minimiseApplication() {
        requireActivity().moveTaskToBack(true)
    }

    private fun addContentView(shoe: Shoe) {
        val inflatedView = layoutInflater.inflate(R.layout.shoe_stock_item, binding.stockListContentHolder, false)
        inflatedView.findViewById<TextView>(R.id.stock_item_name).text =
            resources.getHtmlSpannedString(R.string.stock_item_name_template, shoe.name)
        inflatedView.findViewById<TextView>(R.id.stock_item_size).text =
            resources.getHtmlSpannedString(R.string.stock_item_size_template, shoe.size)
        inflatedView.findViewById<TextView>(R.id.stock_item_company).text =
            resources.getHtmlSpannedString(R.string.stock_item_company_template, shoe.company)
        inflatedView.findViewById<TextView>(R.id.stock_item_description).text =
            resources.getHtmlSpannedString(R.string.stock_item_description_template, shoe.description)

        binding.stockListContentHolder.addView(inflatedView)
    }
}