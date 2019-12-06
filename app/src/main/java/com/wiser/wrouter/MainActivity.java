package com.wiser.wrouter;

import com.wiser.router.WRouter;
import com.wiser.router_annotation.Router;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

@Router("app:MainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.cl_parent).setBackgroundColor(Color.BLUE);

        findViewById(R.id.cl_parent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key","路由跳转");
                WRouter.create("app:OtherActivity").withBundle(bundle).withClose(true).open(MainActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111){
            Toast.makeText(this,"回来了",Toast.LENGTH_SHORT).show();
        }
    }
}
