package com.navigation.latihan.paranmo.ui.identifikasitanaman.resultidentifikasi

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.navigation.latihan.paranmo.databinding.ActivityResultIdentifikasiBinding
import com.navigation.latihan.paranmo.ml.BestModelParanmo
import com.navigation.latihan.paranmo.ml.New
import com.navigation.latihan.paranmo.ml.ParanmoFinal1
import com.navigation.latihan.paranmo.ui.MainActivity
import org.tensorflow.lite.support.image.TensorImage

class ResultIdentifikasiActivity : AppCompatActivity() {

    private lateinit var binding : ActivityResultIdentifikasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultIdentifikasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindingButton()
    }

    private fun bindingButton(){

        binding.fabCam.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED){
                pictPreview.launch(null)
            }else{
                requestPermission.launch(android.Manifest.permission.CAMERA)
            }
        }

        binding.backFavorit.setOnClickListener{
            val intent = Intent(this@ResultIdentifikasiActivity , MainActivity::class.java)
            startActivity(intent)
        }
    }

    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            permission ->

        if (permission){
            pictPreview.launch(null)
        } else{
            Toast.makeText(this, "Izin ditolak !! Coba lagi", Toast.LENGTH_SHORT).show()
        }
    }

    private val pictPreview = registerForActivityResult(ActivityResultContracts.TakePicturePreview()){
            bitmap ->

        if(bitmap != null){
            binding.photo.setImageBitmap(bitmap)
            binding.cardIdentification.visibility = View.VISIBLE
            binding.description.visibility = View.GONE
            binding.useCard.visibility = View.GONE
            output(bitmap)
        }
    }

    private fun output(bitmap: Bitmap) {

        val modelParanmoFinal = ParanmoFinal1.newInstance(this)

        val imageBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val tensorImage = TensorImage.fromBitmap(imageBitmap)

        val outputs = modelParanmoFinal.process(tensorImage)
            .probabilityAsCategoryList.apply {
                sortByDescending { it.score }
            }

        val probabilityOutput = outputs[0]


        binding.plant.text = probabilityOutput.label

        Log.i("TAG", "output : $probabilityOutput")
    }

}