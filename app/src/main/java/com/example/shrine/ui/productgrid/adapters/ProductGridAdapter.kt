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

class ProductGridAdapter : RecyclerView.Adapter<ProductGridAdapter.ProductGridViewHolder>() {

    private val products = arrayListOf<Product>()

    fun addAll(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductGridViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_card, parent, false)
        return ProductGridViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductGridViewHolder, position: Int) {

        val currentProduct = products[position]
        holder.bind(currentProduct)

    }

    class ProductGridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imgImage: ImageView = itemView.findViewById(R.id.img_product_image)
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_product_title)
        private val tvPrice: TextView = itemView.findViewById(R.id.tv_product_price)

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