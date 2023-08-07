package com.lowasis.translationexample

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import com.lowasis.translationexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var englishTranslator: Translator? = null
    private var englishReady = false
    private var chineseTranslator: Translator? = null
    private var chineseReady = false
    private var japaneseTranslator: Translator? = null
    private var japaneseReady = false
    private var vietnameseTranslator: Translator? = null
    private var vietnameseReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        Log.d("TAG", "onStart")
        super.onStart()

        val condition = DownloadConditions.Builder().requireWifi().build()
        englishTranslator = Translation.getClient(
            TranslatorOptions.Builder().setSourceLanguage(TranslateLanguage.KOREAN)
                .setTargetLanguage(TranslateLanguage.ENGLISH).build()
        )
        binding.targetTextEnglish.setText(getString(R.string.download))
        englishTranslator?.downloadModelIfNeeded(condition)?.addOnSuccessListener {
            englishReady = true
            englishTranslator?.translate(binding.sourceText.editableText.toString())
                ?.addOnSuccessListener {
                    Log.i(TAG, it.toString())
                    binding.targetTextEnglish.setText(it)
                }?.addOnFailureListener {
                    Log.i(TAG, "englishTranslator:$it")
                }
        }

        chineseTranslator = Translation.getClient(
            TranslatorOptions.Builder().setSourceLanguage(TranslateLanguage.KOREAN)
                .setTargetLanguage(TranslateLanguage.CHINESE).build()
        )
        binding.targetTextChinese.setText(getString(R.string.download))
        chineseTranslator?.downloadModelIfNeeded(condition)?.addOnSuccessListener {
            chineseReady = true
            chineseTranslator?.translate(binding.sourceText.editableText.toString())
                ?.addOnSuccessListener {
                    Log.i(TAG, it.toString())
                    binding.targetTextChinese.setText(it)
                }?.addOnFailureListener {
                    Log.i(TAG, "chineseTranslator:$it")
                }
        }

        japaneseTranslator = Translation.getClient(
            TranslatorOptions.Builder().setSourceLanguage(TranslateLanguage.KOREAN)
                .setTargetLanguage(TranslateLanguage.JAPANESE).build()
        )
        binding.targetTextJapanese.setText(getString(R.string.download))
        japaneseTranslator?.downloadModelIfNeeded(condition)?.addOnSuccessListener {
            japaneseReady = true
            japaneseTranslator?.translate(binding.sourceText.editableText.toString())
                ?.addOnSuccessListener {
                    Log.i(TAG, it.toString())
                    binding.targetTextJapanese.setText(it)
                }?.addOnFailureListener {
                    Log.i(TAG, "JapaneseTranslator:$it")
                }
        }

        vietnameseTranslator = Translation.getClient(
            TranslatorOptions.Builder().setSourceLanguage(TranslateLanguage.KOREAN)
                .setTargetLanguage(TranslateLanguage.VIETNAMESE).build()
        )
        binding.targetTextVietnamese.setText(getString(R.string.download))
        vietnameseTranslator?.downloadModelIfNeeded(condition)?.addOnSuccessListener {
            vietnameseReady = true
            vietnameseTranslator?.translate(binding.sourceText.editableText.toString())
                ?.addOnSuccessListener {
                    Log.i(TAG, it.toString())
                    binding.targetTextVietnamese.setText(it)
                }?.addOnFailureListener {
                    Log.i(TAG, "VietnameseTranslator:$it")
                }
        }
        binding.sourceText.isEnabled = true
        binding.sourceText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                p0: CharSequence?, p1: Int, p2: Int, p3: Int
            ) {
            }

            override fun onTextChanged(
                p0: CharSequence?, p1: Int, p2: Int, p3: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
                Log.i(TAG, s.toString())
                if (s.toString().isNotBlank()) {
                    if (englishReady) {
                        englishTranslator?.translate(s.toString())?.addOnSuccessListener {
                            Log.i(TAG, it.toString())
                            binding.targetTextEnglish.setText(it)
                        }?.addOnFailureListener {
                            Log.i(TAG, "englishTranslator:$it")
                        }
                    }
                    if (chineseReady) {
                        chineseTranslator?.translate(s.toString())?.addOnSuccessListener {
                            Log.i(TAG, it.toString())
                            binding.targetTextChinese.setText(it)
                        }?.addOnFailureListener {
                            Log.i(TAG, "chineseTranslator:$it")
                        }
                    }
                    if (japaneseReady) {
                        japaneseTranslator?.translate(s.toString())?.addOnSuccessListener {
                            Log.i(TAG, it.toString())
                            binding.targetTextJapanese.setText(it)
                        }?.addOnFailureListener {
                            Log.i(TAG, "japaneseTranslator:$it")
                        }
                    }
                    if (vietnameseReady) {
                        vietnameseTranslator?.translate(s.toString())?.addOnSuccessListener {
                            Log.i(TAG, it.toString())
                            binding.targetTextVietnamese.setText(it)
                        }?.addOnFailureListener {
                            Log.i(TAG, "vietnamese Translator:$it")
                        }
                    }
                }
            }
        })
    }

    override fun onStop() {
        Log.d("TAG", "onStop")
        super.onStop()
        binding.sourceText.isEnabled = false
        englishReady = false
        englishTranslator?.close()
        englishTranslator = null
        chineseReady = false
        chineseTranslator?.close()
        chineseTranslator = null
        japaneseReady = false
        japaneseTranslator?.close()
        japaneseTranslator = null
        vietnameseReady = false
        vietnameseTranslator?.close()
        vietnameseTranslator = null

    }

    companion object {
        const val TAG = "MainActivity"
    }
}