package com.example.linkpreviewandroid

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.bumptech.glide.Glide
import com.example.linkpreviewandroid.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.editTextId.doOnTextChanged { text, start, before, count ->
            retrievePreview(binding.editTextId.text.toString())
        }
    }

    @SuppressLint("CheckResult")
    private fun retrievePreview(url:String){
        Utils.getJsoupData(url)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result->
                    println("ActivityMainBinding data -->>> $result")
                    Glide.with(this).load(result.image).into(binding.image)
                    binding.title.text = result.title
                    binding.desc.text = result.description
                },
                { error ->
                    run {
                        println("MainActivity >>> $error")
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            )
    }
}