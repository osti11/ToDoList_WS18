package com.ema.jannik.todolist

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList

/**
 * Database helper class extends SQLiteOpenHelper.
 * This class create and manage the SQL database on the phone.
 * Created by Jannik on 8/1/2018.
 */

public class DbHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?,
                      version: Int) : SQLiteOpenHelper(context, name, factory, version) {


    /**
     * Create Database with the SQL command from class "To Do"
     */
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(ToDo.CREATE_TABLE);
    }


    /**
     * if an older table exist, drop the table and crate a new one.
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + ToDo.TABLE_NAME)  // Drop older table if existed
        onCreate(db)   // Create tables again
    }


    /**
     * This function create a new tupel in the database.
     * @param values contains the passed parameters `text´ and ´category´,`id` and `timestamp` will be inserted automatically.
     * @return the id of the newly inserted tuple.
     */
    fun insertToDo(category: String, text: String): Long {
        val db = this.writableDatabase     // get writable database
        val values = ContentValues()     // `id` and `timestamp` will be inserted automatically.
        values.put(ToDo.COLUMN_CATEGORY, category)  //insert category in values
        values.put(ToDo.COLUMN_TEXT, text)  //insert text in values
        val id = db.insert(ToDo.TABLE_NAME, null, values)     //insert row in database
        db.close()     // close db connection
        return id  // return newly inserted row id
    }

    fun updateToDo(toDo: ToDo): Int {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(ToDo.COLUMN_TEXT, toDo.getText())

        return db.update(ToDo.TABLE_NAME, values, ToDo.COLUMN_ID + " = ?",
                arrayOf(String.valueOf(toDo.getId())))    // updating row
    }

    fun deleteToDo(toDo: ToDo) {
        val db = this.writableDatabase
        db.delete(ToDo.TABLE_NAME, ToDo.COLUMN_ID + " = ?",
                arrayOf(String.valueOf(toDo.getId())))
        db.close()
    }

    /**
     * get the tuple withe the passed id.
     * //TODO ergänzrn
     */
    fun getToDo(id: Long): ToDo {
        // get readable database as we are not inserting anything
        val db = this.readableDatabase

        val cursor = db.query(ToDo.TABLE_NAME,
                arrayOf(ToDo.COLUMN_ID, ToDo.COLUMN_CATEGORY, ToDo.COLUMN_TEXT, ToDo.COLUMN_TIMESTAMP),
                ToDo.COLUMN_ID + "=?",
                arrayOf(id.toString()), null, null, null, null)

        cursor?.moveToFirst()

        // prepare To do object
        val toDo = ToDo(
                cursor!!.getInt(cursor.getColumnIndex(ToDo.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(ToDo.COLUMN_CATEGORY)),
                cursor.getString(cursor.getColumnIndex(ToDo.COLUMN_TEXT)),
                cursor.getString(cursor.getColumnIndex(ToDo.COLUMN_TIMESTAMP)))

        // close the db connection
        cursor.close()

        return toDo
    }

    fun getAllToDos(): List<ToDo> {
        val toDos = ArrayList<ToDo>()

        // Select All Query
        val selectQuery = "SELECT  * FROM " + ToDo.TABLE_NAME + " ORDER BY " +
                ToDo.COLUMN_TIMESTAMP + " DESC"

        val db = this.writableDatabase
        @SuppressLint("Recycle") val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) { // looping through all rows and adding to list
            do {
                val toDo = ToDo()
                toDo.Id = (cursor.getInt(cursor.getColumnIndex(ToDo.COLUMN_ID)))
                toDo.Category(cursor.getString(cursor.getColumnIndex(ToDo.COLUMN_CATEGORY)))
                toDo.Text(cursor.getString(cursor.getColumnIndex(ToDo.COLUMN_TEXT)))
                toDo.Timestamp(cursor.getString(cursor.getColumnIndex(ToDo.COLUMN_TIMESTAMP)))

                toDos.add(toDo)
            } while (cursor.moveToNext())
        }
        db.close()     // close db connection
        return toDos   // return toDos list
    }
}