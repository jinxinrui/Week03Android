package edu.monash.fit3027ffit4039.week03prac.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rayna on 10/03/2017.
 */

public class Person implements Parcelable
{
    private long _id;
    private String mName;
    private String mCountry;
    private String mClass;

    // database constants
    public static final String TABLE_NAME = "people";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_COUNTRY = "country";

    // Table create statement
    public static final String CREATE_STATEMENT = "CREATE TABLE "
            + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUMN_NAME + " TEXT NOT NULL, " +
            COLUMN_COUNTRY + " TEXT NOT NULL" +
            ")";

    public Person() {
        mName = "IF";
        mCountry = "Planeptune";
        mClass = "Rogue";
    }

    public Person(long id, String name, String country) {
        this._id = id;
        this.mName = name;
        this.mCountry = country;
    }

    public Person(String name, String country) {
        this.mName = name;
        this.mCountry = country;
    }

    protected Person(Parcel in) {
        _id = in.readLong();
        mName = in.readString();
        mCountry = in.readString();
        mClass = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(_id);
        parcel.writeString(mName);
        parcel.writeString(mCountry);
        parcel.writeString(mClass);
    }

    public long getId() { return _id; }

    public void setId(long _id) { this._id = _id; }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        this.mCountry = country;
    }
}
