package com.ema.jannik.todolist

import java.sql.Timestamp

/**
 * An Object of this class represent an tupel in the database.
 * @property CREATE_TABLE String that contains the SQL command to create a database
 * Created by Jannik on 8/1/2018.
 */
class ToDo (private var id: Int, private var category: String, private var text: String,
            private var timestamp: String) {
    //todo timestamp as timestamp or string

    val TABLE_NAME : String = "todo"
    val COLUMN_ID : String = "id"
    val COLUMN_CATEGORY : String = "category"
    val COLUMN_TEXT : String = "text"
    val COLUMN_TIMESTAMP : String = "timestamp"

    val CREATE_TABLE : String = ("CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_CATEGORY + " TEXT,"
            + COLUMN_TEXT + " TEXT,"
            + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ")")
}