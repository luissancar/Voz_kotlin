package com.example.voz

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var grabar: TextView? = null
    private val RECOGNIZE_SPEECH_ACTIVITY = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RECOGNIZE_SPEECH_ACTIVITY ->
                if (resultCode == Activity.RESULT_OK && null != data) {
                    val speech = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    val strSpeech2Text = speech[0]


                    when(strSpeech2Text)
                    {
                         "suma"->txtMostrar.text=(numA.text.toString().toInt()+numB.text.toString().toInt()).toString()
                         "resta"->txtMostrar.text=(numA.text.toString().toInt()-numB.text.toString().toInt()).toString()
                         else->Toast.makeText(this,"No te entiendo",Toast.LENGTH_LONG).show()
                    }



            }
        }
    }
    fun hablar(v: View) {
        val intentActionRecognizeSpeech = Intent(
            RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        // Configura el Lenguaje (Español)
        intentActionRecognizeSpeech.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-ES")
        try {
            startActivityForResult(intentActionRecognizeSpeech,
                RECOGNIZE_SPEECH_ACTIVITY)
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(applicationContext,
                "Tú dispositivo no soporta el reconocimiento por voz",
                Toast.LENGTH_SHORT).show()
        }
    }}
