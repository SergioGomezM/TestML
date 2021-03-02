package com.colapp.testml.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.colapp.testml.R
import com.colapp.testml.model.Attribute

class AttributeAdapter(private val data: List<Attribute>):
    RecyclerView.Adapter<AttributeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: AppCompatTextView = view.findViewById(R.id.tvAttributeName)
        val tvValue: AppCompatTextView = view.findViewById(R.id.tvAttributeValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.adapter_atribute,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = data[position].name
        holder.tvValue.text = data[position].value
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

