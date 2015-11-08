package ro.hacktm.doualocuri;

import android.os.Parcel;
import android.os.Parcelable;

public class Zone implements Parcelable {
	private final int id;
	private final String name;
	private final int freeChairs, totalChairs;

	public Zone(int id, String name, int freeChairs, int totalChairs) {
		this.id = id;
		this.name = name;
		this.freeChairs = freeChairs;
		this.totalChairs = totalChairs;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getFreeChairs() {
		return freeChairs;
	}

	public int getTotalChairs() {
		return totalChairs;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.name);
		dest.writeInt(this.freeChairs);
		dest.writeInt(this.totalChairs);
	}

	protected Zone(Parcel in) {
		this.id = in.readInt();
		this.name = in.readString();
		this.freeChairs = in.readInt();
		this.totalChairs = in.readInt();
	}

	public static final Creator<Zone> CREATOR = new Creator<Zone>() {
		public Zone createFromParcel(Parcel source) {
			return new Zone(source);
		}

		public Zone[] newArray(int size) {
			return new Zone[size];
		}
	};

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Zone)) return false;

		Zone zone = (Zone) o;

		return id == zone.id;

	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public String toString() {
		return name;

	}
}
