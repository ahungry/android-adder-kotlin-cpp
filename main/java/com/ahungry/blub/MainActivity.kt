package com.ahungry.blub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
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
import android.os.Parcelable

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    MainActivityUI().setContentView(this)
  }

  fun getIp(ui: AnkoContext<MainActivity>) {
    ui.doAsync {
      "https://httpbin.org/ip".httpGet()
          .responseObject (IPAddress.Deserializer()) { request, response, result ->
            // Result is of type Result<IPAddress, Exception>
            Log.d("Request", result.toString())
            val (ipaddr, err) = result

            //uiThread {
            activityUiThreadWithContext {
              if (null != ipaddr) {
                longToast(ipaddr.toString())
                // startActivity<MainActivity>()

                // val items = arrayListOf<Item>(Item())
                // val items = ArrayList(arrayListOf<Parcelable>(Item()))
                // val item = Item("30 seconds", 3, "Me", 45, "Woop woop")

                // val i = Intent(this, BlubActivity::class.java)
                // i.putExtra("lol", Item("30 seconds", 3, "Me", 45, "Woop woop"))
                // i.putParcelableArrayListExtra("extraextra", ArrayList(items))
                // startActivity(i)

                startActivity<BlubActivity>("lol" to ArrayList(arrayListOf<Item>(Item("ok", 1, "Hm", 2, "Lol"))))
                // startActivity<BlubActivity>("lol" to ArrayList(arrayListOf<X>(X("yay", 1), X("woop", 2))))

                // startActivity<BlubActivity>()

                // startActivity<BlubActivity>()
                // startActivity<IPActivity>("ip" to ipaddr.toString())

                // startActivity(Intent(this@MainActivity, IPActivity::class.java))
                // sample_text.setText(ipaddr.toString())
              }
            }
          }
        }
      }

      /**
      * A native method that is implemented by the 'native-lib' native library,
      * which is packaged with this application.
      */
      external fun stringFromJNI(): String
      external fun addOne(y: Int): String

      companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
          System.loadLibrary("native-lib")
        }
      }
    }

    class MainActivityUI : AnkoComponent<MainActivity> {
      override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
          padding = dip(30)
          val sample_text = textView {
            text = "Hello there."
            textSize = 24f
          }
          val name = editText {
            hint = "Name"
            textSize = 24f
          }
          // val name = editText()
          button("Say Hello") {
            textSize = 26f
            onClick {
              ui.owner.getIp(ui)
            }
          }
        }
      }
    }
