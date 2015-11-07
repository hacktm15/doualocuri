package ro.hacktm.doualocuri;

import android.os.Parcel;
import android.os.Parcelable;

public class Pub implements Parcelable {
	private final int id;
	private final String name, description;
	private final double lat, lng;


	public Pub(int id, String name, String description, double lat, double lng) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.lat = lat;
		this.lng = lng;
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

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
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
		dest.writeDouble(this.lat);
		dest.writeDouble(this.lng);
	}

	protected Pub(Parcel in) {
		this.id = in.readInt();
		this.name = in.readString();
		this.description = in.readString();
		this.lat = in.readDouble();
		this.lng = in.readDouble();
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
