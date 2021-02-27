package com.colapp.testml.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.colapp.testml.R
import com.colapp.testml.model.Site
import java.util.*

class SpinnerSiteAdapter(val context: Context, var data: List<Site>): BaseAdapter(){

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Site {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = inflater.inflate(R.layout.adapter_sites, parent, false)
        val viewHolder = SiteHolder(view)
        val id = data[position].id?.toLowerCase(Locale.ROOT)
        val resource = context.resources.getIdentifier(id , "drawable", context.packageName)

        view.tag = viewHolder

        viewHolder.name.text = data[position].name
        viewHolder.icon.setBackgroundResource(resource)

        return view
    }

    private class SiteHolder(view: View?) {
        val name = view?.findViewById(R.id.tvNameSiteAdapter) as AppCompatTextView
        val icon = view?.findViewById(R.id.ivIcSiteAdapter) as AppCompatImageView
    }

}
