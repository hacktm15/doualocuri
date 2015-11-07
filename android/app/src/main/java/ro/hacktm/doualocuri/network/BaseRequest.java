package ro.hacktm.doualocuri.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;

/**
 * Base request for API calls.
 *
 * @param <T>
 */
public abstract class BaseRequest<T> extends StringRequest {
	private static class ProxyListener<T> implements Response.Listener<String>, Response.ErrorListener {
		private BaseRequest<T> mRequestObject;
		private final Response.Listener<T> mResponseListener;
		private final Response.ErrorListener mErrorListener;

		public ProxyListener(Response.Listener<T> stationListener, Response.ErrorListener errorListener) {
			if (stationListener == null) {
				throw new IllegalArgumentException("Response listener cannot be null.");
			}
			mResponseListener = stationListener;
			mErrorListener = errorListener;
		}

		@Override
		public void onResponse(String response) {
			T parsedResponse = null;
			try {
				parsedResponse = mRequestObject.parseResponse(response);
			} catch (JSONException e) {
				Log.e(mRequestObject.getClass().getSimpleName(), "" + e.getMessage());
				if (mErrorListener != null) {
					mErrorListener.onErrorResponse(new VolleyError(response, e));
				}
			}

			if (parsedResponse != null) {
				mResponseListener.onResponse(parsedResponse);
			}
		}

		public void setRequestObject(BaseRequest<T> request) {
			mRequestObject = request;
		}

		@Override
		public void onErrorResponse(VolleyError error) {
			if (mErrorListener != null) {
				mErrorListener.onErrorResponse(error);
			}
		}
	}

	/**
	 * Create a new request to the given endpoint and with the given parameters.
	 *
	 * @param url           request endpoint
	 * @param listener      Listener to receive the {@link T} response
	 * @param errorListener Error listener, or null to ignore errors
	 */
	public BaseRequest(String url, @NonNull Response.Listener<T> listener, @Nullable Response.ErrorListener errorListener) {
		this(url, new ProxyListener<>(listener, errorListener));
	}

	private BaseRequest(String url, ProxyListener<T> proxyListener) {
		super(Method.POST, url, proxyListener, proxyListener);
		proxyListener.setRequestObject(this);
	}

	public abstract T parseResponse(String response) throws JSONException;
}
