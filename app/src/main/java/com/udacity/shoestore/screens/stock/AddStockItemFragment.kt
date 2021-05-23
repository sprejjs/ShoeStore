package com.udacity.shoestore.screens.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.AddStockItemFragmentBinding

class AddStockItemFragment: Fragment() {
    private lateinit var binding: AddStockItemFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_stock_item_fragment,
            container,
            false
        )

        binding.addStockItemCancelButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.addStockItemSaveButton.setOnClickListener {
            addNewItem()
        }

        return binding.root
    }

    private fun addNewItem() {
        val viewModel = (requireActivity() as MainActivity).viewModel
        viewModel.addShoe(
            name = binding.addStockItemName.editText?.text.toString(),
            size = binding.addStockItemSize.editText?.text.toString(),
            company = binding.addStockItemSize.editText?.text.toString(),
            description = binding.addStockItemDescription.editText?.text.toString()
        )

        findNavController().popBackStack()
    }
}