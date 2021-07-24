package com.example.nobrokerassignment.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nobrokerassignment.ItemListModelItem
import com.example.nobrokerassignment.databinding.ActivityItemListScreenBinding
import com.example.nobrokerassignment.network.ApiService
import com.example.nobrokerassignment.network.Network
import com.example.nobrokerassignment.room.ItemListApplication
import com.example.nobrokerassignment.room.ItemEntity
import com.example.nobrokerassignment.viewModel.ItemListViewModel
import com.example.nobrokerassignment.viewModel.ItemListViewModelFactory

import com.example.nobrokerassignment.views.adapter.ItemListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ItemListScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemListScreenBinding
    private var itemList = listOf<ItemListModelItem>()
    private lateinit var itemListAdapter: ItemListAdapter
    private var entityList = mutableListOf<ItemEntity>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemListScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val app = application as ItemListApplication
        val repository = app.itemListRepo
        val viewModelFactory = ItemListViewModelFactory(repository)
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ItemListViewModel::class.java)



        binding.rvItemList.layoutManager = LinearLayoutManager(this)
        itemListAdapter = ItemListAdapter(entityList, this@ItemListScreenActivity)
        binding.rvItemList.adapter = itemListAdapter

        viewModel.getItemList().observe(this, Observer {
            entityList.clear()
            entityList.addAll(it)
            itemListAdapter.notifyDataSetChanged()
        })

        if (entityList.isEmpty()) {
            callApi(viewModel)
        }


    }

    private fun callApi(viewModel: ItemListViewModel) {
        val apiClient = Network.getInstance().create(ApiService::class.java)
        apiClient.getItemList().enqueue(object : Callback<List<ItemListModelItem>> {
            override fun onResponse(
                call: Call<List<ItemListModelItem>>,
                response: Response<List<ItemListModelItem>>
            ) {
                itemList = response.body()!!
                for (i in itemList.indices) {
                    val itemEntity =
                        ItemEntity(i+1,itemList[i].title, itemList[i].subTitle, itemList[i].image)
                    viewModel.addItem(itemEntity)
                }


            }

            override fun onFailure(call: Call<List<ItemListModelItem>>, t: Throwable) {
                Toast.makeText(this@ItemListScreenActivity, "Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }
}