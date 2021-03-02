package com.colapp.testml.view.activity

import android.app.SearchManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.colapp.testml.R
import com.colapp.testml.databinding.ActivitySearchBinding
import com.colapp.testml.model.Alert
import com.colapp.testml.model.Query
import com.colapp.testml.repository.RepoConst.ERROR_DATA_NOT_FOUND
import com.colapp.testml.repository.RepoConst.ERROR_REQUEST
import com.colapp.testml.util.Log
import com.colapp.testml.view.adapter.QueryAdapter
import com.colapp.testml.viewmodel.activity.SearchActivityViewModel


class SearchActivity : AppCompatActivity() {

    private val model: SearchActivityViewModel by viewModels()
    private lateinit var binding: ActivitySearchBinding
    private var query: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model.onStart()

        model.query.observe(this, observerQuery())
        model.alert.observe(this, observerAlert())
        model.isSearching.observe(this, observerSearching())

        runQuery(intent)

        binding.rvQuerySearch.layoutManager = LinearLayoutManager(this)

        configSearch()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        runQuery(intent)
    }


    private fun runQuery(intent: Intent?) {
            query = intent?.getStringExtra(SearchManager.QUERY)
            query?.let { model.getQueryBySite(it) }
    }

    private fun observerQuery(): Observer<Query> {
        return Observer<Query> {
            Log.info(it.value.toString())
            if (it.Results?.isNotEmpty() == true) {
                Log.info(it.value?.count_results.toString())
                val adapter = QueryAdapter(this, it.Results, resources)
                binding.rvQuerySearch.adapter = adapter
            }
        }
    }

    private fun observerSearching(): Observer<Boolean> {
        return Observer<Boolean> {
            if(it){
                binding.pbLoadingSearch.show()
                binding.rvQuerySearch.visibility = View.GONE
            }else{
                binding.pbLoadingSearch.hide()
                binding.rvQuerySearch.visibility = View.VISIBLE
            }
        }
    }

    private fun observerAlert(): Observer<Alert> {
        return Observer<Alert> {
            when (it.code) {
                ERROR_DATA_NOT_FOUND -> createAlertDataNotFoundError()
                ERROR_REQUEST -> createAlertInternetError()
            }
        }
    }

    private fun createAlertInternetError() {
        AlertDialog.Builder(this)
            .setTitle(resources.getText(R.string.alert_title_internet))
            .setMessage(resources.getText(R.string.alert_message_internet))
            .setNeutralButton(resources.getText(R.string.alert_button_ok)) { dialog, id ->
            }
            .setPositiveButton(resources.getText(R.string.alert_button_internet)) { dialog, id ->
                query?.let { model.getQueryBySite(it) }
            }
            .show()
    }

    private fun createAlertDataNotFoundError() {
        AlertDialog.Builder(this)
            .setTitle(resources.getText(R.string.alert_title_data_not_found))
            .setMessage(resources.getText(R.string.alert_message_data_not_found))
            .setNeutralButton(resources.getText(R.string.alert_button_ok)) { dialog, id ->
                model
            }
            .show()
    }

    private fun configSearch() {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchInfo = searchManager.getSearchableInfo(componentName)
        binding.svSearchSearch.setSearchableInfo(searchInfo)
        binding.svSearchSearch.setIconifiedByDefault(false)
    }
}