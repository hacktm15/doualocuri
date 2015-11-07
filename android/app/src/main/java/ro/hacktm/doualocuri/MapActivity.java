package ro.hacktm.doualocuri;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import ro.hacktm.doualocuri.network.PubListRequest;
import ro.hacktm.doualocuri.network.RequestResolver;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
	public static final String KEY_PUBS = "pubs";
	public static final String KEY_SELECTED_PUB = "selectedPub";
	public static final String KEY_CAMERA_POSITION = "cameraPosition";
	public static final String KEY_LAST_REFRESH_REQUEST_MILLIS = "lastRefreshRequestMillis";
	public static final String KEY_LAST_SUCCESSFUL_REFRESH_MILLIS = "lastSuccessfulRefresh";
	public static final String KEY_LAST_REFRESH_SUCCESS = "lastRefreshSuccess";
	private GoogleMap mMap = null; // Might be null if Google Play services APK is not available.
	private CameraPosition mSavedPosition = null;
	private ArrayList<Pub> mPubs = null;
	private Map<Marker, Pub> mMarkers = new HashMap<>();
	private Pub mSelectedPub;

	private ImageView mSuccessIndicator, mFailureIndicator;
	private ProgressBar mProgressBar;
	private TextView mLastRefreshTime;

	private long lastRefreshRequestMillis = 0;
	private Calendar lastSuccessfulRefresh = null;
	private Boolean lastRefreshSuccess = null;

	public static final long refreshIntervalMillis = 60000;

	private final Handler mainThread = new Handler(Looper.getMainLooper());
	private final Runnable refreshPubs = new Runnable() {
		@Override
		public void run() {
			RequestResolver.getInstance(MapActivity.this).makeRequest(new PubListRequest(),
					new Response.Listener<ArrayList<Pub>>() {
						@Override
						public void onResponse(ArrayList<Pub> response) {
							onPubsLoaded(response);
						}
					},
					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							Log.e("Pubs", "" + (error != null ? error.getMessage() : "unknown error"));
							setRefreshStatus(lastRefreshSuccess = false);
						}
					}
			);

			Log.i("Refresh", "Refreshed pubs, scheduling next refresh after " + refreshIntervalMillis / 1000 + " seconds.");
			lastRefreshRequestMillis = SystemClock.elapsedRealtime();
			mainThread.postDelayed(this, refreshIntervalMillis);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		mSuccessIndicator = (ImageView) findViewById(R.id.successIndicator);
		mFailureIndicator = (ImageView) findViewById(R.id.failureIndicator);
		mProgressBar = (ProgressBar) findViewById(R.id.refreshProgress);
		mLastRefreshTime = (TextView) findViewById(R.id.lastRefreshTime);

		ImageButton refreshButton = (ImageButton) findViewById(R.id.refreshButton);
		refreshButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.i("Refresh", "Refresh requested, cancelling pending refresh & refreshing now.");
				mainThread.removeCallbacks(refreshPubs);
				mainThread.post(refreshPubs);
			}
		});

		if (savedInstanceState != null) {
			lastRefreshRequestMillis = savedInstanceState.getLong(KEY_LAST_REFRESH_REQUEST_MILLIS);
			lastSuccessfulRefresh = Calendar.getInstance(Locale.getDefault());
			lastSuccessfulRefresh.setTimeInMillis(savedInstanceState.getLong(KEY_LAST_SUCCESSFUL_REFRESH_MILLIS));
			if (savedInstanceState.containsKey(KEY_LAST_REFRESH_SUCCESS)) {
				lastRefreshSuccess = savedInstanceState.getBoolean(KEY_LAST_REFRESH_SUCCESS);
			} else {
				lastRefreshSuccess = null;
			}
		}

		setRefreshStatus(lastRefreshSuccess);
		if (lastSuccessfulRefresh != null) {
			mLastRefreshTime.setText(getString(R.string.last_refresh, lastSuccessfulRefresh));
		}
	}

	public void setRefreshStatus(Boolean success) {
		mSuccessIndicator.setVisibility(success != null && success ? View.VISIBLE : View.GONE);
		mFailureIndicator.setVisibility(success != null && !success ? View.VISIBLE : View.GONE);
		mProgressBar.setVisibility(success == null ? View.VISIBLE : View.GONE);
	}

	@Override
	public void onMapReady(GoogleMap map) {
		if (mMap == null && map != null) {
			LatLng timisoara = new LatLng(45.753010, 21.227179);

			map.setMyLocationEnabled(true);
			map.setBuildingsEnabled(false);
			map.setIndoorEnabled(false);
			UiSettings mapUi = map.getUiSettings();
			mapUi.setRotateGesturesEnabled(false);
			mapUi.setMapToolbarEnabled(true);

			if (mSavedPosition == null) {
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(timisoara, 13));
			} else {
				map.moveCamera(CameraUpdateFactory.newCameraPosition(mSavedPosition));
			}

			if (mPubs != null) {
				addPubsToMap(map, mPubs, mSavedPosition == null);
			}

			map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
				@Override
				public boolean onMarkerClick(Marker marker) {
					mSelectedPub = mMarkers.get(marker);
					return false;
				}
			});
			map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
				@Override
				public void onMapClick(LatLng latLng) {
					mSelectedPub = null;
				}
			});
			mMap = map;
		}
	}


	public void onPubsLoaded(@NonNull ArrayList<Pub> pubs) {
		lastSuccessfulRefresh = Calendar.getInstance(Locale.getDefault());
		setRefreshStatus(lastRefreshSuccess = true);
		mLastRefreshTime.setText(getString(R.string.last_refresh, lastSuccessfulRefresh));
		if (mMap != null) {
			addPubsToMap(mMap, pubs, mPubs == null);
		}
		mPubs = pubs;
	}


	public void addPubsToMap(@NonNull GoogleMap map, @NonNull Collection<Pub> pubs, boolean zoomToFit) {
		map.clear();
		mMarkers.clear();
		LatLngBounds.Builder pubBounds = new LatLngBounds.Builder();
		for (Pub pub : pubs) {
			Marker marker = map.addMarker(new MarkerOptions()
					.title(pub.getName())
					.snippet(pub.getDescription())
					.position(pub.getLatLng()));

			pubBounds.include(pub.getLatLng());
			mMarkers.put(marker, pub);
			if (mSelectedPub != null && pub.equals(mSelectedPub)) {
				marker.showInfoWindow();
			}
		}

		if (zoomToFit) {
			map.moveCamera(CameraUpdateFactory.newLatLngBounds(pubBounds.build(), 40));
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelableArrayList(KEY_PUBS, mPubs);
		outState.putParcelable(KEY_SELECTED_PUB, mSelectedPub);
		if (mMap != null) {
			outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
		}
		outState.putLong(KEY_LAST_REFRESH_REQUEST_MILLIS, lastRefreshRequestMillis);
		if (lastSuccessfulRefresh != null) {
			outState.putLong(KEY_LAST_SUCCESSFUL_REFRESH_MILLIS, lastSuccessfulRefresh.getTimeInMillis());
		}
		if (lastRefreshSuccess != null) {
			outState.putBoolean(KEY_LAST_REFRESH_SUCCESS, lastRefreshSuccess);
		}
	}

	@Override
	protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mPubs = savedInstanceState.getParcelableArrayList(KEY_PUBS);
		mSelectedPub = savedInstanceState.getParcelable(KEY_SELECTED_PUB);
		mSavedPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mainThread.removeCallbacks(refreshPubs);
		Log.i("Refresh", "Activity paused, cancelling pending refresh.");
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mMap == null) {
			SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
			if (mapFragment != null) {
				mapFragment.getMapAsync(this);
			}
		}

		if (mPubs == null) {
			Log.i("Refresh", "Getting pub status for the first time.");
			mainThread.post(refreshPubs);
		} else {
			long delayMillis = Math.max(lastRefreshRequestMillis + refreshIntervalMillis - SystemClock.elapsedRealtime(), 0);
			Log.i("Refresh", "Activity resumed, scheduling refresh after " + delayMillis / 1000 + " seconds.");
			mainThread.postDelayed(refreshPubs, delayMillis);
		}
	}

/*	public boolean showInfoDialog() {
		// DialogFragment.show() will take care of adding the fragment
		// in a transaction.  We also want to remove any currently showing
		// dialog, so make our own transaction and take care of that here.
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		Fragment prev = getSupportFragmentManager().findFragmentByTag("infoDialog");
		if (prev != null) {
			transaction.remove(prev);
		}
		transaction.addToBackStack(null);

		// Create and show the dialog.
		InfoDialog dialog = InfoDialog.newInstance(mPubs);
		dialog.show(transaction, "infoDialog");
		return true;
	}*/
}
