package com.navigation.latihan.paranmo.ui.profil.addplant

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.databinding.ActivityCameraBinding
import com.navigation.latihan.paranmo.ui.MainActivity
import com.navigation.latihan.paranmo.utils.Utils.createFile
class CameraActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCameraBinding



    private var captureImageStory : ImageCapture? = null
    private var cameraStory : CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindingButton()
    }

    private fun bindingButton(){
        binding.back.setOnClickListener{
            val intent = Intent(this@CameraActivity , MainActivity::class.java)
            startActivity(intent)
        }
        binding.captureImagePlant.setOnClickListener{
            addPhoto()
        }
    }

    public override fun onResume() {
        super.onResume()
        hideSystemUI()
        cameraStartStory()
    }

    private fun addPhoto(){
        val imageCaptureStory = captureImageStory?:return
        val filePhotoStory = createFile(application)

        val outputOptionsStream = ImageCapture.OutputFileOptions.Builder(filePhotoStory).build()
        imageCaptureStory.takePicture(
            outputOptionsStream, ContextCompat.getMainExecutor(this),
            object: ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val intentCamera = Intent()
                    intentCamera.putExtra("picture", filePhotoStory)
                    intentCamera.putExtra("isBackCamera", cameraStory == CameraSelector.DEFAULT_BACK_CAMERA)
                    setResult(AddPlantActivity.Camera_X_RESULT_CODE, intentCamera)
                    finish()
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(this@CameraActivity, getString(R.string.failed_pic), Toast.LENGTH_SHORT).show()
                }

            }
        )
    }

    private fun cameraStartStory() {
        val cameraProvider = ProcessCameraProvider.getInstance(this)

        cameraProvider.addListener({
            val providerCamera : ProcessCameraProvider = cameraProvider.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinderCamera.surfaceProvider)
                }

            captureImageStory = ImageCapture.Builder().build()

            try {
                providerCamera.unbindAll()
                providerCamera.bindToLifecycle(
                    this,
                    cameraStory,
                    preview,
                    captureImageStory
                )
            } catch (e: Exception){
                Toast.makeText( this@CameraActivity, getString(R.string.failed_camera), Toast.LENGTH_SHORT).show()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}