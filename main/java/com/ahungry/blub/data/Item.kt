package com.ahungry.blub

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// See: https://www.kotlindevelopment.com/parcelable-kotlin-android-extensions/

@Parcelize
data class X (val a: String) : Parcelable {
  class Deserializer : ResponseDeserializable<X> {
    override fun deserialize(content: String) = Gson().fromJson(content, X::class.java)
  }
}

@Parcelize
data class Item (
    val timeAgo: String = "",
    val id: Number = 0,
    val seller: String = "",
    val date: Number = 0,
    val listing: String = ""
) : Parcelable {
  /*
  constructor (parcel: Parcel) : this() {
    val data = arrayOfNulls<String>(5)
    parcel.readStringArray(data)
    this.timeAgo = data[0]
    this.id = Integer.parseInt(data[1])
    this.seller = data[2]
    this.date = Integer.parseInt(data[3])
    this.listing = data[4]
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(timeAgo)
    parcel.writeInt(id)
    parcel.writeString(seller)
    parcel.writeInt(date)
    parcel.writeString(listing)
  }

  private fun readFromParcel(parcel: Parcel) {
    id = parcel.readString()
    id = parcel.readInt()
    seller = parcel.readString()
    date = parcel.readInt()
    listing = parcel.readString()
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<Series> {
    override fun createFromParcel(parcel: Parcel): Series {
      return Series(parcel)
    }

    override fun newArray(size: Int): Array<Series?> {
      return arrayOfNulls(size)
    }
  }
*/
  override fun toString (): String {
    return "Listing: ${listing}"
  }

  class Deserializer : ResponseDeserializable<Item> {
    override fun deserialize(content: String) = Gson().fromJson(content, Item::class.java)
  }
}
