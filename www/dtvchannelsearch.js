/*
 *
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

var exec = require('cordova/exec');

var DTVChannelSearch = {
	
	resultBandWidth: 0,
	resultExec: false,

    getBandWidth: function (callback) {
		alert("getbandwidth");
		this.resultBandWidth = 20;		
		exec( function(result){
				alert("Callback Sucess: " + result);
				callback(null, result);
			  }, function(err){
				alert("Callback Error: " + err);
				callback(err, null);			
			  }, "DTVChannelSearch", "getBandWidth", []
		);		
        //exec(null, null, "DTVChannelSearch", "getBandWidth", []);
    }
};

//alert("aqui");

module.exports = DTVChannelSearch;