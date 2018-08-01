package com.ema.jannik.todolist

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Database helper class extends SQLiteOpenHelper.
 * This class create and manage the SQL database on the phone.
 * Created by Jannik on 8/1/2018.
 */

public class DbHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?,
                      version: Int) : SQLiteOpenHelper(context, name, factory, version) {


    /**
     *
     */
    override fun onCreate(db: SQLiteDatabase?) {
        TODO("first implement class TODO")
        //db.execSQL(ToDo.CREATE_TABLE);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}