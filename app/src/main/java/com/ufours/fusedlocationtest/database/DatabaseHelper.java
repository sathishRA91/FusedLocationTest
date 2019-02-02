/*
package com.ufours.fusedlocationtest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "LocationDB";


    private static final String TABLE_RECENT_VIEW = "table_recent_view";
    private static final String TABLE_COUNTRY = "table_country";
    private static final String TABLE_STATE_CITY = "table_state_city";
    private static final String TABLE_HISTORY = "table_history";
    private static final String TABLE_RECENT_SEARCH = "table_recent_search";


    private static final String KEY_TRIPID = "id";
    private static final String TRIP_ID = "TripID";
    private static final String TRIP_SOURCE = "Source";
    private static final String TRIP_DESTINATION = "Destination";
    private static final String TRIP_DEPARTUREDATE = "DepartureDate";
    private static final String TRIP_ARRIVALDATE = "ArrivalDate";
    private static final String TRIP_TRAVELMODE = "TravelMode";
    private static final String TRIP_PACKAGEWEIGHT = "PackageWeight";
    private static final String TRIP_CARRIERID = "CarrierID";
    private static final String TRIP_FIRSTNAME = "Firstname";
    private static final String TRIP_MOBILE = "Mobile";
    private static final String TRIP_ISCLICK = "isClick";
    private static final String TRIP_PROFILEIMAGE = "ProfileImage";


    private static final String KEY_HISTORY_ID = "key_history_id";
    private static final String HISTORY_ID = "history_id";
    private static final String HISTORY_SOURCE_ID = "history_source_id";
    private static final String HISTORY_DESTINATION_ID = "history_destination_id";
    private static final String HISTORY_SOURCE = "history_source";
    private static final String HISTORY_DESTINATION = "history_destination";
    private static final String HISTORY_DEPARTUREDATE = "history_departuredate";
    private static final String HISTORY_ARRIVALDATE = "history_arrivaldate";
    private static final String HISTORY_TRAVELMODE = "history_travelmode";
    private static final String HISTORY_PACKAGEWEIGHT = "history_packageweight";
    private static final String HISTORY_STATUS = "history_status";
    private static final String HISTORY_CARRIERID = "history_carrierid";


    private static final String KEY_COUNTRY_ID = "key_country_id";
    private static final String COUNTRY_ID = "country_id";
    private static final String COUNTRY_NAME = "country_name";
    private static final String COUNTRY_CODE = "country_code";
    private static final String COUNTRY_DIAL_CODE = "country_dial_code";

    private static final String KEY_CITY_STATE_ID = "key_city_state_id";
    private static final String CITY_STATE_COUNTRY_ID = "city_state_country_id";
    private static final String CITY_STATE_NAME = "city_state_name";


    private static final String KEY_RECENT_SEARCH_ID = "key_recent_search_id";
    private static final String RECENT_SERACH_SOURCE = "recent_serach_source";
    private static final String RECENT_SERACH_DESTINATION = "recent_serach_destination";
    private static final String RECENT_SERACH_TRAVEL_DATE = "recent_serach_travel_date";
    private static final String RECENT_SERACH_URL = "recent_serach_url";
    private static final String RECENT_SERACH_SOURCE_CODE = "recent_serach_source_code";
    private static final String RECENT_SERACH_DESTINATION_CODE = "recent_serach_destination_code";
    private static final String RECENT_SERACH_PACKAGEWEIGHT = "recent_serach_packageweight";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String CREATE_TABLE_TRIP = "CREATE TABLE " + TABLE_RECENT_VIEW + "("
                + KEY_TRIPID + " INTEGER PRIMARY KEY," + TRIP_ID + " TEXT,"
                + TRIP_SOURCE + " TEXT," + TRIP_DESTINATION + " TEXT," + TRIP_DEPARTUREDATE + " TEXT,"
                + TRIP_ARRIVALDATE + " TEXT," + TRIP_TRAVELMODE + " TEXT,"
                + TRIP_PACKAGEWEIGHT + " TEXT," + TRIP_CARRIERID + " TEXT,"
                + TRIP_FIRSTNAME + " TEXT," + TRIP_MOBILE + " TEXT,"
                + TRIP_ISCLICK + " TEXT," + TRIP_PROFILEIMAGE + " TEXT" + ")";


        String CREATE_TABLE_COUNTRY = "CREATE TABLE " + TABLE_COUNTRY + "("
                + KEY_COUNTRY_ID + " INTEGER PRIMARY KEY," + COUNTRY_ID + " TEXT,"
                + COUNTRY_NAME + " TEXT," + COUNTRY_CODE + " TEXT," + COUNTRY_DIAL_CODE + " TEXT" + ")";


        String CREATE_TABLE_STATE_CITY = "CREATE TABLE " + TABLE_STATE_CITY + "("
                + KEY_CITY_STATE_ID + " INTEGER PRIMARY KEY,"
                + CITY_STATE_COUNTRY_ID + " TEXT,"
                + CITY_STATE_NAME + " TEXT" + ")";


        String CREATE_TABLE_HISTORY = "CREATE TABLE " + TABLE_HISTORY + "("
                + KEY_HISTORY_ID + " INTEGER PRIMARY KEY," + HISTORY_ID + " TEXT,"
                + HISTORY_SOURCE_ID + " TEXT," + HISTORY_DESTINATION_ID + " TEXT," + HISTORY_SOURCE + " TEXT,"
                + HISTORY_DESTINATION + " TEXT," + HISTORY_DEPARTUREDATE + " TEXT,"
                + HISTORY_ARRIVALDATE + " TEXT," + HISTORY_TRAVELMODE + " TEXT,"
                + HISTORY_PACKAGEWEIGHT + " TEXT," + HISTORY_STATUS + " TEXT,"
                + HISTORY_CARRIERID + " TEXT" + ")";


        String CREATE_TABLE_RECENT_SEARCH = "CREATE TABLE " + TABLE_RECENT_SEARCH + "("
                + KEY_RECENT_SEARCH_ID + " INTEGER PRIMARY KEY,"
                + RECENT_SERACH_SOURCE + " TEXT," + RECENT_SERACH_DESTINATION + " TEXT,"
                + RECENT_SERACH_TRAVEL_DATE + " TEXT," + RECENT_SERACH_URL + " TEXT,"
                + RECENT_SERACH_SOURCE_CODE + " TEXT," + RECENT_SERACH_DESTINATION_CODE + " TEXT,"
                + RECENT_SERACH_PACKAGEWEIGHT + " TEXT" + ")";


        sqLiteDatabase.execSQL(CREATE_TABLE_TRIP);
        sqLiteDatabase.execSQL(CREATE_TABLE_COUNTRY);
        sqLiteDatabase.execSQL(CREATE_TABLE_STATE_CITY);
        sqLiteDatabase.execSQL(CREATE_TABLE_HISTORY);
        sqLiteDatabase.execSQL(CREATE_TABLE_RECENT_SEARCH);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECENT_VIEW);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRY);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATE_CITY);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECENT_SEARCH);

        // Create tables again
        onCreate(db);
    }

    //Insert Trip
    public long INSERT_RECENT_VIEW_TRIP(ItemModel data) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();


        values.put(TRIP_ID, String.valueOf(data.getTripID()));
        values.put(TRIP_SOURCE, data.getSource());
        values.put(TRIP_DESTINATION, data.getDestination());
        values.put(TRIP_DEPARTUREDATE, data.getDepartureDate());
        values.put(TRIP_ARRIVALDATE, data.getArrivalDate());
        values.put(TRIP_TRAVELMODE, data.getTravelMode());
        values.put(TRIP_PACKAGEWEIGHT, String.valueOf(data.getPackageWeight()));
        values.put(TRIP_CARRIERID, String.valueOf(data.getCarrierID()));
        values.put(TRIP_FIRSTNAME, data.getFirstname());
        values.put(TRIP_MOBILE, data.getMobile());
        values.put(TRIP_ISCLICK, String.valueOf(data.isClickCheck()));
        values.put(TRIP_PROFILEIMAGE, data.getProfileImage());

        long id = -1;

        id = db.insert(TABLE_RECENT_VIEW, null, values);

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return id;
    }


    //Insert Country
    public long INSERT_COUNTRY(ItemModel data) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();


        values.put(COUNTRY_ID, data.getCountryID());
        values.put(COUNTRY_NAME, data.getCountryName());
        values.put(COUNTRY_CODE, data.getCountryCode());
        values.put(COUNTRY_DIAL_CODE, data.getCountryDialoCde());

        long id = -1;

        id = db.insert(TABLE_COUNTRY, null, values);

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return id;
    }

    //Insert State_City

    //Insert Country
    public long INSERT_STATE_CITY(String countryId, String dataModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();

        values.put(CITY_STATE_COUNTRY_ID, countryId);
        values.put(CITY_STATE_NAME, dataModel);

        long id = -1;

        id = db.insert(TABLE_STATE_CITY, null, values);


        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return id;
    }


    //Insert Trip
    public long INSERT_HISTORY(ItemModel data) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();


        values.put(HISTORY_ID, String.valueOf(data.getAUID()));
        values.put(HISTORY_SOURCE_ID, String.valueOf(data.getSourceCountryID()));
        values.put(HISTORY_DESTINATION_ID, String.valueOf(data.getDestinationCountryID()));
        values.put(HISTORY_SOURCE, data.getSource());
        values.put(HISTORY_DESTINATION, data.getDestination());
        values.put(HISTORY_DEPARTUREDATE, data.getDepartureDate());
        values.put(HISTORY_ARRIVALDATE, data.getArrivalDate());
        values.put(HISTORY_TRAVELMODE, data.getTravelMode());
        values.put(HISTORY_PACKAGEWEIGHT, String.valueOf(data.getPackageWeight()));
        values.put(HISTORY_STATUS, data.getHistoryStatus());
        values.put(HISTORY_CARRIERID, String.valueOf(data.getCarrierID()));

        long id = -1;

        id = db.insert(TABLE_HISTORY, null, values);

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return id;
    }


    //Insert Recent Search
    public long INSERT_RECENT_SEARCH(ItemModel data) {

        SQLiteDatabase db = this.getWritableDatabase();
        long id = -1;
        Cursor cursor = null;
        String selectQuery = "SELECT  * FROM " + TABLE_RECENT_SEARCH + " WHERE " + RECENT_SERACH_SOURCE
                + "= '" + data.getRecentSearchSource() + "'"
                + " AND " + RECENT_SERACH_DESTINATION + "='" + data.getRecentSearchDestination() + "'";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();

            String selectQueryNew = "SELECT  * FROM " + TABLE_RECENT_SEARCH + " WHERE " + RECENT_SERACH_TRAVEL_DATE
                    + "!= '" + data.getRecentSearchTravelDate() + "'" + " OR " +
                    RECENT_SERACH_PACKAGEWEIGHT + "!= '" + data.getRecentSearchPackageWeight() + "'";

            cursor = db.rawQuery(selectQueryNew, null);

            if (cursor != null && cursor.getCount() > 0) {
                ContentValues values = new ContentValues();

                values.put(RECENT_SERACH_TRAVEL_DATE, data.getRecentSearchTravelDate());
                values.put(RECENT_SERACH_PACKAGEWEIGHT, data.getRecentSearchPackageWeight());

                id = db.update(TABLE_RECENT_SEARCH, values,
                        RECENT_SERACH_SOURCE + " = ? AND " + RECENT_SERACH_DESTINATION + " = ?",
                        new String[]{data.getRecentSearchSource(), data.getRecentSearchDestination()});

                cursor.close();

            }


            db.close();
            return id;
        } else {
            db.beginTransaction();
            ContentValues values = new ContentValues();

            values.put(RECENT_SERACH_SOURCE, data.getRecentSearchSource());
            values.put(RECENT_SERACH_DESTINATION, data.getRecentSearchDestination());
            values.put(RECENT_SERACH_TRAVEL_DATE, data.getRecentSearchTravelDate());
            values.put(RECENT_SERACH_URL, data.getRecentSearchUrl());

            values.put(RECENT_SERACH_SOURCE_CODE, data.getRecentSearchSourceCountyCode());
            values.put(RECENT_SERACH_DESTINATION_CODE, data.getRecentSearchDestinationCountryCode());

            values.put(RECENT_SERACH_PACKAGEWEIGHT, data.getRecentSearchPackageWeight());


            id = db.insert(TABLE_RECENT_SEARCH, null, values);
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();

            return id;
        }

    }


    //Get Recent Search

    public ArrayList<ItemModel> GET_RECENT_SEARCH() {

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ItemModel> dataList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_RECENT_SEARCH + " ORDER BY " + KEY_RECENT_SEARCH_ID + "  DESC LIMIT " + 5;


        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {
                do {
                    ItemModel modelData = new ItemModel();

                    modelData.setRecentSearchId(String.valueOf(cursor.getString(0)));
                    modelData.setRecentSearchSource(cursor.getString(1));
                    modelData.setRecentSearchDestination(cursor.getString(2));
                    modelData.setRecentSearchTravelDate(cursor.getString(3));
                    modelData.setRecentSearchUrl(cursor.getString(4));
                    modelData.setRecentSearchSourceCountyCode(cursor.getString(5));
                    modelData.setRecentSearchDestinationCountryCode(cursor.getString(6));
                    modelData.setRecentSearchPackageWeight(cursor.getString(7));

                    dataList.add(modelData);

                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return dataList;
    }

    //Get_histor
    public ArrayList<ItemModel> GET_HiSTORY() {

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ItemModel> dataList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_HISTORY;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.getCount() > 0) {


            if (cursor.moveToFirst()) {
                do {
                    ItemModel modelData = new ItemModel();

                    modelData.setAUID(Integer.parseInt(cursor.getString(1)));
                    modelData.setSourceCountryID(Integer.parseInt(cursor.getString(2)));
                    modelData.setDestinationCountryID(Integer.parseInt(cursor.getString(3)));
                    modelData.setSource(cursor.getString(4));
                    modelData.setDestination(cursor.getString(5));
                    modelData.setDepartureDate(cursor.getString(6));
                    modelData.setArrivalDate(cursor.getString(7));
                    modelData.setTravelMode(cursor.getString(8));
                    modelData.setPackageWeight(Integer.parseInt(cursor.getString(9)));
                    modelData.setHistoryStatus(cursor.getString(10));
                    modelData.setCarrierID(Integer.parseInt(cursor.getString(11)));

                    dataList.add(modelData);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return dataList;
    }


    //Get_Country
    public ArrayList<ItemModel> GET_COUNTRY() {

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ItemModel> dataList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_COUNTRY;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {
                do {
                    ItemModel modelData = new ItemModel();

                    modelData.setCountryID(cursor.getString(1));
                    modelData.setCountryName(cursor.getString(2));
                    modelData.setCountryCode(cursor.getString(3));
                    modelData.setCountryDialoCde(cursor.getString(4));

                    dataList.add(modelData);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return dataList;
    }


    //Get CountryId
    public String GET_COUNTRY_ID(String countryCode) {

        SQLiteDatabase db = this.getWritableDatabase();

        String countryCodeData = "";

        String selectQuery = "SELECT  * FROM " + TABLE_COUNTRY + " WHERE " + COUNTRY_CODE + "= '" + countryCode + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {
                do {

                    countryCodeData = cursor.getString(1);


                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return countryCodeData;
    }


    //GetStateCity

    public ArrayList<ItemModel> GET_STATE_CITY(String countryId) {

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ItemModel> dataList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_STATE_CITY + " WHERE " + CITY_STATE_COUNTRY_ID + "= '" + countryId + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {
                do {

                    try {
                        JSONArray jsonArray = new JSONArray(cursor.getString(2));

                        //Obstric

                        if (jsonArray != null) {

                            for (int i = 0; i < jsonArray.length(); i++) {

                                ItemModel modelData = new ItemModel();

                                JSONObject jsondata = jsonArray.getJSONObject(i);

                                try {

                                    String cityName = jsondata.getString("cityName");
                                    String countryName = jsondata.getString("countryName");
                                    String stateName = jsondata.getString("stateName");

                                    String statecityData = cityName + "," + stateName + "," + countryName;


                                    modelData.setStateCityCountryId(cursor.getString(1));
                                    modelData.setStateCityName(statecityData);

                                    dataList.add(modelData);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return dataList;
    }


    //Get All Trip
    public ArrayList<ItemModel> GET_ALL_RECENT_VIEW_TRIP() {

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ItemModel> dataList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_RECENT_VIEW + " ORDER BY " + KEY_TRIPID + "  DESC ";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {
                do {

                    ItemModel modelData = new ItemModel();

                    modelData.setTripID(Integer.parseInt(cursor.getString(1)));
                    modelData.setSource(cursor.getString(2));
                    modelData.setDestination(cursor.getString(3));
                    modelData.setDepartureDate(cursor.getString(4));
                    modelData.setArrivalDate(cursor.getString(5));
                    modelData.setTravelMode(cursor.getString(6));
                    modelData.setPackageWeight(Integer.parseInt(cursor.getString(7)));
                    modelData.setCarrierID(Integer.parseInt(cursor.getString(8)));
                    modelData.setFirstname(cursor.getString(9));
                    modelData.setMobile(cursor.getString(10));
                    modelData.setClickCheck(Boolean.parseBoolean(cursor.getString(11)));
                    modelData.setProfileImage(cursor.getString(12));


                    dataList.add(modelData);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return dataList;
    }

    public void DELETE_RECENT_VIEW_TRIP(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECENT_VIEW, TRIP_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }


    public void DELETE_ALL_RECENT_VIEW_TRIP() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_RECENT_VIEW, null, null);

        db.close();
    }


    public void DELETE_HISTORY(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HISTORY, HISTORY_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }


    public void DELETE_ALL_HISTORY() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_HISTORY, null, null);

        db.close();
    }


    public void DELETE_COUNTRY() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_COUNTRY, null, null);

        db.close();
    }

    public void DELETE_RECENT_SEARCH() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_RECENT_SEARCH, null, null);

        db.close();
    }

    public void DELETE_ALL_STATE_CITY() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_STATE_CITY, null, null);

        db.close();
    }

    public boolean checkAlreadyTrip(String tripId) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        String sql = "SELECT * FROM " + TABLE_RECENT_VIEW + " WHERE "
                + TRIP_ID + "='" + tripId + "'";
        cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }

    }


}*/
