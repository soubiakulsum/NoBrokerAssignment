package com.example.nobrokerassignment.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
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
    private var itemList = listOf<ItemListModelItem>()
    private lateinit var itemListAdapter: ItemListAdapter
    private var entityList = mutableListOf<ItemEntity>()
    var search = "title"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemListScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.title = "Item List Screem"


        val app = application as ItemApplication
        val repository = app.itemListRepo
        val viewModelFactory = ItemViewModelFactory(repository)
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ItemViewModel::class.java)



        binding.rvItemList.layoutManager = LinearLayoutManager(this)
        itemListAdapter = ItemListAdapter(entityList, this@ItemListScreenActivity, this)
        binding.rvItemList.adapter = itemListAdapter

        binding.etSearchItem.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(input: CharSequence?, start: Int, count: Int, after: Int) {

            }

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


        viewModel.getItemList().observe(this, Observer {
            entityList.clear()
            entityList.addAll(it)
            itemListAdapter.notifyDataSetChanged()
        })

        if (entityList.isEmpty()) {
            callApi(viewModel)
        }
    }

    private fun callApi(viewModel: ItemViewModel) {
        val apiClient = Network.getInstance().create(ApiService::class.java)
        apiClient.getItemList().enqueue(object : Callback<List<ItemListModelItem>> {
            override fun onResponse(
                call: Call<List<ItemListModelItem>>,
                response: Response<List<ItemListModelItem>>
            ) {
                itemList = response.body()!!
                for (i in itemList.indices) {
                    val itemEntity =
                        ItemEntity(
                            i + 1,
                            itemList[i].title,
                            itemList[i].subTitle,
                            itemList[i].image
                        )
                    viewModel.addItem(itemEntity)
                }


            }

            override fun onFailure(call: Call<List<ItemListModelItem>>, t: Throwable) {
                Toast.makeText(this@ItemListScreenActivity, "Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }



    override fun onItemClicked(itemEntity: ItemEntity) {
        val intent = Intent(this, ItemDetailScreenActivity::class.java)
        intent.putExtra("items", itemEntity)
        startActivity(intent)
    }
}