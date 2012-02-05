package com.cynical.android.euchre.database;

public class EuchreDatabaseConstants {
	
	//////////////////////////////////////////////
	//	Table name
	//////////////////////////////////////////////
	static final String STATS_TABLE_NAME = "STATS";
	
	//////////////////////////////////////////////
	// Column names
	//////////////////////////////////////////////
	static final String PRIMARY_KEY = "_id";
	static final String DATE_KEY = "DATE";
	static final String PARTNER_KEY = "PARTNER";
	static final String OPPONENT_1_KEY = "OPPONENT_1";
	static final String OPPONENT_2_KEY = "OPPONENT_2";
	static final String RESULT_KEY = "RESULT";
	static final String SETS_FOR_KEY = "SETS_FOR";
	static final String SETS_AGAINST_KEY = "SETS_AGAINST";
	
	///////////////////////////////////////////////
	//	Column configuration
	///////////////////////////////////////////////
	static final String PRIMARY_KEY_COLUMN = PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT";
	static final String DATE_COLUMN = DATE_KEY + " STRING NOT NULL";
	static final String PARTNER_COLUMN = PARTNER_KEY + " STRING NOT NULL";
	static final String OPPONENT_1_COLUMN = OPPONENT_1_KEY + " STRING NOT NULL";
	static final String OPPONENT_2_COLUMN = OPPONENT_2_KEY + " STRING NOT NULL";
	static final String RESULT_COLUMN = RESULT_KEY + " INTEGER NOT NULL";
	static final String SETS_FOR_COLUMN = SETS_FOR_KEY + " INTEGER NOT NULL";
	static final String SETS_AGAINST_COLUMN = SETS_AGAINST_KEY + " INTEGER NOT NULL";
}
