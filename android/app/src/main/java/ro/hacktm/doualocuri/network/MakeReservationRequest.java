package ro.hacktm.doualocuri.network;

import android.annotation.SuppressLint;

import com.android.volley.Request;
import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MakeReservationRequest extends RequestParams<Integer> {
	private final int numberOfPlaces;
	private final String name;
	private final int zoneId, pubId;
	private final Calendar time;

	public MakeReservationRequest(int numberOfPlaces, String name, int zoneId, int pubId, Calendar time) {
		this.numberOfPlaces = numberOfPlaces;
		this.name = name;
		this.zoneId = zoneId;
		this.pubId = pubId;
		this.time = time;
	}

	@SuppressLint("SimpleDateFormat")
	private final DateFormat mysqlTimestampFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");

	@Override
	public BaseRequest<Integer> buildRequest(String baseUrl, Response.Listener<Integer> responseListener, Response.ErrorListener errorListener) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", name);
		params.put("pub_id", pubId);
		params.put("zone_id", zoneId);
		params.put("number_of_places", numberOfPlaces);
		params.put("timestamp", mysqlTimestampFormat.format(time.getTime()));
		return new BaseRequest<Integer>(Request.Method.POST, baseUrl + "/reservation", params, responseListener, errorListener) {
			@Override
			public Integer parseResponse(JSONObject response) throws JSONException {
				return response.getInt("reservation_id");
			}
		};
	}

	@Override
	public String getKey() {
		return "reservation_" + pubId + "_" + zoneId + "_" + name + "_" + numberOfPlaces + "_" + time.getTime().getTime();
	}
}
