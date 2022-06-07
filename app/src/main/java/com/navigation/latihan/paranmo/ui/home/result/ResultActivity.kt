package com.navigation.latihan.paranmo.ui.home.result

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.navigation.latihan.paranmo.data.storage.PreferenceAkunParanmo
import com.navigation.latihan.paranmo.databinding.ActivityResultBinding
import com.navigation.latihan.paranmo.model.FactoryViewModel
import com.navigation.latihan.paranmo.model.HomeViewModel
import com.navigation.latihan.paranmo.ui.MainActivity
import com.navigation.latihan.paranmo.adapter.AdapterHome

private val Context.dataStoreParanmo: DataStore<Preferences> by preferencesDataStore(name = "paranmo")
class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapterHome: AdapterHome

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindingButton()

        recyclerViewInitArticle()
        recyclerViewAllArticle()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerViewAllArticle() {
      val preferenceAkunParanmo = PreferenceAkunParanmo.getInstanceParanmoApp(dataStoreParanmo)
        homeViewModel = ViewModelProvider(
            this, FactoryViewModel(preferenceAkunParanmo)
        )[HomeViewModel::class.java]

        homeViewModel.getUserParanmo().observe(this){ article ->
            loadingHome(true)
            homeViewModel.setParanmo(id = article.id)
        }
        homeViewModel.getArticle().observe(this){
            if(it!=null){
                adapterHome.setArticleList(it)
                adapterHome.notifyDataSetChanged()
                loadingHome(false)
            } else{
                loadingHome(false)
            }
        }
    }

    private fun loadingHome(state: Boolean) {
        if (state){
            binding.progressHome.visibility = View.VISIBLE
        }else{
            binding.progressHome.visibility= View.GONE
        }
    }

    private fun recyclerViewInitArticle() {


        binding.result.layoutManager = GridLayoutManager(this,2)
        adapterHome = AdapterHome()
        binding.result.adapter = adapterHome
    }

    private fun bindingButton(){
        binding.backFavorit.setOnClickListener {
            val intent = Intent(this@ResultActivity , MainActivity::class.java)
            startActivity(intent)
        }
    }
}