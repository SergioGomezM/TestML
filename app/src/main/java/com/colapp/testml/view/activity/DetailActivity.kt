package com.colapp.testml.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.colapp.testml.R
import com.colapp.testml.databinding.ActivityDetailBinding
import com.colapp.testml.model.Attribute
import com.colapp.testml.model.Result
import com.colapp.testml.view.ViewConst.EXTRA_PRODUCT_SELECTION
import com.colapp.testml.view.adapter.AttributeAdapter
import com.colapp.testml.viewmodel.activity.DetailActivityViewModel

class DetailActivity : AppCompatActivity() {

    private val model: DetailActivityViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding
    private var product: Result? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        product = intent.getParcelableExtra(EXTRA_PRODUCT_SELECTION)

        binding.ivPhotoDetail.load(product?.img)
        binding.tvTitleDetail.text = product?.title
        binding.tvPriceDetail.text = product?.price.toString()

        product?.condition?.let { convertCondition(it) }
        product?.Attributes?.let {loadAttributes(it)}

        model.onStart()

    }

    private fun convertCondition(condition: String){
        if(condition == "new"){
            binding.tvConditionDetail.text = resources.getText(R.string.item_new)
        }else{
            binding.tvConditionDetail.text = resources.getText(R.string.item_old)
        }
    }

    private fun loadAttributes(attributes: List<Attribute>){
        binding.rvAttributes.layoutManager = LinearLayoutManager(this)
        val adapter = AttributeAdapter(attributes)
        binding.rvAttributes.adapter = adapter
    }
}