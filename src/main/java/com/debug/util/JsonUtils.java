package com.debug.util;

import com.debug.json.FastjsonAdapter;
import com.debug.json.GsonAdapter;
import com.debug.json.HuToolJsonAdapter;
import com.debug.json.JacksonAdapter;
import com.debug.json.JsonAdapter;
import com.debug.json.TypeReference;
import java.lang.reflect.Type;
import lombok.Getter;
import lombok.SneakyThrows;

/**
 * @author lingting 2021/2/26 9:58
 */
public class JsonUtils {

	@Getter
	private static JsonAdapter adapter;

	static {
		ClassLoader classLoader = JsonUtils.class.getClassLoader();
		if (ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", classLoader)) {
			adapter = new JacksonAdapter();
		} else if (ClassUtils.isPresent("com.google.gson.Gson", classLoader)) {
			adapter = new GsonAdapter();
		} else if (ClassUtils.isPresent("cn.hutool.json.JSONConfig", classLoader)) {
			adapter = new HuToolJsonAdapter();
		} else if (ClassUtils.isPresent("com.alibaba.fastjson.JSON", classLoader)) {
			adapter = new FastjsonAdapter();
		}
	}

	/**
	 * 切换适配器. 请注意 本方法全局生效
	 *
	 * @author lingting 2021-02-26 11:18
	 */
	public static void switchAdapter(JsonAdapter adapter) {
		JsonUtils.adapter = adapter;
	}

	@SneakyThrows
	public static String toJson(Object obj) {
		return adapter.toJson(obj);
	}

	@SneakyThrows
	public static <T> T toObj(String json, Class<T> r) {
		return adapter.toObj(json, r);
	}

	@SneakyThrows
	public static <T> T toObj(String json, Type t) {
		return adapter.toObj(json, t);
	}

	@SneakyThrows
	public static <T> T toObj(String json, TypeReference<T> t) {
		return adapter.toObj(json, t);
	}

}
