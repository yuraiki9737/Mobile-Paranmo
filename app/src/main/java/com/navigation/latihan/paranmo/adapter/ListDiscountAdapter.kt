
package com.navigation.latihan.paranmo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.navigation.latihan.paranmo.data.Discount
import com.navigation.latihan.paranmo.databinding.ItemDiscountBinding

class ListDiscountAdapter (private val listDiscount: ArrayList<Discount>) : RecyclerView.Adapter<ListDiscountAdapter.ListViewHolder>() {
    class ListViewHolder (binding: ItemDiscountBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var frameDis : FrameLayout = binding.discount
        var viewPay : ImageView = binding.viewPayment
        var discount : TextView = binding.txtDiscount
        var description : TextView = binding.txtDes
        var percent : TextView = binding.txtPer
        var button : AppCompatButton = binding.discountProduct
        var viewImage : View = binding.viewImage

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: ItemDiscountBinding =
            ItemDiscountBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (frameDis,viewPay,txtDis,txtDes,txtPer, txtPerStyle,disProd, viewImage) = listDiscount[position]
        holder.frameDis.setBackgroundResource(frameDis)
        holder.viewPay.setImageResource(viewPay)
        holder.discount.text = txtDis
        holder.description.text = txtDes
        holder.percent.text = txtPer
        holder.percent.setTextColor(txtPerStyle)
        holder.button.setBackgroundResource(disProd)
        holder.viewImage.setBackgroundResource(viewImage)
    }

    override fun getItemCount(): Int =listDiscount.size


}


