package ro.hacktm.doualocuri.network;

import com.android.volley.Response;

public abstract class RequestParams<T> {
	public abstract BaseRequest<T> buildRequest(Response.Listener<T> responseListener, Response.ErrorListener errorListener);

	public abstract String getKey();
}
