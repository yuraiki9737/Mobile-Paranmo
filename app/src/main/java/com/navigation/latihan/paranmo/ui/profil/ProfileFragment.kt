package com.navigation.latihan.paranmo.ui.profil

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.databinding.FragmentHomeBinding
import com.navigation.latihan.paranmo.databinding.FragmentProfileBinding
import com.navigation.latihan.paranmo.ui.home.favorit.FavoritActivity
import com.navigation.latihan.paranmo.ui.home.notif.NotifikasiActivity
import com.navigation.latihan.paranmo.ui.home.result.ResultActivity
import com.navigation.latihan.paranmo.ui.home.search.SearchActivity
import com.navigation.latihan.paranmo.ui.identifikasitanaman.cameraidentifikasi.CameraIdentifikasiActivity
import com.navigation.latihan.paranmo.ui.profil.informasiaplikasi.InformasiAplikasiActivity

class ProfileFragment : Fragment() {


    private var _bindingProfile: FragmentProfileBinding? = null
    private val bindingProfile get() = _bindingProfile


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _bindingProfile = FragmentProfileBinding.inflate(layoutInflater, container, false)

        bindingButton()
        return bindingProfile?.root
    }

    private fun bindingButton(){
        bindingProfile?.lock1?.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
        bindingProfile?.lock2?.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
        bindingProfile?.paranmo?.setOnClickListener {
            val intent = Intent(requireActivity() , InformasiAplikasiActivity::class.java)
            startActivity(intent)
        }
        bindingProfile?.frameSyarat?.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
        bindingProfile?.frameKebijakan?.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }

        bindingProfile?.ulasan?.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
    }
}