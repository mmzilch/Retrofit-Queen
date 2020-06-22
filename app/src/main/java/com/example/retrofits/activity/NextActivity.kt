package com.example.retrofits.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.retrofits.R
import kotlinx.android.synthetic.main.activity_next.*

class NextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

       // var name=intent.extras?.getString("name")
        var name=intent.getStringExtra("name")
        var image = intent.getStringExtra("Image")
        tvNextName.text=name

        Glide.with(applicationContext).load(image).placeholder(R.drawable.ic_launcher_background).into(imageQueen)
    }

}
