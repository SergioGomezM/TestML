package com.colapp.testml.view.activity

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.colapp.testml.R
import com.colapp.testml.databinding.ActivitySearchBinding
import com.colapp.testml.model.Alert
import com.colapp.testml.model.Query
import com.colapp.testml.util.AlertCreator
import com.colapp.testml.util.Log
import com.colapp.testml.view.adapter.QueryAdapter
import com.colapp.testml.viewmodel.activity.SearchActivityViewModel


class SearchActivity : AppCompatActivity() {

    private val model: SearchActivityViewModel by viewModels()
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model.onStart()

        model.query.observe(this, observerQuery())
        model.alert.observe(this, observerAlert())

        runQuery(intent)

        binding.rvQuery.layoutManager = LinearLayoutManager(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let { runQuery(it) }
    }


    private fun runQuery(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also {
                Log.info(it)
                model.getQueryBySite(it)
            }
        }
    }

    private fun observerQuery(): Observer<Query> {
        return Observer<Query> {
            Log.info(it.value.toString())
            if (it.Results?.isNotEmpty() == true){
                Log.info(it.value?.count_results.toString())
                val adapter = QueryAdapter(this, it.Results)
                binding.rvQuery.adapter = adapter
            }
        }
    }

    private fun observerAlert(): Observer<Alert>{
        return Observer<Alert> {
            val alert = AlertCreator(AlertDialog.Builder(this), resources, it)
            alert.createAlert().show()
        }
    }
}