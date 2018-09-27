package com.plugins.qrreader;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.szxb.jni.libszxb;

/**
 * This class echoes a string called from JavaScript.
 */
public class QRReaderPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        } else if (action.equals("readQR")) {
            this.readQR(callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void readQR(CallbackContext callbackContext) {
        byte[] barcodeBuf = new byte[1024];
        int retLen = libszxb.getBarcode(barcodeBuf);

        if (retLen > 0) {

            //
            // String tmpbarStr = new String(barcodeBuf, 0,
            // retLen);
            //
            // final String barStr =
            // tmpbarStr.replace("\r","").replace("\n","");

            final String barStr = new String(barcodeBuf, 0,
                    retLen);
            callbackContext.success(barStr);
        } else {
            callbackContext.error("Error reading QR Code.");
        }
    }
}
