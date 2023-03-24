package com.example.hong.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;

/**
 * 用来将某个POJO映射成JSON的工具类，拷贝自piaoyitong包下
 * 如果有特殊映射，互不影响
 */
@Slf4j
public class JsonUtils {

	private static final TobInvoiceObjectMapper objectMapper = new TobInvoiceObjectMapper();

	private static class TobInvoiceObjectMapper extends ObjectMapper {

		TobInvoiceObjectMapper() {
			super();
			this.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			// 只序列化非空的字段
			this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			// 支持单引号作为key
			this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
			// 忽略不认识的属性名
			this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			// 将驼峰式命名改为带下划线的
			// this.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

			this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			// 数字也加引号
			// this.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
		}
	}

	public static String writeValueAsString(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			log.error("Failed at parsing object to string.", e);
			throw new RuntimeException("解析JSON出错");
		}
	}

	public static Map<String, String> readAsMap(String json) {
		JsonNode jsonNode;
		try {
			jsonNode = objectMapper.readTree(json);
		} catch (IOException e) {
			log.error("Failed at parsing json string: {}", json);
			throw new RuntimeException("解析JSON出错");
		}
		if(jsonNode.size() <= 0) {
			return Collections.emptyMap();
		}
		HashMap<String, String> result = new HashMap<>();
		Iterator<String> iterator = jsonNode.fieldNames();
		String fieldName;
		while(iterator.hasNext()) {
			fieldName = iterator.next();
			result.put(fieldName, jsonNode.get(fieldName).textValue());
		}
		return result;
	}

	public static <T> T readValue(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			log.error("Failed at parsing json string to object.", e);
			throw new RuntimeException("解析JSON出错");
		}
	}

	public static JsonNode readAsJson(String content){
		try {
			 return objectMapper.readTree(content);
		} catch (IOException e) {
			log.error("Failed at parsing json string: {}", content);
			throw new RuntimeException("解析JSON出错");
		}
	}

	public static ObjectNode createJSON(){
		return objectMapper.createObjectNode();
	}

	public static ArrayNode createArray(){
		return objectMapper.createArrayNode();
	}
}
