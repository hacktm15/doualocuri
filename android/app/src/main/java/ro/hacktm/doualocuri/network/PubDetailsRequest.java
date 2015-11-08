package ro.hacktm.doualocuri.network;

import com.android.volley.Request;
import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ro.hacktm.doualocuri.Pub;
import ro.hacktm.doualocuri.PubDetails;
import ro.hacktm.doualocuri.Zone;

public class PubDetailsRequest extends RequestParams<PubDetails> {
	private final int pubId;

	public PubDetailsRequest(int pubId) {
		this.pubId = pubId;
	}

	@Override
	public BaseRequest<PubDetails> buildRequest(String baseUrl, Response.Listener<PubDetails> responseListener, Response.ErrorListener errorListener) {
		return new BaseRequest<PubDetails>(Request.Method.GET, baseUrl + "/pubs/" + pubId, responseListener, errorListener) {
			@Override
			public PubDetails parseResponse(JSONObject response) throws JSONException {
				JSONObject result = response.getJSONObject("result");
				JSONObject pubInfo = result.getJSONObject("pub_info");
				pubInfo.put("id", pubId);
				Pub pub = PubListRequest.parsePub(pubInfo);

				JSONArray zonesJSON = result.getJSONArray("zones");
				ArrayList<Zone> zones = new ArrayList<>(zonesJSON.length());
				for (int i = 0; i < zonesJSON.length(); ++i) {
					JSONObject zone = zonesJSON.getJSONObject(i);
					int id = zone.getInt("id");
					String name = zone.getString("name");
					int totalChairs = zone.getInt("total_chairs");
					int freeChairs = zone.getInt("free_chairs");
					zones.add(new Zone(id, name, freeChairs, totalChairs));
				}

				return new PubDetails(pub.getId(), pub.getName(), pub.getDescription(), pub.getLatLng().latitude, pub.getLatLng().longitude, pub.getBannerUrl(), zones);
			}
		};
	}

	@Override
	public String getKey() {
		return "pub_" + pubId;
	}
}
