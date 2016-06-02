package com.appspot.pistatium.sokuhoukun.models

import android.content.Context
import jp.takuji31.koreference.KoreferenceModel
import jp.takuji31.koreference.stringPreference


class Pref(val context: Context): KoreferenceModel(context=context, name = "pref") {
    var webhook: String by stringPreference("")
}