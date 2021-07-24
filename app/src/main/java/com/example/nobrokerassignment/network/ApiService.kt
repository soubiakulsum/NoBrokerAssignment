package com.example.nobrokerassignment.network


import com.example.nobrokerassignment.ItemListModelItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("b/60fa8fefa917050205ce5470")
     fun getItemList() : Call<List<ItemListModelItem>>
}