package com.colapp.testml.view.activity

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.colapp.testml.databinding.ActivityStartBinding
import com.colapp.testml.model.Alert
import com.colapp.testml.model.Site
import com.colapp.testml.util.AlertCreator
import com.colapp.testml.view.adapter.SpinnerSiteAdapter
import com.colapp.testml.viewmodel.activity.StartActivityViewModel

class StartActivity : AppCompatActivity() {

    private val model: StartActivityViewModel by viewModels()
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model.onStart()

        configSearch()

        model.selectedSite.observe(this, observerSelectedSite())
        model.sites.observe(this, observerSites())
        model.alert.observe(this, observerAlert())

        binding.pbLoading.show()
        binding.spSitesStart.onItemSelectedListener = onItemSelectedListener()

    }

    private fun onItemSelectedListener(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val idList: String = model.sites.value?.get(position)?.id ?: ""
                model.saveSelectedSite(idList)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun observerSites(): Observer<List<Site>> {
        return Observer<List<Site>> {
            if (it?.isNotEmpty() == true) {
                binding.pbLoading.hide()
                val adapter = SpinnerSiteAdapter(this, it)
                binding.spSitesStart.adapter = adapter
            }
        }
    }

    private fun observerSelectedSite(): Observer<String> {
        return Observer<String> {
            binding.spSitesStart.setSelection(model.getIdSelectItem(it))
        }
    }

    private fun observerAlert(): Observer<Alert> {
        return Observer<Alert> {
            val alert = AlertCreator(AlertDialog.Builder(this), resources, it)
            alert.createAlert().show()
        }
    }

    private fun configSearch() {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchInfo = searchManager.getSearchableInfo(componentName)
        binding.svSearch.setSearchableInfo(searchInfo)
        binding.svSearch.setIconifiedByDefault(false)
    }

}
