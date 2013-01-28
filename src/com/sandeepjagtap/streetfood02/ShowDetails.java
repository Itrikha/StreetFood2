package com.sandeepjagtap.streetfood02;

import java.util.ArrayList;

import android.location.Address;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowDetails extends Activity {

	private static final String TAG = "ShowDetails";
	String sql;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_details);
		Bundle b = getIntent().getExtras();
		String shopName=(String) b.get("shopName");
		
		TextView txtShopName = (TextView) findViewById(R.id.txtShopName);	
		txtShopName.setText(shopName);
		
		TextView txtShopInfo = (TextView) findViewById(R.id.txtShopInfo);	
		TextView txtRating = (TextView) findViewById(R.id.txtRating);	
		TextView txtAddress = (TextView) findViewById(R.id.txtAddress);	
			
		// get relevant info from db
		sql="select info,ratings,address,contact from streetShopInfo where shopName=\""+shopName+"\"";
		StreetFoodDataBaseAdapter mDBAdapter= new StreetFoodDataBaseAdapter(this);
		mDBAdapter.createDatabase();       
		mDBAdapter.open();
		String info=mDBAdapter.getSingleStringVal(sql, "info");
		txtShopInfo.setText(info);
		Log.i(TAG,info);
	
		int ratings=mDBAdapter.getSingleIntVal(sql, "ratings");
		String ratingsinstring=String.valueOf(ratings);
		txtRating.setText(ratingsinstring);
		Log.i(TAG,"Got Rating");
	
		String address=mDBAdapter.getSingleStringVal(sql, "address");
		txtAddress.setText("Address: \n"+address);
		Log.i(TAG,"Got Address:"+address);
		
		Button btnList = (Button) findViewById(R.id.btnLists);
		btnList.setOnClickListener(new View.OnClickListener() {

		    public void onClick(View v) {
		        goBackToListView(v);
		    }
		});
		
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_show_details, menu);
		return true;
	}
	
	public void goBackToListView(View v)
	{
		 Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		 //start the DisplayActivity
         startActivity(intent);
	}

}
