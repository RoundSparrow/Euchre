package com.cynical.android.euchre;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.cynical.android.euchre.exception.ConnectionException;
import com.cynical.android.euchre.network.EuchreCommandFuture;
import com.cynical.android.euchre.network.EuchreNetworkService;
import com.cynical.android.euchre.network.EuchreNetworkService.EuchreNetworkBinder;
import com.cynical.android.euchre.network.exception.IllegalConnectionStateException;
import com.cynical.euchre.netty.commands.EuchreCommand;
import com.cynical.euchre.netty.commands.server.login.LoginCommandModel;

public class EuchreStartActivity extends Activity {	
	EditText usernameEditText;
	Button connectButton;
	
	EuchreNetworkBinder binder;
	boolean serviceBound;
	
	ProgressDialog connectingDialog;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  
        
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        connectButton = (Button) findViewById(R.id.connectButton);
        
        connectButton.setOnClickListener(new OnClickListener() { //	Connect to the server and login when button is clicked
			
			public void onClick(View v) {
				//	Show spinning progress dialog until connection and login is complete
				connectingDialog = ProgressDialog.show(EuchreStartActivity.this, "", "Connecting to server...");
				
				//	Connect to server in background thread so progress dialog shows properly
				new Thread(new Runnable() {
					public void run() {
						if(serviceBound) { //	If we have a binder to use
							boolean connected = binder.isConnected();
							if(!connected) {
								connected = binder.connectToServer(); //	connect to server if needed
							}
							try {
								if(connected) { //	If we're connected, do login
									String username = usernameEditText.getText().toString();
									if(username.equals("")) {
										username = "Anonymous";
									}
									LoginCommandModel model = new LoginCommandModel();
									model.setUsername(username);
									binder.sendCommand(EuchreCommand.LOGIN, model).addCallback(new EuchreCommandFuture.CommandCallback() {
										
										public void onSuccess() {
											//XXX:	Move to next activity
										}
										
										public void onFail() {
											showAlertDialog("Login Failed"); //	Show failure message if login failed
										}
									});
									return;
								}
								else {
									throw new ConnectionException();
								}
							} catch(ConnectionException e) {
								showAlertDialog("Failed to connect to server");
							} catch (IllegalConnectionStateException e) {
								Log.e("NETWORK_STATE", "No connection to server");
							} finally {
								connectingDialog.dismiss(); //	Dismiss progress dialog after connection and login is done
							}
						}
					}
				}).start(); //	Start background thread
			}
		});
        
        //	Bind to Network service
        Intent i = new Intent(this, EuchreNetworkService.class);
        ServiceConnection connection = new ServiceConnection() {			
			public void onServiceDisconnected(ComponentName name) {
				binder = null;
				serviceBound = false;
			}
			
			public void onServiceConnected(ComponentName name, IBinder service) {
				binder = (EuchreNetworkBinder) service;
				serviceBound = true;
			}
		};
        bindService(i, connection, Context.BIND_AUTO_CREATE);
    }
    
    /**
     * Thread safe method to show alert dialogs in the UI thread
     * @param message The message to display
     */
    private void showAlertDialog(final String message) {
    	Handler h = new Handler(Looper.getMainLooper());
    	h.post(new Runnable() {
			
			public void run() {
				AlertDialog.Builder builder = new AlertDialog.Builder(EuchreStartActivity.this);
				builder.setTitle("Fail");
				builder.setMessage(message);
				builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				
				AlertDialog dialog = builder.create();
				dialog.show();
			}
		});
    }
}