/* Termo de licensa Apache Software
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
package com.apptec.dtvchannelsearch;

/*Bibliotecas Android*/
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.os.RemoteException;

/*Bibliotecas cordova*/
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONException;

/*Bibliotecas Java*/
import java.util.List;
import java.lang.Exception.*;

/*Bibliotecas SDMV - AIDL - low level*/
import com.sdmc.aidl.ProgramParcel;
import com.sdmc.aidl.SearchParcel;

/*### Bibliotecas SDMV - ACPI - high level ###*/
import com.sdmc.dtv.acpi.NoPermissionsException;
import com.sdmc.dtv.acpi.ProgramInfo;
//import com.sdmc.dtv.acpi.ProgramSearch;
import com.sdmc.dtv.acpi.DTVACPIManager;
//import com.sdmc.dtv.acpi.QuickIntegration;
import com.sdmc.dtv.acpi.TunerInfo;
/*DTVInfo requer level 3 - Não conseguindo acessar - Biblioteca*/
/*NoPermissionsExceptions throws an exception*/

//import com.sdmc.dtv.acpi.DTVInfo;

/*### Fim Bibliotecas SDMV - ACPI - high level ###*/

public class DTVChannelSearch extends CordovaPlugin {
    private static final String TAG = "DTVChannelSearch";
	
	/*Para instanciar, DTVACPIManager tem que ser inicializado*/
	private ProgramInfo mProgramInfo;
	//private ProgramSearch mProgramSearch;
	//private QuickIntegration mQuickIntegration;	
	private TunerInfo mTunerInfo;
	/*Requer level 3 para ser acessado*/
	//private DTVInfo mDTVInfo;
	
	/*Licença SDMC DTV*/
	private static final String LICENSE = "4BC3EE167DAE1AE42FA0C6712F73D3199ADE8C2A242A60FC1815E56E1C6E5B3D003F217FAE62473BBD21B671D42E2A0A447B1A5C48EC1AD72B807AC9E69F5385";
	
	/*Licensa não válida para teste*/
	//private static final String LICENSE = "license";
	
	/*DTVACPIManager não inicializado*/
	private boolean mIsInitSuccess = false;
	
	/*Para pegar excessões do método: */
	/*private DTVACPIManager.OnInitCompleteListener mOnInitFinishListener*/	
	/*Motivo: fora do método exec*/
	private String sTest = "Not changed";

    /* Inicialização do plugin
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

    /* Executa a ação requerida e retorna Pluginresult     
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
		
		/*Inicialização do DTVACPIManager*/
		/* Escopo do método:
		
		public static boolean init(android.content.Context context,
								   java.lang.String license,
								   DTVACPIManager.OnInitCompleteListener listener)
		
		context -
		license - string da licença
		listener - listener completo da inicialização - callback???
		
		##############################################		
		Escopo do método DTVACPIManager.OnInitCompleteListener:
		Obs.: Será chamado após completar a inicialização de DTVInterfaceManager
		
		void onInitComplete(boolean isInitSuccess, int level)
		*/
		
		//Inicia DTVACPIManager
		boolean isSuccess = DTVACPIManager.init(activity, LICENSE, mOnInitFinishListener);
		
		//Ação do plugin - parâmetro "action"
		if("getBandWidth".equals(action)) {
			this.cordova.getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					
					try{						
						//mProgramSearch.autoSearchByIndex(177143,803143);
						
						/*Métodos testados funcionando - usados para testes
						
						//Muda o canal
						mProgramInfo.playProgram(3);
						
						//Get Search frequency list
						List<SearchParcel> lFreqList = mProgramSearch.getSearchParcelList();
						
						//getStreamTime - verificar o que é
						String sStreamTime = mProgramInfo.getStreamTime();						
						
						//Inicia o módulo para buscar canais digitais pela antena
						mQuickIntegration.startSearchActivity();
						
						//Instancializa a classe ProgramParcel
						ProgramParcel mProgramParcel = new ProgramParcel();
						
						//Precisa testar essa forma de pegar o nome do canal
						String sName = ProgramParcel.getName();
						
						// Instancializa a classe SearchParcel
						//depois pega a largura da banda
						//1º parâmetro - frequencia
						//2º parâmetro - largura da banda
						SearchParcel mSearchParcel = new SearchParcel(2050,6);//getBandWidth();
						int iBandWidth = mSearchParcel.getBandWidth();					
						
						Fim métodos testados
						*/
						
						/* Métodos retornando erro 
						Requer level 3 - não acessível
						boolean bIsAPPInit = mDTVInfo.isAPPInit();
						String sGetTunerType = mDTVInfo.getTunerType();
						*/
												
						/*Pega programa corrente e próximo do EPG*/
						List<String> lsPFEPG = mProgramInfo.getPFEPG();
						
						//Pega programação de hoje e dos próximos 6 dias
						List<String> lsTodayEPG = mProgramInfo.getEPG(0);
						List<String> lsTomorrowEPG = mProgramInfo.getEPG(1);
												
						/*Pega informações do programa corrente*/
						ProgramParcel mInfoCurrentProgram = mProgramInfo.getCurrentProgram();
						
						/*Status do sinal do programa corrente*/
						int[] iaSignalStatus = mTunerInfo.getSignalStatus();
						
						/*Callback Sucesso*/
						callbackContext.sendPluginResult(
							new PluginResult(
								PluginResult.Status.OK, 
								"Retorno callback exec SUCCESS<br><br>" + 
								"License OK: " + mIsInitSuccess + 
								"<br><br>" + sTest + 
								"<br><br>Current Channel: " + mInfoCurrentProgram.getName() + 
								"<br>Channel Number: " + mInfoCurrentProgram.getProgramNumber() +
								"<br>Signal Quality: " + iaSignalStatus[0] + 
								"<br>Signal Strength: " + iaSignalStatus[1] +
								"<br><br>Corrente e Próximo programa do EPG: <br><br>" + lsPFEPG +
								"<br><br>Programação de hoje: <br><br>" + lsTodayEPG +
								"<br><br>Programação de amanhã: <br><br>" + lsTomorrowEPG
							)
						);						
						// + "\nTuner Type: " + sGetTunerType + "\nAPPInit: " + bIsAPPInit
						return;
					} catch (Exception e) {						
						//e.printStackTrace();
						
						/*Callback Erro*/
						callbackContext.sendPluginResult(
							new PluginResult(
								PluginResult.Status.ERROR, 
								"Retorno callback java EXCEPTION >>> Exception (exec): " + e + 
								"\n\nLicense OK: " + mIsInitSuccess + 
								"\n\nException (DTVACPIManager)" + sTest
							)
						);
						return;
					}					
				}		
            });			
			return true;
		}		
        return false;
    }

	/*Callback inicialização DTVACPIManager*/
	private DTVACPIManager.OnInitCompleteListener mOnInitFinishListener 
			= new DTVACPIManager.OnInitCompleteListener() {
		
		@Override
		public void onInitComplete(boolean isSuccess, int level) {
			
			mIsInitSuccess = isSuccess;
			//Log.i(TAG, "onInitComplete " + mIsInitSuccess + " level " + level);
			
			if (mIsInitSuccess) {
				
				//sTest = "Changed SUCCESS onInitComplete";				
				
				try{
					mProgramInfo = new ProgramInfo();
					//mProgramSearch = new ProgramSearch();
					//mQuickIntegration = new QuickIntegration();					
					mTunerInfo = new TunerInfo();
					
					/*Requer level 3 - ver imports*/
					//mDTVInfo = new DTVInfo();
					
					sTest = "onInitComplete >>> Callback OK >>> Licença OK";					
				} catch (NoPermissionsException e) {
					sTest = "onInitComplete >>> NoPermissionsException: " + e.toString();
				} catch (RemoteException e){
					sTest = "onInitComplete >>> RemoteException: " + e.toString();
				}
				
				/* Código do demo enviado pela SDMC
				try {
					mRemoteRealTimePlayer = new RemoteRealTimePlayer();
					mRemoteRealTimePlayer.start(MainActivity.this);
					mPlayEventHandler = new PlayEventHandler();
					mPlayEventHandler.setPlayEventListener(mPlayEventListener);
					mTunerInfo = new TunerInfo();
					
					mOSDManager = new OSDManager(MainActivity.this);
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (NoPermissionsException e) {
					e.printStackTrace();
				}
				
				try {
					mRecorder = new Recorder();
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (NoPermissionsException e) {
					e.printStackTrace();
				}
				
				try {
					mTimeShift = new TimeShift();
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (NoPermissionsException e) {
					e.printStackTrace();
				}
				*/
				
			} else {
				sTest = "onInitComplete >>> Callback OK >>> Licença INVÁLIDA";
				
				/* Código do demo enviado pela SDMC
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setMessage("Your lisence is not correct!");
				builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				});
				builder.create().show();
				*/
			}
		}		
	};
}