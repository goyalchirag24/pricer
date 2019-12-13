package com.prizy.pricer.util;

import org.json.JSONObject;

public class StringUtil {

	public static boolean isNullObject(Object s) {
		if (s == null) {
			return true;
		}
		return false;
	}

	public static boolean isNullJSONObject(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof JSONObject && ((JSONObject) obj).length() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isNullOrEmpty(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		return false;
	}
}
