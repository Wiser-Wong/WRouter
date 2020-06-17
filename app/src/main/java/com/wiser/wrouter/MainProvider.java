package com.wiser.wrouter;

import android.content.Context;

import com.wiser.router_annotation.Router;

/**
 * @author Wiser
 */
@Router(provider = "app/MainProvider")
public class MainProvider implements IMainProvider {

    @Override
    public void init(Context context) {

    }

    @Override
    public void hello(String hello) {
        System.out.println("------------->>" + hello);
    }
}
