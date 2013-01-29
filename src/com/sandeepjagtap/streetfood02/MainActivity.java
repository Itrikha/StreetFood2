package com.sandeepjagtap.streetfood02;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	ListView lview;
    private String TAG="MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lview=(ListView) findViewById(R.id.listView1);
		
		
		//Assigning Buttons OnClick Events
				Button btnNearBy = (Button) findViewById(R.id.btnNearBy);
				btnNearBy.setOnClickListener(new View.OnClickListener() {

				    public void onClick(View v) {
				        showNearByStalls(v);
				    }
				});


				//Assigning Buttons OnClick Events
				Button btnAZ = (Button) findViewById(R.id.btnAZ);
				btnAZ.setOnClickListener(new View.OnClickListener() {

				    public void onClick(View v) {
				        showAZ(v);
				    }
				});

				//Assigning OnClick Event to Popular Button
				Button btnPopular=(Button) findViewById(R.id.btnPopular);
				btnPopular.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						showPopular(v);			
					}
				});	

				//Assigning OnClick Event to Popular Button
				Button btnCategory=(Button) findViewById(R.id.btnCats);
				btnCategory.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						showCategory(v);			
					}
				});

				Button btnList = (Button) findViewById(R.id.btnLists);
				btnList.setOnClickListener(new View.OnClickListener() {

				    public void onClick(View v) {
				        fetured();
				    }
				});
				
				
				Button btnMaps = (Button) findViewById(R.id.btnLists);
				btnMaps.setOnClickListener(new View.OnClickListener() {

				    public void onClick(View v) {
				        showMaps();
				    }
				});
             
				
			 lview.setOnItemClickListener(new OnItemClickListener() {
			        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
			        Log.i(TAG,"You Clicked:"+ text1.getText().toString());
                    try{
			         Intent intent = new Intent(getApplicationContext(), ShowDetails.class);
			         //Create a bundle object
			         Bundle b = new Bundle();

			             //Inserts a String value into the mapping of this Bundle
			             b.putString("shopName", text1.getText().toString());
			            // b.putString("age", age.getText().toString()); we can put as many parameters we need

			             //Add the bundle to the intent.
			             intent.putExtras(b);

			             //start the DisplayActivity
			             startActivity(intent);
                    }
                    catch(Exception e)
                    {
                      Log.e(TAG,"Exception While Creating Intent: "+e.toString());
			        }
			      }
			 });




	}
	
	public void fetured()
	{
		Log.i(TAG,"Populating fetured Stall list");
		ArrayList<String> list=new ArrayList<String>();
		String sql="select  shopName from streetShopInfo where ratings >3 order by shopName";
		Log.i(TAG,"Creating Adapter for Fetching Data");
		StreetFoodDataBaseAdapter mDBAdapter= new StreetFoodDataBaseAdapter(this);
		Log.i(TAG,"Adapter Ready..");
		Log.i(TAG,"Creating/Opening Database");
		mDBAdapter.createDatabase();       
		mDBAdapter.open();
		Log.i(TAG,"Requesting info from getInfo function");
		list=mDBAdapter.getInfo(sql,"shopName");
		Log.i(TAG,"Information Retrived Passing it to SetView");
		setView(list);
	}

	public void showPopular(View v){
		ArrayList<String> list=new ArrayList<String>();
		String sql="select  shopName from streetShopInfo where ratings >3 order by shopName";
		Log.i(TAG,"Creating Adapter for Fetching Data");
		StreetFoodDataBaseAdapter mDBAdapter= new StreetFoodDataBaseAdapter(this);
		Log.i(TAG,"Adapter Ready..");
		Log.i(TAG,"Creating/Opening Database");
		mDBAdapter.createDatabase();       
		mDBAdapter.open();
		Log.i(TAG,"Requesting info from getInfo function");
		list=mDBAdapter.getInfo(sql,"shopName");
		Log.i(TAG,"Information Retrived Passing it to SetView");
		setView(list);
	}

	public void showNearByStalls(View v) {
		ArrayList<String> list=new ArrayList<String>();
		String sql="select shopName from streetShopInfo LIMIT 10";
		StreetFoodDataBaseAdapter mDBAdapter= new StreetFoodDataBaseAdapter(this);
		mDBAdapter.createDatabase();       
		mDBAdapter.open();
		list=mDBAdapter.getInfo(sql,"shopName");
		Log.i(TAG,"Cursor Values Retrived into Array list");
		setView(list);


	}


	public void showAZ(View v){
		ArrayList<String> list=new ArrayList<String>();
		String sql="select shopName from streetShopInfo order by shopName";
		StreetFoodDataBaseAdapter mDBAdapter= new StreetFoodDataBaseAdapter(this);
		mDBAdapter.createDatabase();       
		mDBAdapter.open();
		list=mDBAdapter.getInfo(sql,"shopName");
		Log.i(TAG,"Cursor Values Retrived into Array list");
		setView(list);

	}

	public void showCategory(View v){
		ArrayList<String> list=new ArrayList<String>();
		String sql="select  distinct category from streetShopInfo order by category";
		StreetFoodDataBaseAdapter mDBAdapter= new StreetFoodDataBaseAdapter(this);
		mDBAdapter.createDatabase();       
		mDBAdapter.open();
		list=mDBAdapter.getInfo(sql,"category");
		Log.i(TAG,"Cursor Values Retrived into Array list");
		setView(list);
	}



	public void setView(ArrayList<String> info)
	{
		Log.i(TAG,"Setting View");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>
		(this,android.R.layout.simple_list_item_1, android.R.id.text1,info);

		Log.i(TAG,"Array Adapter Set");
		Log.i(TAG,info.toString());
		// Assign adapter to ListView
		Log.i(TAG,"Attaching Arrya Adapter to List View");
		lview.setAdapter(adapter);
		Log.i(TAG,"View Set Succesfully");

	}

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}*/
	
	public void showMaps(){
		
	}

	@Override
	protected void onStart() {
	    super.onStart();
	  //default show Popular Shops
	    Log.i(TAG,"I am in Main Activity Start");
	  		fetured();
	}
	
}
