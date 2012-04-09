package com.cynical.android.euchre;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cynical.android.euchre.network.EuchreCommandFuture.CommandCallback;
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
        
        connectButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				LoginCommandModel model = new LoginCommandModel();
				model.setUsername(usernameEditText.getText().toString());
				try {
					binder.sendCommand(EuchreCommand.LOGIN, model).addCallback(new CommandCallback() {
						
						public void onSuccess() {
							// TODO Auto-generated method stub
							
						}
						
						public void onFail() {
							Toast.makeText(EuchreStartActivity.this, "Could not login to server...", Toast.LENGTH_LONG).show();
						}
					});
				} catch (IllegalConnectionStateException e) {
					//TODO: Do disconnected message thing
				}
			}
		});
        
        connectingDialog = ProgressDialog.show(this, "", "Connecting to server...");
        Intent i = new Intent(EuchreStartActivity.this, EuchreNetworkService.class);
		bindService(i, connection, Context.BIND_AUTO_CREATE);
        
    }
    
    private ServiceConnection connection = new ServiceConnection() {
		
		public void onServiceDisconnected(ComponentName name) {
			binder = null;
			serviceBound = false;
		}
		
		public void onServiceConnected(ComponentName name, IBinder service) {
			binder = (EuchreNetworkBinder) service;
			serviceBound = true;
			connectingDialog.dismiss();
		}
	};
}