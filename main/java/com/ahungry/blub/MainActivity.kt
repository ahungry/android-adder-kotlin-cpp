package com.ahungry.blub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

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
        toast("Howdy doodie")
        Toast.makeText(this@MainActivity, "ButtonClicked!", Toast.LENGTH_LONG).show()
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
