package com.example.testapplication.utils

import android.content.Context
import android.widget.Toast

class MessageUtil(private val context: Context) {

    var type: Type = Type.TOAST

    enum class Type{
        TOAST
    }

    fun show(message: String){
        when(type){
            Type.TOAST -> generateToast(message).show()
        }
    }

    private fun generateToast(text: String): Toast{
        return Toast.makeText(context, text, Toast.LENGTH_SHORT)
    }
}