/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/
package com.apptec.dtvchannelsearch;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONException;

//import java.util.*;
//import java.rmi.RemoteException.*;
//import java.lang.Exception.*;

//import com.sdmc.aidl.*;
//import com.sdmc.dtv.acpi.*;


public class DTVChannelSearch extends CordovaPlugin {
    private static final String TAG = "DTVChannelSearch";

    /**
     * Sets the context of the Command. This can then be used to do things like
     * get file paths associated with the Activity.
     *
     * @param cordova The context of the main Activity.
     * @param webView The CordovaWebView Cordova is running in.
     */
    @Override
    public void initialize(final CordovaInterface cordova, CordovaWebView webView) {
        Log.v(TAG, "DTVChannelSearch: initialization");
        super.initialize(cordova, webView);
    }

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback id used when calling back into JavaScript.
     * @return                  True if the action was valid, false otherwise.
     */
    @Override
    public boolean execute(final String action, final CordovaArgs args, final CallbackContext callbackContext) throws JSONException {
        Log.v(TAG, "Executing action: " + action);
        final Activity activity = this.cordova.getActivity();
        final Window window = activity.getWindow();		
		
		return true;
		
		/*
		if("getBandWidth".equals(action)) {
			this.cordova.getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					/*
					//try{
					//	ProgramSearch mProgramSearch = new ProgramSearch();
					//	List<SearchParcel> lProgramSearch = mProgramSearch.getSearchParcelList();
					//	
					//} catch (Exception e) {
					//	e.printStackTrace();
					//}
					
					
					//SearchParcel mSearchParcel = new SearchParcel(1,1);//getBandWidth();
					//int iBandWidth = mSearchParcel.getBandWidth();					
					//callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 1));
				}
            });			
			return true;
		}
		*/
        //return false;
    }	
}