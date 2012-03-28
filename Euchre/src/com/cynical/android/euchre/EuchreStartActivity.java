package com.cynical.android.euchre;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EuchreStartActivity extends Activity {
	
	Button connectButton;
	Button quitButton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  
        
        connectButton = (Button) findViewById(R.id.main_connect_button);
        quitButton = (Button) findViewById(R.id.main_quit_button);
        
        connectButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Connect", Toast.LENGTH_LONG).show();
			}
		});
        
        quitButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});
        
    }
    
    
}