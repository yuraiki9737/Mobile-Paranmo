package com.navigation.latihan.paranmo.ui.paranshop.detailproduct

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.data.Product
import com.navigation.latihan.paranmo.data.storage.PreferenceAkunParanmo
import com.navigation.latihan.paranmo.databinding.ActivityDetailProductBinding
import com.navigation.latihan.paranmo.model.FactoryViewModel
import com.navigation.latihan.paranmo.model.ProductViewModel
import com.navigation.latihan.paranmo.adapter.ListProductAdapter
import com.navigation.latihan.paranmo.adapter.ListProductAdapter.Companion.DATA_PRODUCT
import com.navigation.latihan.paranmo.ui.paranshop.ParanShopFragment


private val Context.dataStoreParanmo: DataStore<Preferences> by preferencesDataStore("paranmo")
class DetailProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProductBinding

    private lateinit var listProductAdapter: ListProductAdapter
    private lateinit var productViewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindingButton()
        showRecyclerList()
        viewDetailProduct()
        recyclerViewAllProduct()



    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerViewAllProduct() {
        val preferenceAkunParanmo = PreferenceAkunParanmo.getInstanceParanmoApp(dataStoreParanmo)
        productViewModel = ViewModelProvider(
            this, FactoryViewModel(preferenceAkunParanmo)
        )[ProductViewModel::class.java]

        productViewModel.getUserParanmo().observe(this){ product ->

            productViewModel.setProduct(id = product.id)
        }

        productViewModel.getProduct().observe(this) {
            if (it != null) {
                listProductAdapter.setProduct(it)
                listProductAdapter.notifyDataSetChanged()


            }
        }
    }

    private fun viewDetailProduct() {
         val detailProductActivity = intent.getParcelableExtra<Product>(DATA_PRODUCT) as Product

        Glide.with(this)
            .load(detailProductActivity.image_product)
            .placeholder(R.drawable.ic_place_holder)
            .error(R.drawable.ic_broken_image)
            .into(binding.photo)

        binding.date.text = detailProductActivity.date_product
        binding.price.text = detailProductActivity.price_product
        binding.nameProduct.text = detailProductActivity.name_product_full

        Glide.with(this)
            .load(detailProductActivity.logo_outlite)
            .placeholder(R.drawable.ic_place_holder)
            .error(R.drawable.ic_broken_image)
            .into(binding.logoOutlite)

        binding.nameOutlite.text = detailProductActivity.name_outlite
        binding.starInsight.text = detailProductActivity.insight_product
        binding.timeBooking.text =  detailProductActivity.time_booking_product
        binding.booking.text =  detailProductActivity.booking_product
        binding.descriptionProduct.text = detailProductActivity.description_product
        binding.scoreInsight.text = detailProductActivity.score_product
        binding.starProduct.text = detailProductActivity.star_score_product
        binding.timeProduct.text =  detailProductActivity.time_product_insight
        binding.person.text = detailProductActivity.name_person_product
        binding.descriptionInsight.text = detailProductActivity.detail_insight



    }

    private fun bindingButton() {
        binding.home.setOnClickListener {
            val intent = Intent(this@DetailProductActivity , ParanShopFragment::class.java)
            startActivity(intent)
        }
        binding.toggleButton.setOnClickListener {
            Toast.makeText(this, getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
        binding.Follow.setOnClickListener {
            Toast.makeText(this, getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
        binding.share.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            startActivity(shareIntent)
        }
        binding.cart.setOnClickListener {
            Toast.makeText(this, getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
        binding.buy.setOnClickListener {
            Toast.makeText(this, getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
        binding.chat.setOnClickListener {
            Toast.makeText(this, getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
    }


    private fun showRecyclerList() {
        var row = 1
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            row = 1

        }
        binding.rvProduct.layoutManager =
            StaggeredGridLayoutManager(row, StaggeredGridLayoutManager.HORIZONTAL)
        listProductAdapter = ListProductAdapter()
        binding.rvProduct.adapter = listProductAdapter



    }
}