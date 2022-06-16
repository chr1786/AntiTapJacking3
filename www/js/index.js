/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

// Wait for the deviceready event before using any of Cordova's device APIs.
// See https://cordova.apache.org/docs/en/latest/cordova/events/events.html#deviceready
document.addEventListener('deviceready', onDeviceReady, false);

function onDeviceReady() {
    // Cordova is now initialized. Have fun!

    console.log('Running cordova-' + cordova.platformId + '@' + cordova.version);
    document.getElementById('deviceready').classList.add('ready');

    document.getElementById('btnClickMe').ontouchstart = function() {
        alert('Button clicked!');
    }

    window.plugins.packagemanager.show(false, successCallback, errorCallback);
}

function successCallback(e) {
    if (e.length < 1) return;

    let existingPackages = [];

    for (var i=0; i < e.length; i++) {
        existingPackages.push(e[i].split(';')[2]);
    }

    const arrBlacklist = [
        'com.urbandroid.lux:Twilight',
        'jp.ne.hardyinfinity.bluelightfilter.free:Bluelight Filter',
        'com.paget96.bluelightfilter:Blue Light Filter - Night Light',
        'com.ascendik.eyeshield:Night Shift',
        'com.sisomobile.android.brightness:sFilter',
        'jp.snowlife01.android.bluelightfilter0:Blue light Filter',
        'com.mlhg.screenfilter:Darker',
        'com.digipom.nightfilter:Night Filter',
        'com.github.ericytsang.screenfilter.app.android:Screen Dimmer',
        'com.yaojian.protecteye:Eye Protect'
    ];

    let arrAppsFound = [];

    for (var i=0; i < arrBlacklist.length; i++) {
        let packageName = arrBlacklist[i].split(':')[0];
        let appName = arrBlacklist[i].split(':')[1];

        if (existingPackages.indexOf(packageName) > -1) {
            arrAppsFound.push(appName);
        }
    }
    
    if (arrAppsFound.length > 0) {
        let msg = 'There are some overlay apps installed in this device, due to security reason you are not allowed to proceed\n\n';
        for (var i=0; i < arrAppsFound.length; i++) {
            msg += arrAppsFound[i] + '\n';
            if (i == 2) {
                msg += '...';
                break;
            }
        }

        alert(msg);
        navigator.app.exitApp();
    }

    console.log(existingPackages);
}

function errorCallback(e) {
    console.log(e);
}