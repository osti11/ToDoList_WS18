package com.ema.jannik.todolist

import java.sql.Timestamp

/**
 * An Object of this class represent an tupel in the database.
 * @property CREATE_TABLE String that contains the SQL command to create a database
 * Created by Jannik on 8/1/2018.
 */
class ToDo (    private var id: Int = 0, private var category: String? = null,
        private var text: String? = null, private var timestamp: String? = null){

    companion object {  //can used without a instantiation of the class
        val TABLE_NAME = "todo"
        val COLUMN_ID = "id"
        val COLUMN_CATEGORY = "category"
        val COLUMN_TEXT = "text"
        val COLUMN_TIMESTAMP = "timestamp"

        val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_CATEGORY + " TEXT,"
                + COLUMN_TEXT + " TEXT,"
                + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                + ")")
    }
}
