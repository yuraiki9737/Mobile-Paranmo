package com.navigation.latihan.paranmo.ui.paranshop

import android.annotation.SuppressLint
import android.content.Context
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
import com.navigation.latihan.paranmo.data.Category
import com.navigation.latihan.paranmo.data.Discount
import com.navigation.latihan.paranmo.data.storage.PreferenceAkunParanmo
import com.navigation.latihan.paranmo.databinding.FragmentParanShopBinding
import com.navigation.latihan.paranmo.model.FactoryViewModel
import com.navigation.latihan.paranmo.model.ProductViewModel
import com.navigation.latihan.paranmo.adapter.ListProductAdapter
import com.navigation.latihan.paranmo.adapter.ListCategoryAdapter
import com.navigation.latihan.paranmo.adapter.ListDiscountAdapter


private val Context.dataStoreParanmo: DataStore<Preferences> by preferencesDataStore(name = "paranmo")
class ParanShopFragment : Fragment() {
    private var _bindingParanShop: FragmentParanShopBinding? = null
    private val bindingParanshop get() =  _bindingParanShop

    private lateinit var productViewModel: ProductViewModel
    private lateinit var listProductAdapter: ListProductAdapter

    private val listDiscount = ArrayList<Discount>()
    private val listCat = ArrayList<Category>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _bindingParanShop = FragmentParanShopBinding.inflate(layoutInflater, container, false)

        bindingButton()
        return bindingParanshop?.root!!
    }

    private fun bindingButton() {
        bindingParanshop?.tbnBag?.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }

        bindingParanshop?.btnFavorite?.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
        bindingParanshop?.btnNotification?.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
        bindingParanshop?.cardSearch?.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       bindingParanshop?.rvDiscount?.setHasFixedSize(true)
        bindingParanshop?.rvCategory?.setHasFixedSize(true)


        listDiscount.addAll(liDiscount)
        listCat.addAll(lisCat)

        showRecyclerList()
        showRecyclerListPrdouct()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showRecyclerListPrdouct() {
        val preferenceAkunParanmo = PreferenceAkunParanmo.getInstanceParanmoApp(requireContext().dataStoreParanmo)

        productViewModel = ViewModelProvider(
            this, FactoryViewModel(preferenceAkunParanmo)
        )[ProductViewModel::class.java]

        productViewModel.getUserParanmo().observe(requireActivity()){
            user ->
            productViewModel.setProduct(id = user.id)
        }

        productViewModel.getProduct().observe(requireActivity()){
            if (it!=null){
                listProductAdapter.setProduct(it)
                listProductAdapter.notifyDataSetChanged()
        }
        }
    }

    private val lisCat:ArrayList<Category>
    @SuppressLint("Recycle")
    get() {
        val frameCat = resources.obtainTypedArray(R.array.frameCat)
        val viewImageCat = resources.obtainTypedArray(R.array.viewImageCat)
        val txtCat = resources.getStringArray(R.array.txtCat)
        val lisCat = ArrayList<Category>()
        for (i in txtCat.indices) {
            val category = Category(
                frameCat.getResourceId(i, -1),
                viewImageCat.getResourceId(i, -1),
                txtCat[i],
            )
            lisCat.add(category)
        }

        return lisCat
    }

    private val liDiscount:ArrayList<Discount>
    @SuppressLint("Recycle")
    get() {
        val frameDis = resources.obtainTypedArray(R.array.frameDis)
        val viewPay = resources.obtainTypedArray(R.array.viewPay)
        val txtDis = resources.getStringArray(R.array.txtDis)
        val textPer = resources.getStringArray(R.array.txtPer)
        val textDes = resources.getStringArray(R.array.txtDes)
        val textStyle = resources.obtainTypedArray(R.array.txtPerStyle)
        val disProd = resources.obtainTypedArray(R.array.disProd)
        val viewImage = resources.obtainTypedArray(R.array.viewImage)

        val listDis = ArrayList<Discount>()
        for (i in textPer.indices){
            val discount = Discount(
                frameDis.getResourceId(i, -1),
                viewPay.getResourceId(i, -1),
                txtDis[i],
                textPer[i],
                textDes[i],
                textStyle.getResourceId(i,-1),
                disProd.getResourceId(i,-1),
                viewImage.getResourceId(i,-1)



            )
            listDis.add(discount)

        }
        return listDis
    }



    private fun showRecyclerList(){
        var row = 1
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            row= 1

        }
        bindingParanshop?.rvProduct?.layoutManager = StaggeredGridLayoutManager(row , StaggeredGridLayoutManager.HORIZONTAL)
        listProductAdapter = ListProductAdapter()
        bindingParanshop?.rvProduct?.adapter = listProductAdapter

        bindingParanshop?.rvDiscount?.layoutManager = StaggeredGridLayoutManager(row, StaggeredGridLayoutManager.HORIZONTAL)
        bindingParanshop?.rvCategory?.layoutManager =  StaggeredGridLayoutManager(row, StaggeredGridLayoutManager.HORIZONTAL)

        val listDiscountAdapter = ListDiscountAdapter(listDiscount)
        bindingParanshop?.rvDiscount?.adapter = listDiscountAdapter

        val listCategoryAdapter = ListCategoryAdapter(listCat)
        bindingParanshop?.rvCategory?.adapter = listCategoryAdapter




    }

}