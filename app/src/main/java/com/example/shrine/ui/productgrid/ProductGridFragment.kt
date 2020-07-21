package com.example.shrine.ui.productgrid

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shrine.R
import com.example.shrine.data.Product

class ProductGridFragment : Fragment() {

    private lateinit var rvProducts: RecyclerView
    private val productGridAdapter = ProductGridAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_product_grid, container, false)
        setToolbar(view)
        return view
    }

    private fun setToolbar(view: View) {
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_product_grid_fragment, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRvProducts(view)
        val dummyProducts = Product.getDummyProductList(resources)
        productGridAdapter.addAll(products = dummyProducts)

    }

    private fun initRvProducts(view: View) {
        rvProducts = view.findViewById(R.id.rv_products)
        rvProducts.apply {
            layoutManager = GridLayoutManager(
                context, 2, RecyclerView.VERTICAL, false
            )
            adapter = productGridAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.item_search -> {
                Toast.makeText(context, getString(R.string.title_search), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.item_filter -> {
                Toast.makeText(context, getString(R.string.title_filter), Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    }

}