package com.braintreepayments.api.dropinbridge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class MainActivity : AppCompatActivity() {

    companion object {
        const val DEMO_SITE_URL = "http://10.0.2.2:8080/";
//        const val DEMO_SITE_URL = "https://sshropshire.github.io/braintree-android-drop-in-bridge/"
    }

    private val dropInBridgeClient = DropInBridgeClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.web_view)
        dropInBridgeClient.attachToWebView(webView)

        // load demo site
        webView.loadUrl(DEMO_SITE_URL)
    }
}