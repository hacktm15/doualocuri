package ro.hacktm.doualocuri.network;

import com.android.volley.Response;

import org.json.JSONException;

import java.util.ArrayList;

import ro.hacktm.doualocuri.Pub;

public class PubListRequest extends RequestParams<ArrayList<Pub>> {


	@Override
	public BaseRequest<ArrayList<Pub>> buildRequest(Response.Listener<ArrayList<Pub>> responseListener, Response.ErrorListener errorListener) {
		return new BaseRequest<ArrayList<Pub>>("", responseListener, errorListener) {
			@Override
			public ArrayList<Pub> parseResponse(String response) throws JSONException {
				return null;
			}
		};
	}

	@Override
	public String getKey() {
		return "pubs";
	}
}
