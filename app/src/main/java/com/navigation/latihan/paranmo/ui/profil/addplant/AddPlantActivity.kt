package com.navigation.latihan.paranmo.ui.profil.addplant

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.databinding.ActivityAddPlantBinding
import com.navigation.latihan.paranmo.ui.MainActivity
import com.navigation.latihan.paranmo.ui.profil.addplant.utils.rotateBitmapImage
import com.navigation.latihan.paranmo.ui.profil.addplant.utils.uriToFile
import java.io.File

class AddPlantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPlantBinding

    companion object{
        const val Camera_X_RESULT_CODE = 200
        private val REQUIRED_PERMISSION = arrayOf(android.Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSION = 10
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CODE_PERMISSION){
            if(!allPermissionGranted()){
                Toast.makeText(this, getString(R.string.permission_no_granted), Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun allPermissionGranted() = REQUIRED_PERMISSION.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddPlantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!allPermissionGranted()){
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSION,
                REQUEST_CODE_PERMISSION
            )
        }
        bindingButton()
    }

    private fun bindingButton(){
       binding.backFavorit.setOnClickListener {
            val intent = Intent(this@AddPlantActivity , MainActivity::class.java)
            startActivity(intent)
        }

        binding.buttonCamera.setOnClickListener { startCameraX() }
        binding.buttonGallery.setOnClickListener { addPhoto() }
    }

    private fun addPhoto(){
        val intentPhoto = Intent()
        intentPhoto.action = Intent.ACTION_GET_CONTENT
        intentPhoto.type = "image/*"
        val chooserPhoto = Intent.createChooser(intentPhoto,"Choose a Picture")
        launcherGallery.launch(chooserPhoto)
    }

    private var getFile: File? = null
    private val launcherGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == RESULT_OK){
            val selectedImage : Uri = result.data?.data as Uri
            val fileImage = uriToFile(selectedImage, this@AddPlantActivity)
            getFile = fileImage

            binding.photo.setImageURI(selectedImage)
        }
    }

    private fun startCameraX(){
        val intentCamera = Intent(this, CameraActivity::class.java)
        launcherCamera.launch(intentCamera)
    }

    private val launcherCamera = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Camera_X_RESULT_CODE){
            val fileCamera = it.data?.getSerializableExtra("picture") as File
            val isBackCamera= it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            getFile = fileCamera
            val result = rotateBitmapImage( BitmapFactory.decodeFile(getFile?.path), isBackCamera)
            binding.photo.setImageBitmap(result)
        }
    }
}