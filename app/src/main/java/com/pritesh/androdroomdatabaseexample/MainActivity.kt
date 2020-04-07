package com.pritesh.androdroomdatabaseexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.pritesh.androdroomdatabaseexample.models.Product


class MainActivity : AppCompatActivity() {


    private var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        // run the sentence in a new thread
        Thread(Runnable {
            val products = App.get().db.productDao().all
            val force = App.get().isForceUpdate
            if (force || products.isEmpty()) {
                retrieveProducts()
            } else {
                populateProducts(products)
            }
        }).start()

    }

    private fun retrieveProducts() {
        val list = ArrayList<Product>()

        for (i in 0..9) {
            val product = Product()
            product.name = getString(R.string.name_format, i.toString())
            product.imageUrl = "http://lorempixel.com/500/500/technics/" + i
            product.price = if (i == 0) 50 else i * 100
            list.add(product)
        }

        // insert product list into database
        App.get().db.productDao().insertAll(list)

        // disable flag for force update
        App.get().isForceUpdate = false

        populateProducts(list)
    }

    private fun populateProducts(products: List<Product>) {
        runOnUiThread { recyclerView?.setAdapter(ProductAdapter(products)) }
    }
}
