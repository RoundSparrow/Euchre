package com.cynical.android.euchre;

import com.cynical.android.euchre.model.CardDeck;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class EuchreStartActivity extends Activity {
	
	private Button startButton;
	private Context context;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        
        context = getApplicationContext();
        
        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(context, "Start Button Pressed", Toast.LENGTH_LONG).show();
				CardDeck deck = new CardDeck();
				Log.d("TEST", "This is a test");
			}
		});
    }
    
    
}