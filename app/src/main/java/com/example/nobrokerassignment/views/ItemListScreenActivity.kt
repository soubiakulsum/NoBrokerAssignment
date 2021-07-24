package com.example.nobrokerassignment.views

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nobrokerassignment.ItemListModelItem
import com.example.nobrokerassignment.clickListeners.ItemClickListener
import com.example.nobrokerassignment.databinding.ActivityItemListScreenBinding
import com.example.nobrokerassignment.network.ApiService
import com.example.nobrokerassignment.network.Network
import com.example.nobrokerassignment.room.ItemApplication
import com.example.nobrokerassignment.room.ItemEntity
import com.example.nobrokerassignment.viewModel.ItemViewModel
import com.example.nobrokerassignment.viewModel.ItemViewModelFactory
import com.example.nobrokerassignment.views.adapter.ItemListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ItemListScreenActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var binding: ActivityItemListScreenBinding
    private lateinit var itemListAdapter: ItemListAdapter
    private var entityList = mutableListOf<ItemEntity>()
    var search = "title"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemListScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.title = "Item List Screen"

        /**
         * starting the shimmer animation here
         */
        binding.apply {
            shimmerFrameLayout.startShimmerAnimation()
        }

        /**
         * calling repository here to get the itemListRepo from repository class.
         */
        val app = application as ItemApplication
        val repository = app.itemListRepo
        val viewModelFactory = ItemViewModelFactory(repository)
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ItemViewModel::class.java)

        /**
         *populating the adapter with the data in the list and binding it to the recycler view
         */

        binding.rvItemList.layoutManager = LinearLayoutManager(this)
        itemListAdapter = ItemListAdapter(entityList, this@ItemListScreenActivity, this)
        binding.rvItemList.adapter = itemListAdapter

        binding.etSearchItem.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                input: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            /**
             * searching the title and description here.
             */

            override fun onTextChanged(input: CharSequence?, start: Int, before: Int, count: Int) {
                search = if (input!!.isNotEmpty()) {
                    input.toString()
                } else {
                    "title"
                }

                viewModel.getSearchItem(search).observe(this@ItemListScreenActivity, {

                    entityList.clear()
                    entityList.addAll(it)
                    itemListAdapter.notifyDataSetChanged()
                })
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        /**
         * here to observe the data coming from the list and update the view continuously
         */

        viewModel.getItemList().observe(this, Observer {
            shimmerDisplay()
            entityList.clear()
            entityList.addAll(it)
            itemListAdapter.notifyDataSetChanged()
        })
        /**
         * call the api here if the list is not empty
         */
        if (entityList.isEmpty()) {
            callApi(viewModel)
        }
    }

    private fun callApi(viewModel: ItemViewModel) {
        viewModel.getAllList(this)
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            shimmerFrameLayout.startShimmerAnimation()
        }
    }

    private fun shimmerDisplay() {
        binding.apply {
            shimmerFrameLayout.stopShimmerAnimation()
            shimmerFrameLayout.visibility = View.GONE
            rvItemList.visibility = View.VISIBLE
        }
        entityList.clear()
    }

//    override fun onPause() {
//        super.onPause()
//        binding.apply {
//            shimmerFrameLayout.stopShimmerAnimation()
//        }
//    }


    override fun onItemClicked(itemEntity: ItemEntity) {
        val intent = Intent(this, ItemDetailScreenActivity::class.java)
        intent.putExtra("items", itemEntity)
        startActivity(intent)
    }
}