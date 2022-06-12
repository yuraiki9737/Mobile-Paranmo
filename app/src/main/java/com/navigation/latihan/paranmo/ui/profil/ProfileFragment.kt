package com.navigation.latihan.paranmo.ui.profil

import android.content.Context
import android.content.Intent
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
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.data.storage.PreferenceAkunParanmo
import com.navigation.latihan.paranmo.databinding.FragmentProfileBinding
import com.navigation.latihan.paranmo.model.FactoryViewModel
import com.navigation.latihan.paranmo.model.HomeViewModel
import com.navigation.latihan.paranmo.ui.akun.login.LoginActivity
import com.navigation.latihan.paranmo.ui.profil.informasiaplikasi.InformasiAplikasiActivity



private val Context.dataStoreParanmo: DataStore<Preferences> by preferencesDataStore("paranmo")
@Suppress("SameParameterValue")
class ProfileFragment : Fragment() {


    private var _bindingProfile: FragmentProfileBinding? = null
    private val bindingProfile get() = _bindingProfile

    private lateinit var profile : HomeViewModel

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


    private fun view(){
        val preferences =
            PreferenceAkunParanmo.getInstanceParanmoApp(requireContext().dataStoreParanmo)
        profile = ViewModelProvider(
            this, FactoryViewModel(preferences)
        )[HomeViewModel::class.java]

        profile.getUserParanmo().observe(requireActivity()) { user ->
            if (user.isLogin) {
                bindingProfile?.photoProfile?.setBackgroundResource(R.drawable.logo5_13_14350)
                bindingProfile?.account?.text = user.name
                bindingProfile?.email?.text = user.email
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view()

        bindingProfile?.btnLogout?.setOnClickListener {
            profile.logout()
            loadingSetting(true)
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            requireActivity().startActivity(intent)
            requireActivity().finish()

        }
    }


    private fun loadingSetting(state:Boolean){
        if (state){
            bindingProfile?.progressLogout?.visibility = View.VISIBLE
        }else{
            bindingProfile?.progressLogout?.visibility=View.GONE
        }
    }

    private fun bindingButton(){
        bindingProfile?.btnInsight?.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
        bindingProfile?.iconNext?.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
        bindingProfile?.iconNextDetail?.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
       bindingProfile?.iconCreate?.setOnClickListener {
           Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
       }
        bindingProfile?.lock2?.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
        bindingProfile?.btnParanmo?.setOnClickListener {
            val intent = Intent(requireActivity() , InformasiAplikasiActivity::class.java)
            startActivity(intent)
        }
        bindingProfile?.btnSyarat?.setOnClickListener {

        Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
        bindingProfile?.btnKebijakan?.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }


    }
}


