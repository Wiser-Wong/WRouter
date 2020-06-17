package com.wiser.router;

import java.io.Serializable;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * @author Wiser
 * 
 *         管理路由方法接口
 */
public interface IRouterDisplay {

	/**
	 * 打开Activity
	 * 
	 * @param activity
	 */
	void open(@NonNull Activity activity);

	/**
	 * 打开ActivityForResult
	 * 
	 * @param activity
	 * @param requestCode
	 */
	void open(@NonNull Activity activity, int requestCode);

	/**
	 * 关闭当前界面
	 * 
	 * @param value
	 * @return
	 */
	RouterDisplay withClose(boolean value);

	/**
	 * 传递Intent
	 * 
	 * @param intent
	 * @return
	 */
	RouterDisplay withIntent(@NonNull Intent intent);

	/**
	 * 传递Bundle
	 *
	 * @param bundle
	 * @return
	 */
	RouterDisplay withBundle(@NonNull Bundle bundle);

	/**
	 * 传递Bundle
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withBundle(@NonNull String key, @NonNull Bundle value);

	/**
	 * 传递String
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withString(@NonNull String key, @NonNull String value);

	/**
	 * 传递String数组
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withStringArray(@NonNull String key, @NonNull String[] value);

	/**
	 * 传递String集合
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withStringArrayList(@NonNull String key, @NonNull ArrayList<String> value);

	/**
	 * 传递int
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withInt(@NonNull String key, int value);

	/**
	 * 传递int数组
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withIntArray(@NonNull String key, @NonNull int[] value);

	/**
	 * 传递Integer集合
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withIntegerArrayList(@NonNull String key, @NonNull ArrayList<Integer> value);

	/**
	 * 传递Boolean
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withBoolean(@NonNull String key, boolean value);

	/**
	 * 传递Boolean数组
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withBooleanArray(@NonNull String key, @NonNull boolean[] value);

	/**
	 * 传递float
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withFloat(@NonNull String key, float value);

	/**
	 * 传递float数组
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withFloatArray(@NonNull String key, @NonNull float[] value);

	/**
	 * 传递short
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withShort(@NonNull String key, short value);

	/**
	 * 传递short数组
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withShortArray(@NonNull String key, @NonNull short[] value);

	/**
	 * 传递long
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withLong(@NonNull String key, long value);

	/**
	 * 传递long数组
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withLongArray(@NonNull String key, @NonNull long[] value);

	/**
	 * 传递double
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withDouble(@NonNull String key, double value);

	/**
	 * 传递double数组
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withDoubleArray(@NonNull String key, @NonNull double[] value);

	/**
	 * 传递byte
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withByte(@NonNull String key, byte value);

	/**
	 * 传递byte数组
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withByteArray(@NonNull String key, @NonNull byte[] value);

	/**
	 * 传递char
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withChar(@NonNull String key, char value);

	/**
	 * 传递char数组
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withCharArray(@NonNull String key, @NonNull char[] value);

	/**
	 * 传递CharSequence
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withCharSequence(@NonNull String key, @NonNull CharSequence value);

	/**
	 * 传递CharSequence数组
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withCharSequenceArray(@NonNull String key, @NonNull CharSequence[] value);

	/**
	 * 传递CharSequence集合
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withCharSequenceArrayList(@NonNull String key, @NonNull ArrayList<CharSequence> value);

	/**
	 * 传递Serializable
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withSerializable(@NonNull String key, @NonNull Serializable value);

	/**
	 * 传递Parcelable
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withParcelable(@NonNull String key, @NonNull Parcelable value);

	/**
	 * 传递Parcelable数组
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withParcelableArray(@NonNull String key, @NonNull Parcelable[] value);

	/**
	 * 传递Parcelable集合
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	RouterDisplay withParcelableArrayList(@NonNull String key, @NonNull ArrayList<? extends Parcelable> value);

	/**
	 * 查询Provider
	 * @return
	 */
	Object buildProvider();
}
