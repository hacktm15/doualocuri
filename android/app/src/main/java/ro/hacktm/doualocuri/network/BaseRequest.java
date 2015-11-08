package ro.hacktm.doualocuri.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Base request for API calls.
 *
 * @param <T>
 */
public abstract class BaseRequest<T> extends StringRequest {
	@SuppressWarnings("unused")
	public static class RequestFailedError extends VolleyError {
		public RequestFailedError(NetworkResponse response) {
			super(response);
		}

		public RequestFailedError(String exceptionMessage) {
			super(exceptionMessage);
		}

		public RequestFailedError(String exceptionMessage, Throwable reason) {
			super(exceptionMessage, reason);
		}

		public RequestFailedError(Throwable cause) {
			super(cause);
		}
	}

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
			Log.v(getClass().getSimpleName(), response);
			try {
				JSONObject responseJSON = new JSONObject(response);
				boolean success = responseJSON.getBoolean("success");
				if (!success) {
					throw new RequestFailedError(responseJSON.getString("error"));
				}
				parsedResponse = mRequestObject.parseResponse(responseJSON);
			} catch (JSONException e) {
				Log.e(mRequestObject.getClass().getSimpleName(), "Failed to parse response JSON: " + e.getMessage());
				e.printStackTrace();
				if (mErrorListener != null) {
					mErrorListener.onErrorResponse(new VolleyError(response, e));
				}
			} catch (RequestFailedError e) {
				Log.e(mRequestObject.getClass().getSimpleName(), "Request failed with reason" + e.getMessage());
				if (mErrorListener != null) {
					mErrorListener.onErrorResponse(e);
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

	private final Map<String, ?> params;

	/**
	 * Create a new request to the given endpoint and with the given parameters.
	 *
	 * @param url           request endpoint
	 * @param listener      Listener to receive the {@link T} response
	 * @param errorListener Error listener, or null to ignore errors
	 */
	public BaseRequest(int method, String url, @NonNull Response.Listener<T> listener, @Nullable Response.ErrorListener errorListener) {
		this(method, url, new HashMap<String, Object>(), new ProxyListener<>(listener, errorListener));
	}

	/**
	 * Create a new request to the given endpoint and with the given parameters.
	 *
	 * @param url           request endpoint
	 * @param listener      Listener to receive the {@link T} response
	 * @param errorListener Error listener, or null to ignore errors
	 */
	public BaseRequest(int method, String url, Map<String, ?> params, @NonNull Response.Listener<T> listener, @Nullable Response.ErrorListener errorListener) {
		this(method, url, params, new ProxyListener<>(listener, errorListener));
	}

	private BaseRequest(int method, String url, Map<String, ?> params, ProxyListener<T> proxyListener) {
		super(method, url, proxyListener, proxyListener);
		this.params = new HashMap<>(params);
		proxyListener.setRequestObject(this);
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		Map<String, String> stringParams = new HashMap<>();
		for (Map.Entry<String, ?> entry : params.entrySet()) {
			stringParams.put(entry.getKey(), entry.getValue().toString());
		}
		return stringParams;
	}

	@Override
	public String getBodyContentType() {
		return "application/json";
	}

	@Override
	public byte[] getBody() throws AuthFailureError {
		try {
			return new JSONObject(params).toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			return new byte[0];
		}
	}

	public abstract T parseResponse(JSONObject response) throws JSONException;
}
