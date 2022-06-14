cordova.define('cordova/plugin_list', function(require, exports, module) {
  module.exports = [
    {
      "id": "cordova-plugin-packagemanager.packagemanager",
      "file": "plugins/cordova-plugin-packagemanager/www/packagemanager.js",
      "pluginId": "cordova-plugin-packagemanager",
      "clobbers": [
        "packagemanager"
      ]
    },
    {
      "id": "cordova-plugin-app-exit.exitApp",
      "file": "plugins/cordova-plugin-app-exit/www/ExitApp.js",
      "pluginId": "cordova-plugin-app-exit",
      "merges": [
        "navigator.app"
      ]
    }
  ];
  module.exports.metadata = {
    "cordova-plugin-tapjackingprotection": "0.1.1",
    "cordova-plugin-packagemanager": "0.1.7",
    "cordova-plugin-app-exit": "0.0.1"
  };
});