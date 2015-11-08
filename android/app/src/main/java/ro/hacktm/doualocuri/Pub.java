package ro.hacktm.doualocuri;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class Pub implements Parcelable {
	private final int id;
	private final String name, description, phone;
	private final LatLng latLng;
	private final String bannerUrl;

	public Pub(int id, String name, String description, double lat, double lng, String phone, String bannerUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.phone = phone;
		this.bannerUrl = bannerUrl;
		this.latLng = new LatLng(lat, lng);
	}

	public int getId() {
		return id;
	}

	public String getPhone() {
		return phone;
	}

	public String getName() {
		return name;
	}

	public String getBannerUrl() {
		return bannerUrl;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return name + "-" + id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Pub)) return false;

		Pub pub = (Pub) o;

		return id == pub.id;

	}

	@Override
	public int hashCode() {
		return id;
	}

	public LatLng getLatLng() {
		return latLng;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.name);
		dest.writeString(this.description);
		dest.writeString(this.phone);
		dest.writeParcelable(this.latLng, 0);
		dest.writeString(this.bannerUrl);
	}

	protected Pub(Parcel in) {
		this.id = in.readInt();
		this.name = in.readString();
		this.description = in.readString();
		this.phone = in.readString();
		this.latLng = in.readParcelable(LatLng.class.getClassLoader());
		this.bannerUrl = in.readString();
	}

	public static final Creator<Pub> CREATOR = new Creator<Pub>() {
		public Pub createFromParcel(Parcel source) {
			return new Pub(source);
		}

		public Pub[] newArray(int size) {
			return new Pub[size];
		}
	};
}
