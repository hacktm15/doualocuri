package ro.hacktm.doualocuri.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Network {
	/**
	 * An {@link com.android.volley.toolbox.HttpStack HttpStack} implementation to use OkHttp as
	 * the transport layer for Volley.
	 */
	private static class OkHttpStack extends HurlStack {
		private final OkUrlFactory okUrlFactory;

		public OkHttpStack() {
			this(new OkUrlFactory(new OkHttpClient()));
		}

		public OkHttpStack(OkUrlFactory okUrlFactory) {
			if (okUrlFactory == null) {
				throw new NullPointerException("Client must not be null.");
			}
			this.okUrlFactory = okUrlFactory;
		}

		@Override
		protected HttpURLConnection createConnection(URL url) throws IOException {
			return okUrlFactory.open(url);
		}
	}

	private final RequestQueue mRequestQueue;

	private Network(Context context) {
		mRequestQueue = Volley.newRequestQueue(context.getApplicationContext(), new OkHttpStack());
	}

	/**
	 * Get a reference to the main {@link com.android.volley.RequestQueue RequestQueue}.
	 *
	 * @return the application request queue
	 */
	public RequestQueue getRequestQueue() {
		return mRequestQueue;
	}

	/**
	 * Add <code>request</code> to the main {@link com.android.volley.RequestQueue RequestQueue}.
	 *
	 * @param request the request to add
	 */
	public <T> void addToQueue(Request<T> request) {
		mRequestQueue.add(request);
	}


	private static Network instance;

	public static synchronized Network getInstance(Context context) {
		if (instance == null) {
			instance = new Network(context.getApplicationContext());
		}

		return instance;
	}
}
