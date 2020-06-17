package com.wiser.router;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import com.wiser.router_annotation.IRouterConstant;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import dalvik.system.DexFile;

/**
 * @author Wiser
 *
 *         路由跳转
 */
public class WRouter {

	private HashMap<String, Class<? extends Activity>> activates;

	private HashMap<String, Class<? extends IProvider>> providers;

	private WRouter() {
		activates = new HashMap<>();
		providers = new HashMap<>();
	}

	private static class WRouterHolder {

		private static final WRouter router = new WRouter();
	}

	public static WRouter getInstance() {
		return WRouterHolder.router;
	}

	/**
	 * 初始化
	 * 
	 * @param application
	 */
	public static void init(Application application) {
 		List<String> classNames = getClassNames(application);
		for (String className : classNames) {
			try {
				Class<?> aClass = Class.forName(className);
				if (IRouterActivity.class.isAssignableFrom(aClass)) {
					IRouterActivity iRouterActivity = (IRouterActivity) aClass.newInstance();
					iRouterActivity.injectActivity();
				}
				if (IRouterProvider.class.isAssignableFrom(aClass)) {
					IRouterProvider iRouterProvider = (IRouterProvider) aClass.newInstance();
					iRouterProvider.injectProvider();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取包名下所有的class文件
	 * 
	 * @param context
	 * @return
	 */
	private static List<String> getClassNames(Context context) {
		List<String> classNames = new ArrayList<>();
		String path;
		try {
			// 通过包管理器 获取到应用信息类然后获取到apk的完整路径
			path = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir;
			// 根据apk的完整路径获取编译后的dex文件
			DexFile dexFile = new DexFile(path);
			// 获取编译后的dex文件中所有的class
			Enumeration enumeration = dexFile.entries();
			// 然后进行遍历
			while (enumeration.hasMoreElements()) {
				// 通过遍历所有的class的包名
				String name = (String) enumeration.nextElement();
				// 判断类的包名是否含有传入的包名
				if (name.contains(IRouterConstant.PACKAGE_NAME)) {
					// 添加集合中
					classNames.add(name);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classNames;
	}

	HashMap<String, Class<? extends Activity>> getActivates() {
		return activates;
	}

	public HashMap<String, Class<? extends IProvider>> getProviders() {
		return providers;
	}

	/**
	 * 注入Activity
	 * 
	 * @param path
	 * @param clazz
	 */
	public void injectActivity(String path, Class<? extends Activity> clazz) {
		if (TextUtils.isEmpty(path) || clazz == null) return;
		if (activates != null) activates.put(path, clazz);
	}

	/**
	 * 注入Provider
	 *
	 * @param path
	 * @param clazz
	 */
	public void injectProvider(String path, Class<? extends IProvider> clazz) {
		if (TextUtils.isEmpty(path) || clazz == null) return;
		if (providers != null) providers.put(path, clazz);
	}

	public static IRouterDisplay create(String path) {
		return new RouterDisplay(path);
	}

}
