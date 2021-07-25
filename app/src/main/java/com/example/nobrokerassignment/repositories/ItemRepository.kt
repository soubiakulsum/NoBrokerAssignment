package com.example.nobrokerassignment.repositories

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.nobrokerassignment.ItemListModelItem
import com.example.nobrokerassignment.network.ApiService
import com.example.nobrokerassignment.network.Network
import com.example.nobrokerassignment.network.ResponseHandler
import com.example.nobrokerassignment.room.ItemDao
import com.example.nobrokerassignment.room.ItemEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemRepository(
    private val itemListDao: ItemDao
) {

    /**
     *Making an object of ApiService here to fetch the data and store it in the data base.
     */

    private val api: ApiService = Network.getInstance().create(ApiService::class.java)

    /**
     * calling the api using the callback method and enqueue.
     */

    fun getItemList(context: Context) {
         api.getItemList().enqueue(object : Callback<List<ItemListModelItem>> {
             override fun onResponse(
                 call: Call<List<ItemListModelItem>>,
                 response: Response<List<ItemListModelItem>>
             ) {
                 val itemList = response.body()!!
                 /**
                  * iterating through the list of the items in the entity.
                  */

                 for (i in itemList.indices) {
                     val itemEntity =
                         ItemEntity(
                             i + 1,
                             itemList[i].title,
                             itemList[i].subTitle,
                             itemList[i].image
                         )

                     CoroutineScope(Dispatchers.IO).launch {
                         itemListDao.addItem(itemEntity)
                     }

                 }

             }

             override fun onFailure(call: Call<List<ItemListModelItem>>, t: Throwable) {
                 Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
             }
         })
    }

    /**
     * getting the getItemList function from the dao class and returning livedata
     */

    fun getAllItem(): LiveData<List<ItemEntity>> {
        return itemListDao.getItemList()
    }

    /**
     *
     */

    fun addItem(itemListEntity: ItemEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            itemListDao.addItem(itemListEntity)
        }
    }

    fun getSearchItem(search: String): LiveData<List<ItemEntity>> {
        return itemListDao.getSearchItem(search)
    }

}