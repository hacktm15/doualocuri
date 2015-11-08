package ro.hacktm.doualocuri;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import ro.hacktm.doualocuri.network.PubDetailsRequest;
import ro.hacktm.doualocuri.network.RequestResolver;

public class PubFragment extends Fragment {
	public static final String KEY_PUB_ID = "pub_id";

	public static PubFragment createFragment(int pubId) {
		PubFragment fragment = new PubFragment();
		Bundle args = new Bundle();
		args.putInt(KEY_PUB_ID, pubId);
		fragment.setArguments(args);
		return fragment;
	}

	@Nullable
	private PubDetails pub;
	private int pubId;

	private TextView pubName, pubDescription, freeCapacity, phoneNumber;
	private ImageView pubBanner;
	private ViewGroup buttonBar;
	private ViewSwitcher viewSwitcher, loadingErrorViewSwitcher;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pubId = getArguments().getInt(KEY_PUB_ID);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_pub, container, false);
	}

	@Override
	public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		pubName = (TextView) view.findViewById(R.id.pubName);
		pubDescription = (TextView) view.findViewById(R.id.pubDescription);
		freeCapacity = (TextView) view.findViewById(R.id.availableSeats);
		pubBanner = (ImageView) view.findViewById(R.id.pubBanner);
		buttonBar = (ViewGroup) view.findViewById(R.id.buttonBar);
		viewSwitcher = (ViewSwitcher) view.findViewById(R.id.loadingPubViewSwitcher);
		loadingErrorViewSwitcher = (ViewSwitcher) view.findViewById(R.id.loadingErrorViewSwitcher);
		phoneNumber = (TextView) view.findViewById(R.id.phoneNumber);

		viewSwitcher.post(new Runnable() {
			@Override
			public void run() {
				RequestResolver.getInstance(getContext()).makeRequest(new PubDetailsRequest(pubId),
						new Response.Listener<PubDetails>() {
							@Override
							public void onResponse(PubDetails response) {
								if (pub == null) {
									onPubDetailsLoaded(response);
								}
							}
						},
						new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								loadingErrorViewSwitcher.showNext();
							}
						}
				);
			}
		});

		view.findViewById(R.id.makeReservationButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (pub != null) {
					final FragmentManager fragmentManager = getChildFragmentManager();
					final Fragment fragment = fragmentManager.findFragmentByTag("reservation");
					if (fragment == null) {
						fragmentManager.beginTransaction()
								.replace(R.id.reservationFragmentContainer, ReservationFragment.createFragment(pub), "reservation")
								.addToBackStack("reservation")
								.commit();
						final FrameLayout reservationFragmentContainer = (FrameLayout) view.findViewById(R.id.reservationFragmentContainer);
						Animation bottomUp = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_up);
						reservationFragmentContainer.startAnimation(bottomUp);
						Animation bottomUp2 = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_up);
						buttonBar.startAnimation(bottomUp2);
					} else {
						final FrameLayout reservationFragmentContainer = (FrameLayout) view.findViewById(R.id.reservationFragmentContainer);
						Animation bottomDown = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_down);
						reservationFragmentContainer.startAnimation(bottomDown);
						Animation bottomDown2 = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_down);
						buttonBar.startAnimation(bottomDown2);
						bottomDown.setAnimationListener(new Animation.AnimationListener() {
							@Override
							public void onAnimationStart(Animation animation) {

							}

							@Override
							public void onAnimationEnd(Animation animation) {
								fragmentManager.beginTransaction().remove(fragment).commit();
							}

							@Override
							public void onAnimationRepeat(Animation animation) {

							}
						});
					}
				}
			}
		});

		if (pub != null) {
			onPubDetailsLoaded(pub);
		}
	}

	private void onPubDetailsLoaded(PubDetails pub) {
		if (getView() != null && pub != null) {
			pubName.setText(pub.getName());
			pubDescription.setText(pub.getDescription());
			Picasso.with(getContext()).load(pub.getBannerUrl()).noPlaceholder().noFade().into(pubBanner);

			int free = 0;
			for (Zone zone : pub.getZones()) {
				free += zone.getFreeChairs();
			}
			freeCapacity.setText(String.format("%d", free));
			viewSwitcher.showNext();
			for (int i = 0; i < buttonBar.getChildCount(); ++i) {
				buttonBar.getChildAt(i).setEnabled(true);
			}
			phoneNumber.setText(pub.getPhone());
		}
		this.pub = pub;
	}
}
