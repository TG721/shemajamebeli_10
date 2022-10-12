package com.example.shemajamebeli_10.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shemajamebeli_10.R
import com.example.shemajamebeli_10.databinding.ActivityMainBinding
import com.example.shemajamebeli_10.ui.adapter.ItemAdapter
import com.example.shemajamebeli_10.utils.ResponseState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
    }

    private fun setup() {
        var adapter: ItemAdapter = ItemAdapter()
        val recyclerView = binding.mainRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        viewModel.getInfo()

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.myState.collect {
                    when (it) {
                        is ResponseState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is ResponseState.Error -> {
                            binding.finalText.text = "could not get the items"
                            binding.progressBar.visibility = View.GONE
                        }
                        is ResponseState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val UIlist = mutableListOf<UIItem>()
                            for (i in 0 until it.items.size){
                                UIlist.add(convertDTOtoUIObject(it.items.elementAt(i)))
                            }
                            adapter.submitList(UIlist)
                            if (adapter.currentList.isEmpty()) {
                                binding.finalText.text = "not found items"
                                binding.progressBar.visibility = View.VISIBLE
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}