/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
*/

package org.apache.cordova.engine;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log; //Added By CHR, June 13, 2022
import android.view.KeyEvent;
import android.view.MotionEvent; //Added By CHR, June 13, 2022
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewEngine;

/**
 * Custom WebView subclass that enables us to capture events needed for Cordova.
 */
public class SystemWebView extends WebView implements CordovaWebViewEngine.EngineView {
    private SystemWebViewClient viewClient;
    SystemWebChromeClient chromeClient;
    private SystemWebViewEngine parentEngine;
    private CordovaInterface cordova;

    public SystemWebView(Context context) {
        this(context, null);
    }

    public SystemWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // Package visibility to enforce that only SystemWebViewEngine should call this method.
    void init(SystemWebViewEngine parentEngine, CordovaInterface cordova) {
        this.cordova = cordova;
        this.parentEngine = parentEngine;
        if (this.viewClient == null) {
            setWebViewClient(new SystemWebViewClient(parentEngine));
        }

        if (this.chromeClient == null) {
            setWebChromeClient(new SystemWebChromeClient(parentEngine));
        }
    }

    @Override
    public CordovaWebView getCordovaWebView() {
        return parentEngine != null ? parentEngine.getCordovaWebView() : null;
    }

    @Override
    public void setWebViewClient(WebViewClient client) {
        viewClient = (SystemWebViewClient)client;
        super.setWebViewClient(client);
    }

    @Override
    public void setWebChromeClient(WebChromeClient client) {
        chromeClient = (SystemWebChromeClient)client;
        super.setWebChromeClient(client);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Boolean ret = parentEngine.client.onDispatchKeyEvent(event);
        if (ret != null) {
            return ret.booleanValue();
        }
        return super.dispatchKeyEvent(event);
    }

    /* Start : Added By CHR, June 13, 2022 */
    @Override
    public boolean onFilterTouchEventForSecurity(MotionEvent event) {

        /* 
            Untuk menghindari isu kompatibilitas karena target API Level, maka untuk perbandingan tidak menggunakan Enumerasi, melainkan 
            langsung nilai integer-nya.

            Constant yang digunakan :
            MotionEvent.FLAG_WINDOW_IS_OBSCURED = 1 (Build.VERSION.SDK_INT >= 9)
            MotionEvent.FLAG_WINDOW_IS_PARTIALLY_OBSCURED = 2 (Build.VERSION.SDK_INT >= 29)

            Log.v("Cordova.SystemWebView", "onFilterTouchEventForSecurity() is invoked : event.getFlags() -> " + event.getFlags() + ", MotionEvent.FLAG_WINDOW_IS_OBSCURED -> " + MotionEvent.FLAG_WINDOW_IS_OBSCURED + ", MotionEvent.FLAG_WINDOW_IS_PARTIALLY_OBSCURED -> " + MotionEvent.FLAG_WINDOW_IS_PARTIALLY_OBSCURED);

            if (event.getFlags() == MotionEvent.FLAG_WINDOW_IS_OBSCURED || event.getFlags() == MotionEvent.FLAG_WINDOW_IS_PARTIALLY_OBSCURED) {    
                return false;
            }
        */

        Log.v("Cordova.SystemWebView", "onFilterTouchEventForSecurity() is invoked : event.getFlags() -> " + event.getFlags() + ", MotionEvent.FLAG_WINDOW_IS_OBSCURED -> 1, MotionEvent.FLAG_WINDOW_IS_PARTIALLY_OBSCURED -> 2");

        if (event.getFlags() == 1 || event.getFlags() == 2) {    
            return false;
        }

        return super.onFilterTouchEventForSecurity(event);
    }
    /* End : Added By CHR, June 13, 2022 */
}
