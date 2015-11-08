package ro.hacktm.doualocuri;

import android.os.Parcel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PubDetails extends Pub {
	private final ArrayList<Zone> zones;

	public PubDetails(int id, String name, String description, double lat, double lng, String bannerUrl, String phone, Collection<? extends Zone> zones) {
		super(id, name, description, lat, lng, phone, bannerUrl);
		this.zones = new ArrayList<>(zones);
	}

	public List<Zone> getZones() {
		return Collections.unmodifiableList(zones);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeTypedList(zones);
	}

	protected PubDetails(Parcel in) {
		super(in);
		this.zones = in.createTypedArrayList(Zone.CREATOR);
	}

	public static final Creator<PubDetails> CREATOR = new Creator<PubDetails>() {
		public PubDetails createFromParcel(Parcel source) {
			return new PubDetails(source);
		}

		public PubDetails[] newArray(int size) {
			return new PubDetails[size];
		}
	};
}
