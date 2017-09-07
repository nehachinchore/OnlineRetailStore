package com.retailstore.common;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonUtils {

	private static final Logger log = Logger.getLogger((JsonUtils.class).getPackage().getName());

	public static String getErrorJson(String message) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("warning", message);
		return new Gson().toJson(jsonObject);
	}

	public static String getSuccessJson(String message) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("success", message);
		return new Gson().toJson(jsonObject);
	}

	public static String getJson(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Hibernate4Module().disable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION))
				.setSerializationInclusion(Include.NON_NULL);
		objectMapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false);
		objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
		String json = null;
		try {
			json = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.log(Level.WARNING, "", log);
		}
		return json;
	}

	public static <T> T fromJSON(final TypeReference<T> type, final String jsonPacket) {
		T data = null;

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			objectMapper.setDateFormat(formatter);
			data = objectMapper.readValue(jsonPacket, type);
		} catch (Exception e) {
			log.log(Level.WARNING, "", log);
		}
		return data;
	}
}
