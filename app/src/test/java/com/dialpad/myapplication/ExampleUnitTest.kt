package com.dialpad.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.test.core.app.ApplicationProvider
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {
    @Test
    fun queryCommonTableExpression() {
        val db = SqliteOpenHelper(ApplicationProvider.getApplicationContext()).readableDatabase
        val cteStatement = "WITH one AS ( SELECT 1 ) SELECT * FROM one"
        val queryResults = arrayListOf<Int>()
        try {
            db.rawQuery(cteStatement, null)
        } catch (e: Exception) {
            null
        }?.use {
            while (it.moveToNext()) {
                queryResults.add(it.getInt(0))
            }
        }
        Assert.assertThat(queryResults.size, CoreMatchers.`is`(1))
        Assert.assertThat(queryResults[0], CoreMatchers.`is`(1))
    }

    private class SqliteOpenHelper(context: Context) : SQLiteOpenHelper(context, "test.db", null, 1) {
        override fun onCreate(db: SQLiteDatabase) {}
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
    }
}
