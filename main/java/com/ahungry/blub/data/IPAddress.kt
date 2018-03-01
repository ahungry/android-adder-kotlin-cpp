package com.ahungry.blub

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class IPAddress (val origin: String = "") {
  override fun toString (): String {
    return "Your IP: ${origin}"
  }

  class Deserializer : ResponseDeserializable<IPAddress> {
    override fun deserialize(content: String) = Gson().fromJson(content, IPAddress::class.java)
  }
}
