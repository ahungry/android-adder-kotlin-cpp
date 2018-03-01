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

class BlubActivity : ListActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // BlubActivityUI().setContentView(this)

    val items = listOf(
        "USA" to "United States",
        "CAN" to "Canada"
    )

    listAdapter = ArrayAdapter(this, 0, items)
  }
}

// class BlubActivityUI : AnkoComponent<BlubActivity> {
//   override fun createView(ui: AnkoContext<BlubActivity>) = with(ui) {
//     verticalLayout {
//       padding = dip(30)
//       val xsample_text = textView {
//         text = "Got your ip"
//         textSize = 24f
//       }
//     }
//   }
// }
