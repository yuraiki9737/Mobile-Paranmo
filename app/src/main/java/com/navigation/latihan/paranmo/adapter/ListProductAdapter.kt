package com.navigation.latihan.paranmo.adapter

import android.app.Activity
import android.app.SharedElementCallback
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.app.ActivityOptionsCompat.makeSceneTransitionAnimation
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.data.Product
import com.navigation.latihan.paranmo.databinding.ItemProductBinding
import com.navigation.latihan.paranmo.ui.paranshop.detailproduct.DetailProductActivity

class ListProductAdapter: RecyclerView.Adapter<ListProductAdapter.ListViewHolder>() {

    private var productList : List<Product>? = null

    fun setProduct (productList: List<Product>?){
        this.productList = productList
    }

    inner class ListViewHolder (private val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(dataProduct: Product){
            binding.cardView.setOnClickListener {
                val intentProduct = Intent(itemView.context, DetailProductActivity::class.java)
                intentProduct.putExtra(DATA_PRODUCT, dataProduct)

                val optionsCompat : ActivityOptionsCompat = makeSceneTransitionAnimation(
                    itemView.context as Activity,
                    Pair(binding.photo, "image"),
                    Pair(binding.plant, "name_product"),
                    Pair(binding.price, "price_product"),
                    Pair(binding.textLocation, "location"),
                    Pair(binding.textStar, "star_product"),

                )
                itemView.context.startActivity(intentProduct, optionsCompat.toBundle())
            }

            binding.apply {
                Glide.with(itemView)
                    .load(dataProduct.image_product)
                    .placeholder(R.drawable.ic_place_holder)
                    .error(R.drawable.ic_broken_image)
                    .centerCrop()
                    .into(photo)

                plant.text = dataProduct.name_product
                price.text = dataProduct.price_product
                textLocation.text = dataProduct.location
                textStar.text =  dataProduct.star_product
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val viewProduct = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(viewProduct)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(productList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return if(productList == null)0
        else productList?.size!!
    }

    companion object{
        const val DATA_PRODUCT = "dataProduct"
    }

}


