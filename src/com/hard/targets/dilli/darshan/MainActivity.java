package com.hard.targets.dilli.darshan;

import java.util.Locale;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {

	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mListTitles;
    
    static int i = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mTitle = mDrawerTitle = getTitle();
        mListTitles = getResources().getStringArray(R.array.list_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mListTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,  
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_more).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
		
		switch(item.getItemId()) {
		case R.id.action_rate:
			
			return true;
		case R.id.action_share:
			
			return true;
        case R.id.action_about:
            Intent intent = new Intent("");
            startActivity(intent);
            return true;
        case R.id.action_disclaimer:
        	
        	return true;
        case R.id.action_exit:
        	
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			new AlertDialog.Builder(this)
			.setIcon(R.drawable.ic_action_alert)
			.setMessage(R.string.dialog_message)
			.setTitle(R.string.dialog_title)
			.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					MainActivity.this.finish();
				}
			})
			.setNegativeButton(R.string.no, null)
			.setCancelable(false)
			.show();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
	
	private void selectItem(int position) {
        Fragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt(ListFragment.ARG_ITEM_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(mListTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
	
	@Override
    public void setTitle(CharSequence title) {
        //mTitle = title;
        getActionBar().setSubtitle(title);
    }
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    public static class ListFragment extends Fragment {
        public static final String ARG_ITEM_NUMBER = "item_number";
        
        //Fragments
        SectionsPagerAdapter mSectionsPagerAdapter;
    	ViewPager mViewPager;
    	View root = null;
        
        public ListFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        	
            i = getArguments().getInt(ARG_ITEM_NUMBER);
            root = inflater.inflate(R.layout.fragment_l0, container, false);
            //Fragments
            mSectionsPagerAdapter = new SectionsPagerAdapter(((FragmentActivity) getActivity()).getSupportFragmentManager());

    		mViewPager = (ViewPager) root.findViewById(R.id.pager);
    		mViewPager.setAdapter(mSectionsPagerAdapter);
            return root;
        }
        
        public class SectionsPagerAdapter extends android.support.v4.app.FragmentStatePagerAdapter {

    		public SectionsPagerAdapter(android.support.v4.app.FragmentManager fm) {
    			super(fm);
    		}

    		@Override
    		public android.support.v4.app.Fragment getItem(int position) {
    			android.support.v4.app.Fragment fragment = new DummySectionFragment();
    			Bundle args = new Bundle();
    			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position);
    			fragment.setArguments(args);
    			return fragment;
    		}

    		@Override
    		public int getCount() {
    			switch (i) {
				case 0:
					return 9;
				case 1:
					return 8;
				case 2:
					return 10;
				case 3:
					return 5;
				case 4:
					return 7;
				case 5:
					return 9;
				case 6:
					return 1;
				default:
					return 0;
				}
    		}

    		@Override
    		public CharSequence getPageTitle(int position) {
    			if (i == 0) {
    				switch (position) {
    				case 0:
    					return getString(R.string.title_section00);
    				case 1:
    					return getString(R.string.title_section01);
    				case 2:
    					return getString(R.string.title_section02);
    				case 3:
    					return getString(R.string.title_section03);
    				case 4:
    					return getString(R.string.title_section04);
    				case 5:
    					return getString(R.string.title_section05);
    				case 6:
    					return getString(R.string.title_section06);
    				case 7:
    					return getString(R.string.title_section07);
    				case 8:
    					return getString(R.string.title_section08);
    				}
    			} else if (i == 1) {
    				switch (position) {
					case 0:
						return getString(R.string.title_section10);
					case 1:
						return getString(R.string.title_section11);
					case 2:
						return getString(R.string.title_section12);
					case 3:
						return getString(R.string.title_section13);
					case 4:
						return getString(R.string.title_section14);
					case 5:
						return getString(R.string.title_section15);
					case 6:
						return getString(R.string.title_section16);
					case 7:
						return getString(R.string.title_section17);
					}
    			} else if (i == 2) {
    				switch (position) {
    				case 0:
    					return getString(R.string.title_section20);
    				case 1:
    					return getString(R.string.title_section21);
    				case 2:
    					return getString(R.string.title_section22);
    				case 3:
    					return getString(R.string.title_section23);
    				case 4:
    					return getString(R.string.title_section24);
    				case 5:
    					return getString(R.string.title_section25);
    				case 6:
    					return getString(R.string.title_section26);
    				case 7:
    					return getString(R.string.title_section27);
    				case 8:
    					return getString(R.string.title_section28);
    				case 9:
    					return getString(R.string.title_section29);
    				}
    			} else if (i == 3) {
    				switch (position) {
    				case 0:
    					return getString(R.string.title_section30);
    				case 1:
    					return getString(R.string.title_section31);
    				case 2:
    					return getString(R.string.title_section32);
    				case 3:
    					return getString(R.string.title_section33);
    				case 4:
    					return getString(R.string.title_section34);
    				}
    			} else if (i == 4) {
    				switch (position) {
    				case 0:
    					return getString(R.string.title_section40);
    				case 1:
    					return getString(R.string.title_section41);
    				case 2:
    					return getString(R.string.title_section42);
    				case 3:
    					return getString(R.string.title_section43);
    				case 4:
    					return getString(R.string.title_section44);
    				case 5:
    					return getString(R.string.title_section45);
    				case 6:
    					return getString(R.string.title_section46);
    				}
    			} else if (i == 5) {
    				switch (position) {
    				case 0:
    					return getString(R.string.title_section50);
    				case 1:
    					return getString(R.string.title_section51);
    				case 2:
    					return getString(R.string.title_section52);
    				case 3:
    					return getString(R.string.title_section53);
    				case 4:
    					return getString(R.string.title_section54);
    				case 5:
    					return getString(R.string.title_section55);
    				case 6:
    					return getString(R.string.title_section56);
    				case 7:
    					return getString(R.string.title_section57);
    				case 8:
    					return getString(R.string.title_section58);
    				}
    			}
    			return null;
    		}
    	}

    	public static class DummySectionFragment extends android.support.v4.app.Fragment {
    		
    		public static final String ARG_SECTION_NUMBER = "section_number";
    		View rootView = null;
    		AdView av;

    		public DummySectionFragment() {
    		}

    		@Override
    		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    			
    			int position = getArguments().getInt(ARG_SECTION_NUMBER);
    			Locale l = Locale.getDefault();
    			
    			if (i == 0) {
    				rootView = null;
    				av = null;
    				switch (position) {
    				case 0:
    					rootView = inflater.inflate(R.layout.frame00, container, false);
    					WebView wv00 = (WebView) rootView.findViewById(R.id.wv00);
    					av = (AdView) rootView.findViewById(R.id.av00);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv00.loadUrl("file:///android_asset/www/frame00_hi.html");
    					else
    						wv00.loadUrl("file:///android_asset/www/frame00.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 1:
    					rootView = inflater.inflate(R.layout.frame01, container, false);
    					WebView wv01 = (WebView) rootView.findViewById(R.id.wv01);
    					av = (AdView) rootView.findViewById(R.id.av01);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv01.loadUrl("file:///android_asset/www/frame01_hi.html");
    					else
    						wv01.loadUrl("file:///android_asset/www/frame01.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 2:
    					rootView = inflater.inflate(R.layout.frame02, container, false);
    					WebView wv02 = (WebView) rootView.findViewById(R.id.wv02);
    					av = (AdView) rootView.findViewById(R.id.av02);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv02.loadUrl("file:///android_asset/www/frame02_hi.html");
    					else
    						wv02.loadUrl("file:///android_asset/www/frame02.html");
    					wv02.setWebViewClient(new WebViewClient() {

							@Override
							public boolean shouldOverrideUrlLoading(WebView view, String url) {
								if (url.equalsIgnoreCase("file:///android_asset/www/map")) {
									Intent i = new Intent("com.hard.targets.dilli.darshan.IMAGEACTIVITY");
									i.putExtra("SUB_TITLE", "Heritage Map");
									i.putExtra("FILE_NAME", "map");
									startActivity(i);
									return true;
								}
								return super.shouldOverrideUrlLoading(view, url);
							}
    						
    					});
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 3:
    					rootView = inflater.inflate(R.layout.frame03, container, false);
    					WebView wv03 = (WebView) rootView.findViewById(R.id.wv03);
    					av = (AdView) rootView.findViewById(R.id.av03);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv03.loadUrl("file:///android_asset/www/frame03_hi.html");
    					else
    						wv03.loadUrl("file:///android_asset/www/frame03.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 4:
    					rootView = inflater.inflate(R.layout.frame04, container, false);
    					WebView wv04 = (WebView) rootView.findViewById(R.id.wv04);
    					av = (AdView) rootView.findViewById(R.id.av04);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv04.loadUrl("file:///android_asset/www/frame04_hi.html");
    					else
    						wv04.loadUrl("file:///android_asset/www/frame04.html");
    					wv04.setWebViewClient(new WebViewClient() {

							@Override
							public boolean shouldOverrideUrlLoading(WebView view, String url) {
								Intent map = new Intent("com.hard.targets.dilli.darshan.IMAGEACTIVITY");
								if (url.equalsIgnoreCase("file:///android_asset/www/map_1")) {
									map.putExtra("SUB_TITLE", "Conaught Place");
									map.putExtra("FILE_NAME", "map_1");
									startActivity(map);
									return true;
								} else if (url.equalsIgnoreCase("file:///android_asset/www/map_2")) {
									map.putExtra("SUB_TITLE", "Chandni Chowk");
									map.putExtra("FILE_NAME", "map_2");
									startActivity(map);
									return true;
								} else if (url.equalsIgnoreCase("file:///android_asset/www/map_3")) {
									map.putExtra("SUB_TITLE", "Chanakyapuri");
									map.putExtra("FILE_NAME", "map_3");
									startActivity(map);
									return true;
								} else if (url.equalsIgnoreCase("file:///android_asset/www/map_4")) {
									map.putExtra("SUB_TITLE", "India Gate");
									map.putExtra("FILE_NAME", "map_4");
									startActivity(map);
									return true;
								} else if (url.equalsIgnoreCase("file:///android_asset/www/map_5")) {
									map.putExtra("SUB_TITLE", "Delhi Metro Map");
									map.putExtra("FILE_NAME", "map_5");
									startActivity(map);
									return true;
								} else
									return super.shouldOverrideUrlLoading(view, url);
							}
    						
    					});
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 5:
    					rootView = inflater.inflate(R.layout.frame05, container, false);
    					WebView wv05 = (WebView) rootView.findViewById(R.id.wv05);
    					av = (AdView) rootView.findViewById(R.id.av05);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv05.loadUrl("file:///android_asset/www/frame05_hi.html");
    					else
    						wv05.loadUrl("file:///android_asset/www/frame05.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 6:
    					rootView = inflater.inflate(R.layout.frame06, container, false);
    					WebView wv06 = (WebView) rootView.findViewById(R.id.wv06);
    					av = (AdView) rootView.findViewById(R.id.av06);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv06.loadUrl("file:///android_asset/www/frame06_hi.html");
    					else
    						wv06.loadUrl("file:///android_asset/www/frame06.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 7:
    					rootView = inflater.inflate(R.layout.frame07, container, false);
    					WebView wv07 = (WebView) rootView.findViewById(R.id.wv07);
    					av = (AdView) rootView.findViewById(R.id.av07);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv07.loadUrl("file:///android_asset/www/frame07_hi.html");
    					else
    						wv07.loadUrl("file:///android_asset/www/frame07.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 8:
    					rootView = inflater.inflate(R.layout.frame08, container, false);
    					WebView wv08 = (WebView) rootView.findViewById(R.id.wv08);
    					av = (AdView) rootView.findViewById(R.id.av08);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv08.loadUrl("file:///android_asset/www/frame08_hi.html");
    					else
    						wv08.loadUrl("file:///android_asset/www/frame08.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				default:
    					return rootView;
    				}
    			} else if (i == 1) {
    				rootView = null;
    				av = null;
    				switch (position) {
					case 0:
						rootView = inflater.inflate(R.layout.frame10, container, false);
						WebView wv10 = (WebView) rootView.findViewById(R.id.wv10);
						av = (AdView) rootView.findViewById(R.id.av10);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv10.loadUrl("file:///android_asset/www/frame10_hi.html");
    					else
    						wv10.loadUrl("file:///android_asset/www/frame10.html");
    					av.loadAd(new AdRequest());
    					return rootView;
					case 1:
						rootView = inflater.inflate(R.layout.frame11, container, false);
						WebView wv11 = (WebView) rootView.findViewById(R.id.wv11);
						av = (AdView) rootView.findViewById(R.id.av11);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv11.loadUrl("file:///android_asset/www/frame11_hi.html");
    					else
    						wv11.loadUrl("file:///android_asset/www/frame11.html");
    					av.loadAd(new AdRequest());
    					return rootView;
					case 2:
						rootView = inflater.inflate(R.layout.frame12, container, false);
						WebView wv12 = (WebView) rootView.findViewById(R.id.wv12);
						av = (AdView) rootView.findViewById(R.id.av12);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv12.loadUrl("file:///android_asset/www/frame12_hi.html");
    					else
    						wv12.loadUrl("file:///android_asset/www/frame12.html");
    					av.loadAd(new AdRequest());
    					return rootView;
					case 3:
						rootView = inflater.inflate(R.layout.frame13, container, false);
						WebView wv13 = (WebView) rootView.findViewById(R.id.wv13);
						av = (AdView) rootView.findViewById(R.id.av13);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv13.loadUrl("file:///android_asset/www/frame13_hi.html");
    					else
    						wv13.loadUrl("file:///android_asset/www/frame13.html");
    					av.loadAd(new AdRequest());
    					return rootView;
					case 4:
						rootView = inflater.inflate(R.layout.frame14, container, false);
						WebView wv14 = (WebView) rootView.findViewById(R.id.wv14);
						av = (AdView) rootView.findViewById(R.id.av14);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv14.loadUrl("file:///android_asset/www/frame14_hi.html");
    					else
    						wv14.loadUrl("file:///android_asset/www/frame14.html");
    					av.loadAd(new AdRequest());
    					return rootView;
					case 5:
						rootView = inflater.inflate(R.layout.frame15, container, false);
						WebView wv15 = (WebView) rootView.findViewById(R.id.wv15);
						av = (AdView) rootView.findViewById(R.id.av15);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv15.loadUrl("file:///android_asset/www/frame15_hi.html");
    					else
    						wv15.loadUrl("file:///android_asset/www/frame15.html");
    					av.loadAd(new AdRequest());
    					return rootView;
					case 6:
						rootView = inflater.inflate(R.layout.frame16, container, false);
						WebView wv16 = (WebView) rootView.findViewById(R.id.wv16);
						av = (AdView) rootView.findViewById(R.id.av16);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv16.loadUrl("file:///android_asset/www/frame16_hi.html");
    					else
    						wv16.loadUrl("file:///android_asset/www/frame16.html");
    					av.loadAd(new AdRequest());
    					return rootView;
					case 7:
						rootView = inflater.inflate(R.layout.frame17, container, false);
						WebView wv17 = (WebView) rootView.findViewById(R.id.wv17);
						av = (AdView) rootView.findViewById(R.id.av17);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv17.loadUrl("file:///android_asset/www/frame17_hi.html");
    					else
    						wv17.loadUrl("file:///android_asset/www/frame17.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				default:
    					return rootView;
					}
    			} else if (i == 2) {
    				rootView = null;
					av = null;
    				switch (position) {
    				case 0:
    					rootView = inflater.inflate(R.layout.frame20, container, false);
    					WebView wv20 = (WebView) rootView.findViewById(R.id.wv20);
    					av = (AdView) rootView.findViewById(R.id.av20);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv20.loadUrl("file:///android_asset/www/frame20_hi.html");
    					else
    						wv20.loadUrl("file:///android_asset/www/frame20.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 1:
    					rootView = inflater.inflate(R.layout.frame21, container, false);
    					WebView wv21 = (WebView) rootView.findViewById(R.id.wv21);
    					av = (AdView) rootView.findViewById(R.id.av21);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv21.loadUrl("file:///android_asset/www/frame21_hi.html");
    					else
    						wv21.loadUrl("file:///android_asset/www/frame21.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 2:
    					rootView = inflater.inflate(R.layout.frame22, container, false);
    					WebView wv22 = (WebView) rootView.findViewById(R.id.wv22);
    					av = (AdView) rootView.findViewById(R.id.av22);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv22.loadUrl("file:///android_asset/www/frame22_hi.html");
    					else
    						wv22.loadUrl("file:///android_asset/www/frame22.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 3:
    					rootView = inflater.inflate(R.layout.frame23, container, false);
    					WebView wv23 = (WebView) rootView.findViewById(R.id.wv23);
    					av = (AdView) rootView.findViewById(R.id.av23);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv23.loadUrl("file:///android_asset/www/frame23_hi.html");
    					else
    						wv23.loadUrl("file:///android_asset/www/frame23.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 4:
    					rootView = inflater.inflate(R.layout.frame24, container, false);
    					WebView wv24 = (WebView) rootView.findViewById(R.id.wv24);
    					av = (AdView) rootView.findViewById(R.id.av24);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv24.loadUrl("file:///android_asset/www/frame24_hi.html");
    					else
    						wv24.loadUrl("file:///android_asset/www/frame24.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 5:
    					rootView = inflater.inflate(R.layout.frame25, container, false);
    					WebView wv25 = (WebView) rootView.findViewById(R.id.wv25);
    					av = (AdView) rootView.findViewById(R.id.av25);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv25.loadUrl("file:///android_asset/www/frame25_hi.html");
    					else
    						wv25.loadUrl("file:///android_asset/www/frame25.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 6:
    					rootView = inflater.inflate(R.layout.frame26, container, false);
    					WebView wv26 = (WebView) rootView.findViewById(R.id.wv26);
    					av = (AdView) rootView.findViewById(R.id.av26);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv26.loadUrl("file:///android_asset/www/frame26_hi.html");
    					else
    						wv26.loadUrl("file:///android_asset/www/frame26.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 7:
    					rootView = inflater.inflate(R.layout.frame27, container, false);
    					WebView wv27 = (WebView) rootView.findViewById(R.id.wv27);
    					av = (AdView) rootView.findViewById(R.id.av27);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv27.loadUrl("file:///android_asset/www/frame27_hi.html");
    					else
    						wv27.loadUrl("file:///android_asset/www/frame27.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 8:
    					rootView = inflater.inflate(R.layout.frame28, container, false);
    					WebView wv28 = (WebView) rootView.findViewById(R.id.wv28);
    					av = (AdView) rootView.findViewById(R.id.av28);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv28.loadUrl("file:///android_asset/www/frame28_hi.html");
    					else
    						wv28.loadUrl("file:///android_asset/www/frame28.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 9:
    					rootView = inflater.inflate(R.layout.frame29, container, false);
    					WebView wv29 = (WebView) rootView.findViewById(R.id.wv29);
    					av = (AdView) rootView.findViewById(R.id.av29);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv29.loadUrl("file:///android_asset/www/frame29_hi.html");
    					else
    						wv29.loadUrl("file:///android_asset/www/frame29.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				default:
    					return rootView;
    				}
    			} else if (i == 3) {
    				rootView = null;
					av = null;
    				switch (position) {
    				case 0:
    					rootView = inflater.inflate(R.layout.frame30, container, false);
    					WebView wv30 = (WebView) rootView.findViewById(R.id.wv30);
    					av = (AdView) rootView.findViewById(R.id.av30);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv30.loadUrl("file:///android_asset/www/frame30_hi.html");
    					else
    						wv30.loadUrl("file:///android_asset/www/frame30.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 1:
    					rootView = inflater.inflate(R.layout.frame31, container, false);
    					WebView wv31 = (WebView) rootView.findViewById(R.id.wv31);
    					av = (AdView) rootView.findViewById(R.id.av31);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv31.loadUrl("file:///android_asset/www/frame31_hi.html");
    					else
    						wv31.loadUrl("file:///android_asset/www/frame31.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 2:
    					rootView = inflater.inflate(R.layout.frame32, container, false);
    					WebView wv32 = (WebView) rootView.findViewById(R.id.wv32);
    					av = (AdView) rootView.findViewById(R.id.av32);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv32.loadUrl("file:///android_asset/www/frame32_hi.html");
    					else
    						wv32.loadUrl("file:///android_asset/www/frame32.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 3:
    					rootView = inflater.inflate(R.layout.frame33, container, false);
    					WebView wv33 = (WebView) rootView.findViewById(R.id.wv33);
    					av = (AdView) rootView.findViewById(R.id.av33);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv33.loadUrl("file:///android_asset/www/frame33_hi.html");
    					else
    						wv33.loadUrl("file:///android_asset/www/frame33.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 4:
    					rootView = inflater.inflate(R.layout.frame34, container, false);
    					WebView wv34 = (WebView) rootView.findViewById(R.id.wv34);
    					av = (AdView) rootView.findViewById(R.id.av34);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv34.loadUrl("file:///android_asset/www/frame34_hi.html");
    					else
    						wv34.loadUrl("file:///android_asset/www/frame34.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				default:
    					return rootView;
    				}
    			} else if (i == 4) {
    				rootView = null;
					av = null;
    				switch (position) {
    				case 0:
    					rootView = inflater.inflate(R.layout.frame40, container, false);
    					WebView wv40 = (WebView) rootView.findViewById(R.id.wv40);
    					av = (AdView) rootView.findViewById(R.id.av40);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv40.loadUrl("file:///android_asset/www/frame40_hi.html");
    					else
    						wv40.loadUrl("file:///android_asset/www/frame40.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 1:
    					rootView = inflater.inflate(R.layout.frame41, container, false);
    					WebView wv41 = (WebView) rootView.findViewById(R.id.wv41);
    					av = (AdView) rootView.findViewById(R.id.av41);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv41.loadUrl("file:///android_asset/www/frame41_hi.html");
    					else
    						wv41.loadUrl("file:///android_asset/www/frame41.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 2:
    					rootView = inflater.inflate(R.layout.frame42, container, false);
    					WebView wv42 = (WebView) rootView.findViewById(R.id.wv42);
    					av = (AdView) rootView.findViewById(R.id.av42);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv42.loadUrl("file:///android_asset/www/frame42_hi.html");
    					else
    						wv42.loadUrl("file:///android_asset/www/frame42.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 3:
    					rootView = inflater.inflate(R.layout.frame43, container, false);
    					WebView wv43 = (WebView) rootView.findViewById(R.id.wv43);
    					av = (AdView) rootView.findViewById(R.id.av43);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv43.loadUrl("file:///android_asset/www/frame43_hi.html");
    					else
    						wv43.loadUrl("file:///android_asset/www/frame43.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 4:
    					rootView = inflater.inflate(R.layout.frame44, container, false);
    					WebView wv44 = (WebView) rootView.findViewById(R.id.wv44);
    					av = (AdView) rootView.findViewById(R.id.av44);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv44.loadUrl("file:///android_asset/www/frame44_hi.html");
    					else
    						wv44.loadUrl("file:///android_asset/www/frame44.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 5:
    					rootView = inflater.inflate(R.layout.frame45, container, false);
    					WebView wv45 = (WebView) rootView.findViewById(R.id.wv45);
    					av = (AdView) rootView.findViewById(R.id.av45);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv45.loadUrl("file:///android_asset/www/frame45_hi.html");
    					else
    						wv45.loadUrl("file:///android_asset/www/frame45.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 6:
    					rootView = inflater.inflate(R.layout.frame46, container, false);
    					WebView wv46 = (WebView) rootView.findViewById(R.id.wv46);
    					av = (AdView) rootView.findViewById(R.id.av46);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv46.loadUrl("file:///android_asset/www/frame46_hi.html");
    					else
    						wv46.loadUrl("file:///android_asset/www/frame46.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				default:
    					return rootView;
    				}
    			} else if (i == 5) {
    				rootView = null;
					av = null;
    				switch (position) {
    				case 0:
    					rootView = inflater.inflate(R.layout.frame50, container, false);
    					WebView wv50 = (WebView) rootView.findViewById(R.id.wv50);
    					av = (AdView) rootView.findViewById(R.id.av50);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv50.loadUrl("file:///android_asset/www/frame50_hi.html");
    					else
    						wv50.loadUrl("file:///android_asset/www/frame50.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 1:
    					rootView = inflater.inflate(R.layout.frame51, container, false);
    					WebView wv51 = (WebView) rootView.findViewById(R.id.wv51);
    					av = (AdView) rootView.findViewById(R.id.av51);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv51.loadUrl("file:///android_asset/www/frame51_hi.html");
    					else
    						wv51.loadUrl("file:///android_asset/www/frame51.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 2:
    					rootView = inflater.inflate(R.layout.frame52, container, false);
    					WebView wv52 = (WebView) rootView.findViewById(R.id.wv52);
    					av = (AdView) rootView.findViewById(R.id.av52);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv52.loadUrl("file:///android_asset/www/frame52_hi.html");
    					else
    						wv52.loadUrl("file:///android_asset/www/frame52.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 3:
    					rootView = inflater.inflate(R.layout.frame53, container, false);
    					WebView wv53 = (WebView) rootView.findViewById(R.id.wv53);
    					av = (AdView) rootView.findViewById(R.id.av53);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv53.loadUrl("file:///android_asset/www/frame53_hi.html");
    					else
    						wv53.loadUrl("file:///android_asset/www/frame53.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 4:
    					rootView = inflater.inflate(R.layout.frame54, container, false);
    					WebView wv54 = (WebView) rootView.findViewById(R.id.wv54);
    					av = (AdView) rootView.findViewById(R.id.av54);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv54.loadUrl("file:///android_asset/www/frame54_hi.html");
    					else
    						wv54.loadUrl("file:///android_asset/www/frame54.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 5:
    					rootView = inflater.inflate(R.layout.frame55, container, false);
    					WebView wv55 = (WebView) rootView.findViewById(R.id.wv55);
    					av = (AdView) rootView.findViewById(R.id.av55);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv55.loadUrl("file:///android_asset/www/frame55_hi.html");
    					else
    						wv55.loadUrl("file:///android_asset/www/frame55.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 6:
    					rootView = inflater.inflate(R.layout.frame56, container, false);
    					WebView wv56 = (WebView) rootView.findViewById(R.id.wv56);
    					av = (AdView) rootView.findViewById(R.id.av56);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv56.loadUrl("file:///android_asset/www/frame56_hi.html");
    					else
    						wv56.loadUrl("file:///android_asset/www/frame56.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 7:
    					rootView = inflater.inflate(R.layout.frame57, container, false);
    					WebView wv57 = (WebView) rootView.findViewById(R.id.wv57);
    					av = (AdView) rootView.findViewById(R.id.av57);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv57.loadUrl("file:///android_asset/www/frame57_hi.html");
    					else
    						wv57.loadUrl("file:///android_asset/www/frame57.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				case 8:
    					rootView = inflater.inflate(R.layout.frame58, container, false);
    					WebView wv58 = (WebView) rootView.findViewById(R.id.wv58);
    					av = (AdView) rootView.findViewById(R.id.av58);
    					if (l.toString().equalsIgnoreCase("hi_IN"))
    						wv58.loadUrl("file:///android_asset/www/frame58_hi.html");
    					else
    						wv58.loadUrl("file:///android_asset/www/frame58.html");
    					av.loadAd(new AdRequest());
    					return rootView;
    				default:
    					return rootView;
    				}
    			} else {
    				return rootView;
    			}
    		}
    	}
    }

}
