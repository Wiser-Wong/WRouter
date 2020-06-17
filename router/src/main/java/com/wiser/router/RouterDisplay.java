package com.wiser.router;

import java.io.Serializable;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * @author Wiser
 * 
 *         路由管理
 */
public class RouterDisplay implements IRouterDisplay {

	// 路径
	private String	path;

	private Intent	intent	= new Intent();

	private Bundle	bundle;

	private boolean	isClose;

	RouterDisplay(String path) {
		this.path = path;
	}

	@Override public void open(@NonNull Activity activity) {
		if (TextUtils.isEmpty(path)) return;
		Class<? extends Activity> aClass = WRouter.getInstance().getActivates().get(path);
		if (aClass == null) return;
		if (intent == null) intent = new Intent().setClass(activity, aClass);
		else intent.setClass(activity, aClass);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		activity.startActivity(intent);
		if (isClose) activity.finish();
	}

	@Override public void open(@NonNull Activity activity, int requestCode) {
		if (TextUtils.isEmpty(path)) return;
		Class<? extends Activity> aClass = WRouter.getInstance().getActivates().get(path);
		if (aClass == null) return;
		if (intent == null) intent = new Intent().setClass(activity, aClass);
		else intent.setClass(activity, aClass);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		activity.startActivityForResult(intent, requestCode);
	}

	@Override public RouterDisplay withClose(boolean value) {
		this.isClose = value;
		return this;
	}

	@Override public RouterDisplay withIntent(@NonNull Intent intent) {
		this.intent = intent;
		return this;
	}

	@Override public RouterDisplay withBundle(@NonNull Bundle bundle) {
		this.bundle = bundle;
		return this;
	}

	@Override public RouterDisplay withBundle(@NonNull String key, @NonNull Bundle value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withString(@NonNull String key, @NonNull String value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withStringArray(@NonNull String key, @NonNull String[] value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withStringArrayList(@NonNull String key, @NonNull ArrayList<String> value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withInt(@NonNull String key, int value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withIntArray(@NonNull String key, @NonNull int[] value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withIntegerArrayList(@NonNull String key, @NonNull ArrayList<Integer> value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withBoolean(@NonNull String key, boolean value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withBooleanArray(@NonNull String key, @NonNull boolean[] value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withFloat(@NonNull String key, float value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withFloatArray(@NonNull String key, @NonNull float[] value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withShort(@NonNull String key, short value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withShortArray(@NonNull String key, @NonNull short[] value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withLong(@NonNull String key, long value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withLongArray(@NonNull String key, @NonNull long[] value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withDouble(@NonNull String key, double value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withDoubleArray(@NonNull String key, @NonNull double[] value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withByte(@NonNull String key, byte value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withByteArray(@NonNull String key, @NonNull byte[] value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withChar(@NonNull String key, char value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withCharArray(@NonNull String key, @NonNull char[] value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withCharSequence(@NonNull String key, @NonNull CharSequence value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withCharSequenceArray(@NonNull String key, @NonNull CharSequence[] value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withCharSequenceArrayList(@NonNull String key, @NonNull ArrayList<CharSequence> value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withSerializable(@NonNull String key, @NonNull Serializable value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withParcelable(@NonNull String key, @NonNull Parcelable value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withParcelableArray(@NonNull String key, @NonNull Parcelable[] value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override public RouterDisplay withParcelableArrayList(@NonNull String key, @NonNull ArrayList<? extends Parcelable> value) {
		if (this.intent != null) this.intent.putExtra(key, value);
		return this;
	}

	@Override
	public Object buildProvider() {
		if (TextUtils.isEmpty(path)) return null;
		Class<? extends IProvider> aClass = WRouter.getInstance().getProviders().get(path);
		try {
			assert aClass != null;
			return aClass.newInstance();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return null;
	}
}
