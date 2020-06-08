package com.wiser.router;

import java.util.concurrent.ConcurrentHashMap;

public class RouterModule {

    private ConcurrentHashMap<String,String> modules;

    private RouterModule(){
        modules = new ConcurrentHashMap<>();
    }

    private final static class RouterModuleHolder{
        private final static RouterModule instance = new RouterModule();
    }

    public static RouterModule getInstance(){
        return RouterModuleHolder.instance;
    }

    public void putModuleName(String key,String name){
        if (modules != null) modules.put(key,name);
    }

    public ConcurrentHashMap<String, String> getModules() {
        return modules;
    }
}
