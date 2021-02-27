package com.colapp.testml.view.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.colapp.testml.R
import com.colapp.testml.model.Result
import com.colapp.testml.view.activity.DetailActivity

class QueryAdapter(private val context: Context, private val data: List<Result>):
    RecyclerView.Adapter<QueryAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPhoto: AppCompatImageView = view.findViewById(R.id.ivPhotoResultAdapter)
        val tvTitle: AppCompatTextView = view.findViewById(R.id.tvTitleResultAdapter)
        val tvPrice: AppCompatTextView = view.findViewById(R.id.tvPriceResultAdapter)
        val clItem: ConstraintLayout = view.findViewById(R.id.clItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.adapter_query,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvTitle.text = data[position].title
        holder.tvPrice.text = data[position].price.toString()
        holder.ivPhoto.load(data[position].img)

        holder.clItem.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("img", data[position].img)
            intent.putExtra("title", data[position].title)
            intent.putExtra("price", data[position].price.toString())
            intent.putExtra("condition", data[position].condition)
            context.startActivity(intent)
        })

    }

    override fun getItemCount(): Int {
        return data.size
    }

}