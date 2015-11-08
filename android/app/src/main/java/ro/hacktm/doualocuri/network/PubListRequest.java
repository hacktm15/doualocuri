package ro.hacktm.doualocuri.network;

import com.android.volley.Request;
import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ro.hacktm.doualocuri.Pub;

public class PubListRequest extends RequestParams<ArrayList<Pub>> {

	public static Pub parsePub(JSONObject pub) throws JSONException {
		int id = pub.getInt("id");
		String name = pub.getString("name");
		String description = pub.getString("description");
		double lat = pub.getDouble("latitute");
		double lng = pub.getDouble("longitute");
		String bannerUrl = pub.getString("banner_url");
		String phone = pub.getString("phone");
		return new Pub(id, name, description, lat, lng, phone, bannerUrl);
	}

	@Override
	public BaseRequest<ArrayList<Pub>> buildRequest(String baseUrl, Response.Listener<ArrayList<Pub>> responseListener, Response.ErrorListener errorListener) {
		return new BaseRequest<ArrayList<Pub>>(Request.Method.GET, baseUrl + "/pubs", responseListener, errorListener) {
			@Override
			public ArrayList<Pub> parseResponse(JSONObject response) throws JSONException {
				JSONArray result = response.getJSONArray("result");
				ArrayList<Pub> pubs = new ArrayList<>(result.length());
				for (int i = 0; i < result.length(); ++i) {
					pubs.add(parsePub(result.getJSONObject(i)));
				}

				return pubs;
			}
		};
	}

	@Override
	public String getKey() {
		return "pubs";
	}
}
