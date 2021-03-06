package com.sandeepjagtap.streetfood02;

import java.io.IOException;
import java.util.ArrayList;

//import android.content.ContentValues; //Need While inserting values
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class StreetFoodDataBaseAdapter {

	protected static final String TAG = "DataAdapter"; 
	 
    private final Context mContext; 
    private SQLiteDatabase mDb; 
    private StreetFoodDataBaseOpenHelper mDbHelper; 
    
    //Constructor for StreetFoodDataBaseAdapter
    
    public StreetFoodDataBaseAdapter(Context context)  
    { 
        this.mContext = context; 
        mDbHelper = new StreetFoodDataBaseOpenHelper(mContext); 
    } 
	
    public StreetFoodDataBaseAdapter createDatabase() throws SQLException  
    { 
        try  
        { 
            mDbHelper.createDataBase(); 
        }  
        catch (IOException mIOException)  
        { 
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase"); 
            throw new Error("UnableToCreateDatabase"); 
        } 
        return this; 
    }
    
    public StreetFoodDataBaseAdapter open() throws SQLException  
    { 
        try  
        { 
            mDbHelper.openDataBase(); 
            mDbHelper.close(); 
            Log.i(TAG,"Asking for Readable Acess to DB");
            mDb = mDbHelper.getReadableDatabase(); 
            Log.i(TAG,"Readable Access Granted");
        }  
        catch (SQLException mSQLException)  
        { 
            Log.e(TAG, "open >>"+ mSQLException.toString()); 
            throw mSQLException; 
        } 
        return this; 
    } 
    
    public void close()  
    { 
        mDbHelper.close(); 
    }	
    
   /* public Cursor getTestData() 
    { 
        try 
        { 
            String sql ="SELECT EmployeeId, Name, Email FROm Employees"; 

            Cursor mCur = mDb.rawQuery(sql, null); 
            if (mCur!=null) 
            { 
               mCur.moveToNext(); 
            } 
            return mCur; 
        } 
        catch (SQLException mSQLException)  
        { 
            Log.e(TAG, "getTestData >>"+ mSQLException.toString()); 
            throw mSQLException; 
        } 
    }
    */
    
    public ArrayList<String> getInfo(String sql,String column)
    {
    	ArrayList<String> result = new ArrayList<String>();
    	Log.i(TAG,"Getting Information..");
    	try 
        { 
             
            Log.i(TAG,"Executing Query"+sql);
            Cursor mCur = mDb.rawQuery(sql, null); 
            if  (mCur.moveToFirst()) 
            {
                
               do {
                    	      	  
                   String columnValue = mCur.getString(mCur.getColumnIndex(column));
                   //Log.i(TAG,"Values: "+shopName);
                   result.add(columnValue);
               }while (mCur.moveToNext());
                     
               mCur.close();
                	}
             else
             {
                	mCur.close();
                	Log.e(TAG,"No Data Received from Query");
             }
            
            				 
        }
        catch (Exception e)  
        { 
            Log.e(TAG, "getInfo >>"+ e.toString()); 
           // throw mSQLException; 
        } 
		return result;
    	
    }
    
    
    
    public int getSingleIntVal(String sql,String column)
    {
    	int columnVal = 0;
    	Log.i(TAG,"Getting Information..");
    	try 
        { 
             
            Log.i(TAG,"Executing Query"+sql);
            Cursor mCur = mDb.rawQuery(sql, null); 
            if  (mCur.moveToFirst()) 
            {
                
               do {
                    	      	  
            	   columnVal= mCur.getInt(mCur.getColumnIndex(column));
                                      
               }while (mCur.moveToNext());
                     
               mCur.close();
                	}
             else
             {
                	mCur.close();
                	Log.e(TAG,"No Data Received from Query");
             }
            
            				 
        }
        catch (Exception e)  
        { 
            Log.e(TAG, "getSingleIntVal >>"+ e.toString()); 
           // throw mSQLException; 
        } 
		return columnVal;
    	
    }
    
    public String getSingleStringVal(String sql,String column)
    {
    	String columnVal = null;
    	Log.i(TAG,"Getting Information..");
    	try 
        { 
             
            Log.i(TAG,"Executing Query"+sql);
            Cursor mCur = mDb.rawQuery(sql, null); 
            if  (mCur.moveToFirst()) 
            {
                
               do {
                    	      	  
            	   columnVal= mCur.getString(mCur.getColumnIndex(column));
                                      
               }while (mCur.moveToNext());
                     
               mCur.close();
                	}
             else
             {
                	mCur.close();
                	Log.e(TAG,"No Data Received from Query");
             }
            
            				 
        }
        catch (Exception e)  
        { 
            Log.e(TAG, "getSingleStringVal >>"+ e.toString()); 
           // throw mSQLException; 
        } 
		return columnVal;
    	
    }
    
   /* Function to insert values
    public boolean Insert Values(String name, String email) 
 	{
 		try
 		{
 			ContentValues cv = new ContentValues();
 			cv.put("Name", name);
 			cv.put("Email", email);
 			
 			mDb.insert("Employees", null, cv);

 			Log.d("SaveEmployee", "information saved");
 			return true;
 			
 		}
 		catch(Exception ex)
 		{
 			Log.d("SaveEmployee", ex.toString());
 			return false;
 		}
 	}
 	*/
    
}
