package com.colapp.testml.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.colapp.testml.R
import com.colapp.testml.model.Result
import com.colapp.testml.util.Log
import com.squareup.picasso.Picasso

class QueryAdapter(private val data: List<Result>, private val photoSize: Float):
    RecyclerView.Adapter<QueryAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPhoto: AppCompatImageView
        val tvTitle: AppCompatTextView
        val tvPrice: AppCompatTextView
        init {
            ivPhoto = view.findViewById(R.id.ivPhotoResultAdapter)
            tvTitle = view.findViewById(R.id.tvTitleResultAdapter)
            tvPrice = view.findViewById(R.id.tvPriceResultAdapter)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_query, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvTitle.text = data.get(position).title
        holder.tvPrice.text = data.get(position).price.toString()

        Log.info("url--${data.get(position).img}")
        Picasso.get()
            .load(data.get(position).img)
            .into(holder.ivPhoto)

    }

    override fun getItemCount(): Int {
        return data.size
    }

}