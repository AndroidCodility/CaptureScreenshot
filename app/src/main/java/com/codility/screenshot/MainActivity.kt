package com.codility.screenshot

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun screenShot(view: View) {
        var bitmap: Bitmap? = getBitmapRootView(view)
        imageView.setImageBitmap(bitmap)
        createImage(bitmap!!)
    }

    private fun getBitmapRootView(v: View): Bitmap {
        val rootView = v.rootView
        rootView.isDrawingCacheEnabled = true
        return rootView.drawingCache
    }

    private fun createImage(bmp: Bitmap) {
        val bytes = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 40, bytes)
        val file = File(Environment.getExternalStorageDirectory().toString().plus("/captured_screen_shot.jpg"))
        try {
            file.createNewFile()
            val outputStream = FileOutputStream(file)
            outputStream.write(bytes.toByteArray())
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
