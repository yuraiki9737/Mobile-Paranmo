
package com.navigation.latihan.paranmo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.navigation.latihan.paranmo.data.ParanNity
import com.navigation.latihan.paranmo.databinding.ItemParannityBinding
import de.hdodenhof.circleimageview.CircleImageView

class ListParanNityAdapter (private val listParanNity: ArrayList<ParanNity>) : RecyclerView.Adapter<ListParanNityAdapter.ListViewHolder>() {
    class ListViewHolder (binding: ItemParannityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var photo : CircleImageView = binding.view7
        var name : TextView = binding.name
        var date : TextView = binding.date
        var image : ShapeableImageView = binding.photo
        var like : TextView = binding.textLike
        var comment : TextView = binding.textComment
        var share : TextView = binding.textShare


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: ItemParannityBinding =
            ItemParannityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (photo, name, date, image,like, comment,share) = listParanNity[position]
        holder.photo.setImageResource(photo)
        holder.name.text = name
        holder.date.text = date
        holder.image.setImageResource(image)
        holder.like.text = like
        holder.comment.text = comment
        holder.share.text = share
    }

    override fun getItemCount(): Int =listParanNity.size


}


