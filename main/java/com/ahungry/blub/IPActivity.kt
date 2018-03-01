package com.ahungry.blub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.view.View
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL
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

class IPActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    IPActivityUI().setContentView(this)
  }
}

class IPActivityUI : AnkoComponent<IPActivity> {
  override fun createView(ui: AnkoContext<IPActivity>) = with(ui) {
    verticalLayout {
      padding = dip(30)
      val xsample_text = textView {
        text = "Got your ip"
        textSize = 24f
      }
    }
  }
}
