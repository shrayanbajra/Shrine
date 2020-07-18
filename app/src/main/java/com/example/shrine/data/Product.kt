package com.example.shrine.data

import android.content.res.Resources
import com.example.shrine.R
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.util.*

data class Product(

    @SerializedName("price")
    var price: String = "",

    @SerializedName("title")
    var title: String = "",

    @SerializedName("url")
    var imageUrl: String = ""

) {

    companion object {
        /**
         * Loads a raw JSON at R.raw.products and converts it into a list of Product objects
         */
        fun getDummyProductList(resources: Resources): List<Product> {
            val inputStream = resources.openRawResource(R.raw.products)
            val jsonProductsString = inputStream.bufferedReader().use(BufferedReader::readText)
            val gson = Gson()
            val productListType = object : TypeToken<ArrayList<Product>>() {}.type
            return gson.fromJson<List<Product>>(jsonProductsString, productListType)
        }
    }

}