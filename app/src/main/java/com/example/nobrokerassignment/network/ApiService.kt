package com.example.nobrokerassignment.network


import com.example.nobrokerassignment.ItemListModelItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("b/60fc4870a263d14a297b960b")
     fun getItemList() : Call<List<ItemListModelItem>>
}