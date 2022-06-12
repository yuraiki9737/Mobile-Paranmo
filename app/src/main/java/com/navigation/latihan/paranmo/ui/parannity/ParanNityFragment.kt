package com.navigation.latihan.paranmo.ui.parannity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.adapter.ListParanNityAdapter
import com.navigation.latihan.paranmo.data.ParanNity
import com.navigation.latihan.paranmo.databinding.FragmentParanNityBinding

class ParanNityFragment : Fragment() {

    private var _bindingParanNity: FragmentParanNityBinding? = null
    private val bindingParanNity get() =  _bindingParanNity

    private val listParan = ArrayList<ParanNity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _bindingParanNity = FragmentParanNityBinding.inflate(layoutInflater, container, false)

        bindingButton()
        return bindingParanNity?.root!!
    }

    private fun bindingButton() {
        bindingParanNity?.menu?.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
        bindingParanNity?.chat?.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
        bindingParanNity?.addPerson?.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
        bindingParanNity?.search?.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingParanNity?.rvParan?.setHasFixedSize(true)

        listParan.addAll(paranNity)
        showRecyclerList()

    }

    private val paranNity:ArrayList<ParanNity>
        @SuppressLint("Recycle")
        get() {
            val photo = resources.obtainTypedArray(R.array.photo)
            val name = resources.getStringArray(R.array.name)
            val date = resources.getStringArray(R.array.date)
            val image = resources.obtainTypedArray(R.array.image)
            val like = resources.getStringArray(R.array.like)
            val comment = resources.getStringArray(R.array.comment)
            val share = resources.getStringArray(R.array.share)


            val listParan = ArrayList<ParanNity>()
            for (i in name.indices){
                val paranNity = ParanNity(
                    photo.getResourceId(i, -1),
                    name [i],
                    date[i],
                    image.getResourceId(i,-1),
                    like[i],
                    comment[i],
                    share[i],



                )
                listParan.add(paranNity)

            }
            return listParan
        }
    private fun showRecyclerList() {
        bindingParanNity?.rvParan?.layoutManager = LinearLayoutManager(requireContext())
        val listParanNityAdapter = ListParanNityAdapter(listParan)
        bindingParanNity?.rvParan?.adapter = listParanNityAdapter

    }



}