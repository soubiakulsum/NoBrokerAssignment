package com.example.nobrokerassignment.network


import com.example.nobrokerassignment.ItemListModelItem
import retrofit2.Call
import retrofit2.http.GET


/**
 * getting the list of data in model class through get pannotation and the end point of teh api
 */
interface ApiService {

    @GET("b/60fc4870a263d14a297b960b")
     fun getItemList() : Call<List<ItemListModelItem>>
}