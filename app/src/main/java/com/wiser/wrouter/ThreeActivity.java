package com.wiser.wrouter;

import com.wiser.router.WRouter;
import com.wiser.router_annotation.Router;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

@Router(path = "app/ThreeActivity")
public class ThreeActivity extends FragmentActivity {

	@Override protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.cl_parent).setBackgroundColor(Color.RED);

		Toast.makeText(this, (getIntent() != null && getIntent().getExtras() != null) ? getIntent().getStringExtra("key") : "", Toast.LENGTH_SHORT).show();

		MainProvider mainProvider = (MainProvider) WRouter.create("app/MainProvider").buildProvider();
		if (mainProvider != null) mainProvider.hello("你好啊");

		findViewById(R.id.cl_parent).setOnClickListener(new View.OnClickListener() {

			@Override public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
			}
		});
	}
}
