package com.colapp.testml.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import coil.load
import com.colapp.testml.R
import com.colapp.testml.databinding.ActivityDetailBinding
import com.colapp.testml.viewmodel.activity.DetailActivityViewModel

class DetailActivity : AppCompatActivity() {

    private val model: DetailActivityViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivPhotoDetail.load(intent.getStringExtra("img"))
        binding.tvTitleDetail.text = intent.getStringExtra("title")
        binding.tvPriceDetail.text = intent.getStringExtra("price")

        val condition = intent.getStringExtra("condition")
        if(condition == "new"){
            binding.tvConditionDetail.text = resources.getText(R.string.item_new)
        }else{
            binding.tvConditionDetail.text = resources.getText(R.string.item_old)
        }

        model.onStart()

    }


}