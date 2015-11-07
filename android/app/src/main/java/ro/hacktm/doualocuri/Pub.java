package ro.hacktm.doualocuri;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class Pub implements Parcelable {
	private final int id;
	private final String name, description;
	private final LatLng latLng;

	public Pub(int id, String name, String description, double lat, double lng) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.latLng = new LatLng(lat, lng);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
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
		dest.writeParcelable(this.latLng, 0);
	}

	protected Pub(Parcel in) {
		this.id = in.readInt();
		this.name = in.readString();
		this.description = in.readString();
		this.latLng = in.readParcelable(LatLng.class.getClassLoader());
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
