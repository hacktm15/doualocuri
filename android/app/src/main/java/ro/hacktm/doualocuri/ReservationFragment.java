package ro.hacktm.doualocuri;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import ro.hacktm.doualocuri.network.MakeReservationRequest;
import ro.hacktm.doualocuri.network.RequestResolver;

public class ReservationFragment extends Fragment {
	public static final String KEY_PUB = "pub";

	public static ReservationFragment createFragment(@NonNull PubDetails pub) {
		ReservationFragment fragment = new ReservationFragment();
		Bundle args = new Bundle();
		args.putParcelable(KEY_PUB, pub);
		fragment.setArguments(args);
		return fragment;
	}

	private PubDetails pub;
	private Calendar reservationTime;
	private TextView reservationTimeDisplay;
	private Spinner zoneSpinner;
	private EditText reservationCount, name;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pub = getArguments().getParcelable(KEY_PUB);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_reservation, container, false);
	}

	private void updateReservationTime(Calendar reservationTime) {
		this.reservationTime = reservationTime;
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault());
		DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault());
		reservationTimeDisplay.setText(String.format("%s ora %s", dateFormat.format(reservationTime.getTime()), timeFormat.format(reservationTime.getTime())));
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		reservationTimeDisplay = (TextView) view.findViewById(R.id.reservationDateTime);
		zoneSpinner = (Spinner) view.findViewById(R.id.reservationZone);
		zoneSpinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, pub.getZones()));

		reservationTime = Calendar.getInstance();
		reservationTime.add(Calendar.MINUTE, 45);
		reservationTimeDisplay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Calendar now = Calendar.getInstance();
				DatePickerDialog datePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
						TimePickerDialog timePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
							@Override
							public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
								Calendar newTime = Calendar.getInstance();
								newTime.set(year, monthOfYear, dayOfMonth, hourOfDay, minute);
								updateReservationTime(newTime);
							}
						}, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), true);
						timePicker.show();
					}
				}, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
				datePicker.getDatePicker().setMinDate(now.getTimeInMillis());
				datePicker.show();
			}
		});

		int maxPeople = 0;
		for (Zone zone : pub.getZones()) {
			maxPeople += zone.getFreeChairs();
		}

		reservationCount = (EditText) view.findViewById(R.id.reservationCount);
		name = (EditText) view.findViewById(R.id.nameEditText);
		final int finalMaxPeople = maxPeople;
		reservationCount.addTextChangedListener(new TextWatcher() {
			private String previousContent = "1";

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				previousContent = s.toString();
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				try {
					int value = Integer.parseInt(s.toString());
					if (value < 1 || value > finalMaxPeople) {
						s.replace(0, s.length(), previousContent);
					}
				} catch (NumberFormatException e) {
					s.replace(0, s.length(), previousContent);
				}
			}
		});
		View.OnClickListener changeCountListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					int oldCount = Integer.parseInt(reservationCount.getText().toString());
					int delta = v.getId() == R.id.addPeopleButton ? 1 : (v.getId() == R.id.removePeopleButton ? -1 : 0);
					reservationCount.setText(String.format("%d", oldCount + delta));
				} catch (NumberFormatException ignored) {
				}
			}
		};
		view.findViewById(R.id.addPeopleButton).setOnClickListener(changeCountListener);
		view.findViewById(R.id.removePeopleButton).setOnClickListener(changeCountListener);

		view.findViewById(R.id.submitReservation).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int numberOfPlaces = Integer.parseInt(reservationCount.getText().toString());
				String name = ReservationFragment.this.name.getText().toString();
				int zoneId = ((Zone) zoneSpinner.getSelectedItem()).getId();
				RequestResolver.getInstance(getContext()).makeRequest(new MakeReservationRequest(numberOfPlaces, name, zoneId, pub.getId(), reservationTime),
						new Response.Listener<Integer>() {
							@Override
							public void onResponse(Integer response) {
								if (isAdded()) {
									Toast.makeText(getContext(), "Your reservation ID is " + response, Toast.LENGTH_LONG).show();
								}
							}
						},
						new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								error.printStackTrace();
							}
						}
				);
			}
		});
	}
}
