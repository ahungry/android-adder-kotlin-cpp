package com.ahungry.blub

import android.app.ListActivity
import android.os.Bundle
import android.widget.Toast
import android.view.View
import android.util.Log
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.sdk25.coroutines.*
import java.util.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import com.github.kittinunf.fuel.*
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import org.example.ankodemo.util.ListItem
import org.example.ankodemo.util.ListItemAdapter
import org.example.ankodemo.util.TextListItem
import android.os.Parcelable

class BlubActivity : ListActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val eqItems = this.intent.getParcelableArrayListExtra<Parcelable>("extraextra")
    val items = listOf(
        "America" to listOf("This is some really long text, I wonder if it will work, hmmm it could be an acution even....", "Canada", "United States"),
        "Asia" to listOf("China", "India", "Japan"),
        "Europe" to listOf("France", "Germany", "Spain", "United Kingdom")
    )

    val listItems = items.flatMap {
      listOf(ContinentItem(it.first)) + it.second.map { CountryItem(it) }
    }

    listAdapter = BlubAdapter(this, listItems)
  }
}

internal class BlubAdapter(ctx: Context, items: List<ListItem>) : ListItemAdapter(ctx, items) {
  // All ListItem implementations
  override val listItemClasses = listOf(ContinentItem::class.java, CountryItem::class.java)
}

// Default implementation
// DSL preview plugin requires AnkoComponent inheritors to have an empty constructor
internal class CountryItem(text: String = "") : TextListItem(text)

// Custom implementation
internal class ContinentItem(text: String = "") : TextListItem(text) {
  override fun createView(ui: AnkoContext<ListItemAdapter>) = createTextView(ui) {
    gravity = Gravity.CENTER_VERTICAL
    horizontalPadding = ui.dip(20)
    verticalPadding = ui.dip(10)
    backgroundColor = 0x99CCCCCC.toInt()
    textSize = 17f
    textColor = Color.BLUE
  }
}
