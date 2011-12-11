package com.cynical.android.euchre.database;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class EuchreDatabaseAdapter {
	
	private Context context;
	private SQLiteDatabase db;
	private EuchreDatabaseHelper dbHelper;
	
	
	public EuchreDatabaseAdapter(Context context) {
		this.context = context;
	}
	
	public EuchreDatabaseAdapter open() {
		dbHelper = new EuchreDatabaseHelper(context);
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public long addGameToDb(Date date, String partner, String opponent1, String opponent2, String result, int setsFor, int setsAgainst) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ContentValues values = new ContentValues();
		values.put(EuchreDatabaseConstants.DATE_KEY, sdf.format(date));
		values.put(EuchreDatabaseConstants.PARTNER_KEY, partner);
		values.put(EuchreDatabaseConstants.OPPONENT_1_KEY, opponent1);
		values.put(EuchreDatabaseConstants.OPPONENT_2_KEY, opponent2);
		values.put(EuchreDatabaseConstants.RESULT_KEY, result);
		values.put(EuchreDatabaseConstants.SETS_FOR_KEY, setsFor);
		values.put(EuchreDatabaseConstants.SETS_AGAINST_KEY, setsAgainst);
		
		return db.insert(EuchreDatabaseConstants.STATS_TABLE_NAME, null, values);
	}
	
	public int deleteGameFromDb(long rowId) {
		return db.delete(EuchreDatabaseConstants.STATS_TABLE_NAME, EuchreDatabaseConstants.PRIMARY_KEY + "=" + rowId, null);
	}
	
	public int updateGameInDb(long rowId, Date date, String partner, String opponent1, String opponent2, String result, Integer setsFor, Integer setsAgainst) {
		ContentValues values = new ContentValues();
		if(date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			values.put(EuchreDatabaseConstants.DATE_KEY, sdf.format(date));
		}
		if(partner != null) {
			values.put(EuchreDatabaseConstants.PARTNER_KEY, partner);
		}
		if(opponent1 != null) {
			values.put(EuchreDatabaseConstants.OPPONENT_1_KEY, opponent1);
		}
		if (opponent2 != null) {
			values.put(EuchreDatabaseConstants.OPPONENT_2_KEY, opponent2);
		}
		if (result != null) {
			values.put(EuchreDatabaseConstants.RESULT_KEY, result);
		}
		if (setsFor != null) {
			values.put(EuchreDatabaseConstants.SETS_FOR_KEY, setsFor);
		}
		if (setsAgainst != null) {
			values.put(EuchreDatabaseConstants.SETS_AGAINST_KEY, setsAgainst);
		}
		if(values.size() > 0) {
			return db.update(EuchreDatabaseConstants.STATS_TABLE_NAME, values, EuchreDatabaseConstants.PRIMARY_KEY + "=" + rowId, null);
		}
		else {
			return 0;
		}
	}
	
	public Cursor getGameFromDb(long rowId) {
		return db.query(EuchreDatabaseConstants.STATS_TABLE_NAME, null, EuchreDatabaseConstants.PRIMARY_KEY + "=" + rowId, null, null, null, null);
	}
	
	public Cursor getAllGamesFromDb() {
		return db.query(EuchreDatabaseConstants.STATS_TABLE_NAME, null, null, null, null, null, null);
	}

}
