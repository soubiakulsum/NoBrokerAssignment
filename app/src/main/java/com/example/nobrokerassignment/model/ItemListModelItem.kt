package com.example.nobrokerassignment

import com.google.gson.annotations.SerializedName

data class ItemListModel(

	@field:SerializedName("ItemListModel")
	val itemListModel: List<ItemListModelItem?>? = null
)

data class ItemListModelItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("subTitle")
	val subTitle: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)
