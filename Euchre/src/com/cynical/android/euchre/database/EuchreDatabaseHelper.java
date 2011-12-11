package com.cynical.android.euchre.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EuchreDatabaseHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "euchre";
	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_STATS_TABLE_CREATE = 
			"CREATE TABLE " + EuchreDatabaseConstants.STATS_TABLE_NAME + " (" + 
			EuchreDatabaseConstants.PRIMARY_KEY_COLUMN + 
			", " + EuchreDatabaseConstants.DATE_COLUMN +
			", " + EuchreDatabaseConstants.PARTNER_COLUMN +
			", " + EuchreDatabaseConstants.OPPONENT_1_COLUMN +
			", " + EuchreDatabaseConstants.OPPONENT_2_COLUMN +
			", " + EuchreDatabaseConstants.RESULT_COLUMN +
			", " + EuchreDatabaseConstants.SETS_FOR_COLUMN +
			", " + EuchreDatabaseConstants.SETS_AGAINST_COLUMN + 
			");";
			
	
	public EuchreDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_STATS_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
