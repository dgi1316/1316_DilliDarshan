package com.hard.targets.dilli.darshan;

import com.hard.targets.dilli.darshan.imagezoom.ImageViewTouch;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ImageActivity extends Activity {

	String SUB_TITLE, FILE_NAME;
	private ImageViewTouch mImageView;
	Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		Intent t = getIntent();
		SUB_TITLE = t.getStringExtra("SUB_TITLE");
		FILE_NAME = t.getStringExtra("FILE_NAME");
		setupActionBar();
		
		if (FILE_NAME.equalsIgnoreCase("map"))
			bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.map);
		else if (FILE_NAME.equalsIgnoreCase("map_1"))
			bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.map_1);
		else if (FILE_NAME.equalsIgnoreCase("map_2"))
			bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.map_2);
		else if (FILE_NAME.equalsIgnoreCase("map_3"))
			bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.map_3);
		else if (FILE_NAME.equalsIgnoreCase("map_4"))
			bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.map_4);
		else if (FILE_NAME.equalsIgnoreCase("map_5"))
			bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.map_5);
		
		mImageView = (ImageViewTouch) findViewById(R.id.imageView1);
		mImageView.setImageBitmapReset(bitmap, true);
	}

	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setSubtitle(SUB_TITLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.image, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
