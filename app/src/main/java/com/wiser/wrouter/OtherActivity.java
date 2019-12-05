package com.wiser.wrouter;

import com.wiser.router.WRouter;
import com.wiser.routeranno.Router;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

@Router("app:OtherActivity")
public class OtherActivity extends FragmentActivity {

	@Override protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.cl_parent).setBackgroundColor(Color.YELLOW);

		Toast.makeText(this,(getIntent() != null && getIntent().getExtras() != null) ? getIntent().getStringExtra("key") : "",Toast.LENGTH_SHORT).show();

		findViewById(R.id.cl_parent).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
			}
		});
	}
}
