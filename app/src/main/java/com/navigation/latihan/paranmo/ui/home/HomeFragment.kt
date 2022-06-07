package com.navigation.latihan.paranmo.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.data.storage.PreferenceAkunParanmo
import com.navigation.latihan.paranmo.databinding.FragmentHomeBinding
import com.navigation.latihan.paranmo.model.FactoryViewModel
import com.navigation.latihan.paranmo.model.HomeViewModel
import com.navigation.latihan.paranmo.adapter.AdapterHomeFragment
import com.navigation.latihan.paranmo.ui.home.favorit.FavoritActivity
import com.navigation.latihan.paranmo.ui.home.notif.NotifikasiActivity
import com.navigation.latihan.paranmo.ui.home.result.ResultActivity
import com.navigation.latihan.paranmo.ui.home.search.SearchActivity
import com.navigation.latihan.paranmo.ui.identifikasitanaman.resultidentifikasi.ResultIdentifikasiActivity

private val Context.dataStoreParanmo: DataStore<Preferences> by preferencesDataStore(name = "paranmo")
class HomeFragment : Fragment() {

    private var _bindingHome : FragmentHomeBinding? = null
    private val bindingHome get() =  _bindingHome

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapterHome: AdapterHomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _bindingHome = FragmentHomeBinding.inflate(layoutInflater, container, false)

        bindingButton()
        return bindingHome?.root

    }

    private fun bindingButton(){
        bindingHome?.tbnSearch?.setOnClickListener{
            val intent = Intent(requireActivity() , SearchActivity::class.java)
            startActivity(intent)
        }

        bindingHome?.btnFavorite?.setOnClickListener{
            val intent = Intent(requireActivity() , FavoritActivity::class.java)
            startActivity(intent)
        }

        bindingHome?.btnNotification?.setOnClickListener{
            val intent = Intent(requireActivity() , NotifikasiActivity::class.java)
            startActivity(intent)
        }

        bindingHome?.cardScan?.setOnClickListener{
            val intent = Intent(requireActivity() , ResultIdentifikasiActivity::class.java)
            startActivity(intent)
            Toast.makeText(requireActivity(), getString(R.string.camera_identification), Toast.LENGTH_SHORT).show()
        }

        bindingHome?.showAll?.setOnClickListener{
            val intent = Intent(requireActivity() , ResultActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewInitArticle()
        recyclerViewAllArticle()


    }

    private fun recyclerViewInitArticle() {
        var row = 1
        if ( resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            row = 1
        }

        bindingHome?.rvHome?.layoutManager = StaggeredGridLayoutManager(row, StaggeredGridLayoutManager.HORIZONTAL)
        adapterHome = AdapterHomeFragment()
        bindingHome?.rvHome?.adapter = adapterHome
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerViewAllArticle() {
        val preferencesParanmo = PreferenceAkunParanmo.getInstanceParanmoApp(requireContext().dataStoreParanmo)

        homeViewModel = ViewModelProvider(
            this, FactoryViewModel(preferencesParanmo)
        )[HomeViewModel::class.java]

        homeViewModel.getUserParanmo().observe(requireActivity()){user ->
            homeViewModel.setParanmo(id = user.id)
        }

        homeViewModel.getArticle().observe(requireActivity()){
            if (it != null){
                adapterHome.setArticleList(it)
                adapterHome.notifyDataSetChanged()

            }
        }
    }

}