package com.ahungry.blub

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.sdk25.coroutines.*
import java.util.*

class AnkoDslActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AnkoDslActivityUI().setContentView(this)
  }
}

class AnkoDslActivityUI : AnkoComponent<AnkoDslActivity> {
  override fun createView(ui: AnkoContext<AnkoDslActivity>) = with(ui) {
    verticalLayout {
      padding = dip(30)
      val name = editText {
        hint = "Name"
        textSize = 24f
      }
      // val name = editText()
      button("Say Hello") {
        textSize = 26f
        onClick { toast("Hello, ${name.text}!") }
      }

      // ankoView(::TextView, theme, init)
      // aknoView(::TextView)
    }
  }
}

/*
class AnkoDslActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
    super.onCreate(savedInstanceState, persistentState)
    AnkoDslActivityUI().setContentView(this)
  }
}

class AnkoDslActivityUI : AnkoComponent<AnkoDslActivity> {
  override fun createView(ui: AnkoContext<AnkoDslActivity>) = with(ui) {
    verticalLayout {
      padding = dip(30)
      val name = editText {
        hint = "Name"
        textSize = 24f
      }
      // val name = editText()
      button("Say Hello") {
        textSize = 26f
        onClick { toast("Hello, ${name.text}!") }
      }

      // ankoView(::TextView, theme, init)
      // aknoView(::TextView)
    }
  }
}
*/

/*
class AnkoDslActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    verticalLayout {
      padding = dip(30)
      editText {
        hint = "Name"
        textSize = 24f
      }
      editText {
        hint = "Password"
        textSize = 24f
      }
      button("Login") {
        textSize = 26f
        onClick {
          toast("lol")
        }
      }
    }
  }
}
*/
