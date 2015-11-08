package ro.hacktm.doualocuri.network;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RequestResolver {
	private final Map<String, RunningRequest> runningRequests = new HashMap<>();
	private final Context context;
	private final String baseUrl;

	public RequestResolver(Context context, String baseUrl) {
		this.context = context;
		this.baseUrl = baseUrl;
	}

	private static RequestResolver instance = null;

	public synchronized static RequestResolver getInstance(Context context) {
		if (instance == null) {
			instance = new RequestResolver(context, "http://192.168.0.102:8080/api");
		}

		return instance;
	}

	@SuppressWarnings("unchecked")
	public <T> void makeRequest(final RequestParams<T> params, Response.Listener<T> responeListener, Response.ErrorListener errorListener) {
		try {
			RunningRequest<T> request = runningRequests.get(params.getKey());
			if (request == null) {
				ProxyListener<T> listener = new ProxyListener<>(params.getKey());
				request = new RunningRequest<>(params.buildRequest(baseUrl, listener, listener));
				runningRequests.put(params.getKey(), request);
				Network.getInstance(context).addToQueue(request.request);
			}

			request.addListeners(responeListener, errorListener);
		} catch (ClassCastException e) {
			e.printStackTrace();
			runningRequests.remove(params.getKey());
		}
	}

	private class ProxyListener<T> implements Response.Listener<T>, Response.ErrorListener {
		private final String key;

		private ProxyListener(String key) {
			this.key = key;
		}

		@Override
		public void onErrorResponse(VolleyError error) {
			RunningRequest request = runningRequests.get(key);
			if (request != null) {
				request.onError(error);
				runningRequests.remove(key);
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public void onResponse(T response) {
			RunningRequest<T> request = runningRequests.get(key);
			if (request != null) {
				request.onResponse(response);
				runningRequests.remove(key);
			}
		}
	}

	private static class RunningRequest<T> {
		public final BaseRequest<T> request;
		private final ArrayList<WeakReference<Response.Listener<T>>> responseListeners = new ArrayList<>();
		private final ArrayList<WeakReference<Response.ErrorListener>> errorListeners = new ArrayList<>();

		public RunningRequest(BaseRequest<T> request) {
			this.request = request;
		}

		public void addListeners(Response.Listener<T> responseListener, Response.ErrorListener errorListener) {
			responseListeners.add(new WeakReference<>(responseListener));
			errorListeners.add(new WeakReference<>(errorListener));
		}

		public void onResponse(T response) {
			for (WeakReference<Response.Listener<T>> listenerWeakReference : responseListeners) {
				Response.Listener<T> listener = listenerWeakReference != null ? listenerWeakReference.get() : null;
				if (listener != null) {
					listener.onResponse(response);
				}
			}
		}

		public void onError(VolleyError error) {
			for (WeakReference<Response.ErrorListener> listenerWeakReference : errorListeners) {
				Response.ErrorListener listener = listenerWeakReference != null ? listenerWeakReference.get() : null;
				if (listener != null) {
					listener.onErrorResponse(error);
				}
			}
		}
	}
}
