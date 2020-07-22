package com.example.shrine.ui.productgrid

import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shrine.R
import com.example.shrine.data.Product
import com.example.shrine.ui.NavigationIconClickListener
import com.example.shrine.ui.productgrid.adapters.ProductGridAdapter
import com.example.shrine.ui.productgrid.adapters.StaggeredProductGridAdapter

class ProductGridFragment : Fragment() {

    private lateinit var toolbar: Toolbar

    private lateinit var rvProducts: RecyclerView
    private val productGridAdapter = ProductGridAdapter()
    private val staggeredGridAdapter = StaggeredProductGridAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_product_grid, container, false)
        setToolbar(view)
        return view
    }

    private fun setToolbar(view: View) {
        toolbar = view.findViewById(R.id.toolbar)
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

    }

    private fun initRvProducts(view: View) {
        rvProducts = view.findViewById(R.id.rv_products)
        rvProducts.apply {

            layoutManager = getGridLayoutManager()
            adapter = staggeredGridAdapter
            addItemDecoration(getItemDecoration())

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                background = context?.getDrawable(R.drawable.product_grid_background_shape)
            }
        }
    }

    private fun getGridLayoutManager(): GridLayoutManager {
        val gridLayoutManager = GridLayoutManager(
            context, 2, RecyclerView.HORIZONTAL, false
        )
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {

            override fun getSpanSize(position: Int): Int {

                return if (position % 3 == 2) 2 else 1

            }
        }
        return gridLayoutManager
    }

    private fun getItemDecoration(): ProductGridItemDecoration {
        val largePadding = resources
            .getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_large)
        val smallPadding = resources
            .getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_small)

        return ProductGridItemDecoration(largePadding, smallPadding)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val dummyProducts = Product.getDummyProductList(resources)
        productGridAdapter.addAll(products = dummyProducts)
        staggeredGridAdapter.addAll(products = dummyProducts)

        navigationIconClickListener()
    }

    private fun navigationIconClickListener() {
        toolbar.setNavigationOnClickListener(
            NavigationIconClickListener(
                context = requireContext(),
                sheet = rvProducts,
                interpolator = AccelerateDecelerateInterpolator(),
                openIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_branded_menu),
                closeIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_close_menu)
            )
        )
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