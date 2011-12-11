package com.cynical.android.euchre.database;

public class EuchreDatabaseConstants {

	static final String PRIMARY_KEY_COLUMN = "_id INTEGER PRIMARY KEY AUTOINCREMENT";
	static final String WINS_COLUMN = "WINS INTEGER NOT NULL";
	static final String LOSSES_COLUMN = "LOSES INTEGER NOT NULL";
	static final String SETS_FOR_COLUMN = "SETS_FOR INTEGER NOT NULL";
	static final String SETS_AGAINST_COLUMN = "SETS_AGAINST INTEGER NOT NULL";
}
