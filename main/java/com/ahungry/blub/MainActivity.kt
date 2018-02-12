package com.ahungry.blub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.view.View
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import com.github.kittinunf.fuel.*
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

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Example of a call to a native method
    sample_text.text = stringFromJNI()
    edit.setText("1")

    // Explicit
    // button.setOnClickListener(object: View.OnClickListener {
      //   override fun onClick(view: View): Unit {
        //     Toast.makeText(this@MainActivity, "Button 1", Toast.LENGTH_LONG).show()
        //   }
        // })

        // Implicit via SAM conversion
        button.setOnClickListener {
          if (null == edit.getText().toString().trim().toIntOrNull()) {
            sample_text.setText("Only numbers are allowed!")
            edit.setText("0")
          } else {
            val computed: String = addOne(edit.getText().toString().trim().toInt())
            sample_text.setText("Updated")
            edit.setText(computed)
            toast("Fetch that ip....")

            // Try doing it async with Fuel
            doAsync {
              "https://httpbin.org/ip".httpGet().responseObject (IPAddress.Deserializer()) { request, response, result ->
                // Result is of type Result<IPAddress, Exception>
                // Log.d("Request", result.toString())
                val (ipaddr, err) = result

                uiThread {
                  if (null != ipaddr) {
                    longToast("Req performed")
                    // sample_text.setText(ipaddr.origin)
                    sample_text.setText(ipaddr.toString())
                  }
                }
              }
            }

            // https://antonioleiva.com/api-request-kotlin/
            // Neat, doing it async worked
            /*
            doAsync {
              val result = URL("https://httpbin.org/ip").readText()
              uiThread {
                val jsonString = result.toString().trim()
                Log.d("request", result)
                longToast("Request performed")

                val attrs = Gson().fromJson(
                    jsonString,
                    IPAddress::class.java
                )
                Log.d("Attrs", attrs.origin)
                sample_text.setText(attrs.origin)
              }
            }

            request(findViewById(R.layout.activity_main))
            Toast.makeText(this@MainActivity, "Toast time.", Toast.LENGTH_LONG).show()
            */
          }
          //Toast.makeText(this@MainActivity, "Button 1", Toast.LENGTH_LONG).show()
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
