package com.example.nobrokerassignment.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nobrokerassignment.ItemListModel
import com.example.nobrokerassignment.ItemListModelItem
import com.example.nobrokerassignment.R
import com.example.nobrokerassignment.databinding.ActivityItemListScreenBinding
import com.example.nobrokerassignment.network.ApiService
import com.example.nobrokerassignment.network.Network

import com.example.nobrokerassignment.views.adapter.ItemListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemListScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemListScreenBinding
    private lateinit var itemList : List<ItemListModelItem>
    private lateinit var itemListAdapter : ItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemListScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvItemList.layoutManager = LinearLayoutManager(this)



        callApi()


    }

    private fun callApi() {
        val apiClient = Network.getInstance().create(ApiService::class.java)
        apiClient.getItemList().enqueue(object : Callback<List<ItemListModelItem>> {
            override fun onResponse(
                call: Call<List<ItemListModelItem>>,
                response: Response<List<ItemListModelItem>>
            ) {
                itemList = response.body()!!

                itemListAdapter = ItemListAdapter(itemList,this@ItemListScreenActivity)
                binding.rvItemList.adapter = itemListAdapter
            }
            override fun onFailure(call: Call<List<ItemListModelItem>>, t: Throwable) {
                Toast.makeText(this@ItemListScreenActivity, "Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }
}