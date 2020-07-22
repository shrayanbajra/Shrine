package com.example.shrine.ui.productgrid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shrine.R
import com.example.shrine.data.Product

/**
 * Adapter used to show an asymmetric grid of products, with 2 items in the first column, and 1
 * item in the second column, and so on.
 */
class StaggeredProductCardAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<StaggeredProductCardAdapter.StaggeredProductCardViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return position % 3
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StaggeredProductCardViewHolder {

        var layoutId = R.layout.shr_staggered_product_card_first
        if (viewType == 1) {

            layoutId = R.layout.shr_staggered_product_card_second

        } else if (viewType == 2) {

            layoutId = R.layout.shr_staggered_product_card_third

        }

        val layoutView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return StaggeredProductCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: StaggeredProductCardViewHolder, position: Int) {

        val currentProduct = productList[position]
        holder.bind(currentProduct)

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class StaggeredProductCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imgImage: ImageView = itemView.findViewById(R.id.img_product_image)
        var tvTitle: TextView = itemView.findViewById(R.id.tv_product_title)
        var tvPrice: TextView = itemView.findViewById(R.id.tv_product_price)

        fun bind(currentProduct: Product) {

            loadImageFromURL(currentProduct.imageUrl)
            tvTitle.text = currentProduct.title
            tvPrice.text = currentProduct.price

        }

        private fun loadImageFromURL(imageURL: String) {
            Glide.with(itemView.context)
                .load(imageURL)
                .centerCrop()
                .placeholder(R.drawable.ic_picture)
                .into(imgImage);
        }

    }
}
