package com.udacity.shoestore.screens.stock

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.StockListFragmentBinding
import com.udacity.shoestore.getHtmlSpannedString
import com.udacity.shoestore.models.Shoe

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

        val viewModel = (requireActivity() as MainActivity).viewModel
        binding.lifecycleOwner = this

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            minimiseApplication()
        }

        binding.stockListAddItem.setOnClickListener {
            findNavController()
                .navigate(StockListFragmentDirections
                    .actionStockListFragmentToAddStockItemFragment())
        }

        viewModel.stock.observe(viewLifecycleOwner, { list ->
            binding.stockListContentHolder.removeAllViews()
            binding.stockListEmptyListTextView.isVisible = list.isEmpty()
            list.forEach { shoe ->
                addContentView(shoe)
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout_button) {
            findNavController().popBackStack(R.id.loginFragment, false)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
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