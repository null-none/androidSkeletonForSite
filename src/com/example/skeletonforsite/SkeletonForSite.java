package com.example.skeletonforsite;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class SkeletonForSite extends Activity {

	private WebView page;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skeleton_for_site);

		page = (WebView) findViewById(R.id.wv);

		WebSettings webSettings = page.getSettings();
		webSettings.setSaveFormData(true);
		webSettings.setJavaScriptEnabled(true);

		page.setWebViewClient(new WebViewClient() {
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				Toast.makeText(getApplicationContext(),
						"Error: " + description + " " + failingUrl,
						Toast.LENGTH_LONG).show();
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (url.indexOf("about") <= 0) {
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri
							.parse(url));
					startActivity(intent);
					return true;
				}
				return false;
			}
		});

		page.loadUrl("http://about.me/kal1sha");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && page.canGoBack()) {
			page.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.skeleton_for_site, menu);
		return true;
	}

}

