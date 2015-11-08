package ro.hacktm.doualocuri;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class PubActivity extends AppCompatActivity {
	public static final String KEY_PUB_ID = "pub_id";

	public static void start(@NonNull Context sourceContext, int pubId) {
		Intent intent = new Intent(sourceContext, PubActivity.class);
		intent.putExtra(KEY_PUB_ID, pubId);
		sourceContext.startActivity(intent);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pub);
		Intent intent = getIntent();

		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}

		if (savedInstanceState == null && intent != null) {
			int pubId = intent.getIntExtra(KEY_PUB_ID, -1);
			PubFragment fragment = PubFragment.createFragment(pubId);

			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
			fragmentManager.executePendingTransactions();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				NavUtils.navigateUpFromSameTask(this);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
