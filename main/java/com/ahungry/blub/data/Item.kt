package com.ahungry.blub

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Item (
    val timeAgo: String = "",
    val id: Number = 0,
    val seller: String = "",
    val date: Number = 0,
    val listing: String = ""
) {
  override fun toString (): String {
    return "Listing: ${listing}"
  }

  class Deserializer : ResponseDeserializable<Item> {
    override fun deserialize(content: String) = Gson().fromJson(content, Item::class.java)
  }
}
