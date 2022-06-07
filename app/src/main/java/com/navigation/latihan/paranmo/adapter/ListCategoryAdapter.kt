
package com.navigation.latihan.paranmo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.navigation.latihan.paranmo.data.Category
import com.navigation.latihan.paranmo.databinding.ItemCategoryBinding

class ListCategoryAdapter (private val listCategory: ArrayList<Category>) : RecyclerView.Adapter<ListCategoryAdapter.ListViewHolder>() {
    class ListViewHolder (binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var frameCat : FrameLayout = binding.Category
        var viewImageCat : ImageView = binding.view7
        var txtCat : TextView = binding.txtCat

    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: ItemCategoryBinding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (frameCat,viewImageCat,txtCat) = listCategory[position]
        holder.frameCat.setBackgroundResource(frameCat)
        holder.viewImageCat.setImageResource(viewImageCat)
        holder.txtCat.text = txtCat}

    override fun getItemCount(): Int =listCategory.size


}


