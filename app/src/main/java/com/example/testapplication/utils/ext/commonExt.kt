package com.example.testapplication.utils.ext

import android.widget.EditText

fun String?.safe() = this ?: ""

fun EditText.value() = this.text.toString().trim()