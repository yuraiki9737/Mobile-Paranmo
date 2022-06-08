package com.navigation.latihan.paranmo.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.app.ActivityOptionsCompat.makeSceneTransitionAnimation
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.data.Article
import com.navigation.latihan.paranmo.databinding.ItemRowHomeBinding
import com.navigation.latihan.paranmo.ui.home.detailplant.DetailPlantActivity

@Suppress("DEPRECATION")
class AdapterHomeFragment : RecyclerView.Adapter<AdapterHomeFragment.HomeViewHolder>() {

    private var listArticle : List<Article>? = null

    fun setArticleList (listArticle: List<Article>?){
        this.listArticle = listArticle
    }

    inner class HomeViewHolder(private val binding : ItemRowHomeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(dataArticle: Article){
            binding.card.setOnClickListener{
                val intentArticle = Intent(itemView.context, DetailPlantActivity::class.java)
                intentArticle.putExtra(AdapterHome.DATA_ARTICLE, dataArticle)

                val optionsCompat : ActivityOptionsCompat = makeSceneTransitionAnimation(
                    itemView.context as Activity,
                    Pair(binding.photo, "image"),
                    Pair(binding.plant, "plant_name"),
                    Pair(binding.latin, "latin_name")
                )

                itemView.context.startActivity(intentArticle, optionsCompat.toBundle())
            }



            binding.apply {
               Glide .with(itemView)
                    .load(dataArticle.photo_url)
                    .placeholder(R.drawable.ic_place_holder)
                    .error(R.drawable.ic_broken_image)
                    .transform(CenterCrop(), RoundedCorners(20))
                    .into(photo)

                plant.text = dataArticle.plant_name
                latin.text = dataArticle.latin_name
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val viewArticle = ItemRowHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(viewArticle)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        listArticle?.let { holder.bind(it[position]) }

    }

    override fun getItemCount()= 5
}