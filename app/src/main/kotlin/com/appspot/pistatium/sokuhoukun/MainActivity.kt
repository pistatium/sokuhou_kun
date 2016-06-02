package com.appspot.pistatium.sokuhoukun

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.appspot.pistatium.sokuhoukun.models.Pref
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), TextWatcher {

    var iwInput: EditText by Delegates.notNull()
    var pref: Pref by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onResume() {
        super.onResume()
        iwInput.setText(pref.webhook)
    }

    override fun afterTextChanged(s: Editable?) {
        // TODO: Validation
        pref.webhook = s.toString()
    }

    private fun init() {
        setContentView(R.layout.activity_main)
        pref = Pref(applicationContext)

        iwInput = findViewById(R.id.iwInput) as EditText
        iwInput.addTextChangedListener(this)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}
