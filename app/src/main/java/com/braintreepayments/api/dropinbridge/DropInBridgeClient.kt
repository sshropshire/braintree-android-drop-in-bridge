package com.braintreepayments.api.dropinbridge

import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import org.json.JSONObject

class DropInBridgeClient {

    companion object {
        const val JAVASCRIPT_INTERFACE_NAME = "dropInBridge"
    }

    private var webView: WebView? = null

    fun attachToWebView(webView: WebView) {
        this.webView = webView
        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(this, JAVASCRIPT_INTERFACE_NAME)
    }

    @JavascriptInterface
    fun bootstrap() {
        // language=javascript
        val bootstrapJavaScript = """
            window.startDropIn = function startDropIn(params) {
                window.dropInBridge.startWithJSON(JSON.stringify(params));
            }
            
            if (document.readyState === 'complete') {
                bootstrapDropInBridge();
            } else {
                window.addEventListener('load', function () {
                    bootstrapDropInBridge();
                });
            }
        """.trimIndent()
        runJavaScriptInWebView(bootstrapJavaScript)
    }

    @JavascriptInterface
    fun startWithJSON(jsonParams: String) {
        val startParams = JSONObject(jsonParams)
        Log.d("DROP IN BRIDGE", startParams.toString())
    }

    private fun runJavaScriptInWebView(script: String) {
        webView?.post {
            webView?.evaluateJavascript(script, null)
        }
    }
}