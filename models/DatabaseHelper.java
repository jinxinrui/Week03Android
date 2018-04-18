package edu.monash.fit3027ffit4039.week03prac.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by jxr on 21/3/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Set Database Properties
    public static final String DATABASE_NAME = "PersonDB";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Person.CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Person.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Person.COLUMN_NAME, person.getName());
        values.put(Person.COLUMN_COUNTRY, person.getCountry());
        db.insert(Person.TABLE_NAME, null, values);
        db.close();
    }

    public HashMap<Long, Person> getAllPeople() {
        HashMap<Long, Person> people = new LinkedHashMap<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Person.TABLE_NAME, null);
        // Add each person to hashmap (Each row has 1 person)
        while (cursor.moveToNext()) {
            Person person = new Person(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2));
            people.put(person.getId(), person);
        }
        cursor.close();
        db.close();

        if(people.size() == 0) {
            // If there are no people in the db then add some default people
            createDefaultPeople();
            people = getAllPeople();
        }
        return people;
    }

    public void removePerson(Person person)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Person.TABLE_NAME,
                Person.COLUMN_ID + " = ?",
                new String[] {String.valueOf(person.getId())});
    }

    private void createDefaultPeople()
    {
        addPerson(new Person(0, "Neptune", "Australia"));
        addPerson(new Person(1, "NepGear", "Asia"));
        addPerson(new Person(2, "Noire", "North America"));
        addPerson(new Person(3, "Uni", "South America"));
        addPerson(new Person(4, "Vert", "Europe"));
        addPerson(new Person(5, "Blanc", "Australia"));
        addPerson(new Person(6, "Rom", "Africa"));
        addPerson(new Person(7, "Ram", "Asia"));
    }
}
