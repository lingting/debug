package com.debug;

import com.debug.json.FastjsonAdapter;
import com.debug.json.GsonAdapter;
import com.debug.json.HuToolJsonAdapter;
import com.debug.json.JacksonAdapter;
import com.debug.json.TypeReference;
import com.debug.util.JsonUtils;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * @author lingting 2021/2/26 11:03
 */
public class JsonTest {

	@Test
	public void jackson() {
		JsonUtils.switchAdapter(new JacksonAdapter());
		test();
	}

	@Test
	public void gson() {
		JsonUtils.switchAdapter(new GsonAdapter());
		test();
	}

	@Test
	public void huTool() {
		JsonUtils.switchAdapter(new HuToolJsonAdapter());
		test();
	}

	@Test
	public void fastjson() {
		JsonUtils.switchAdapter(new FastjsonAdapter());
		test();
	}

	public void test() {
		System.out.println(JsonUtils.getAdapter().getClass());
		System.out.println("==========================");
		// json 字段多余实体类字段
		String t1 = "{\n"
				+ "\t\"str\": \"string\",\n"
				+ "\t\"i\": 233,\n"
				+ "\t\"b\": true,\n"
				+ "\t\"t1\": \"t\"\n"
				+ "}";
		System.out.println(JsonUtils.toObj(t1, Target.class));
		Target target = JsonUtils.toObj(t1, (Type) Target.class);
		System.out.println(target);
		target = JsonUtils.toObj(t1, new TypeReference<Target>() {
		});
		System.out.println(target);

		// json 字段少于实体类字段
		System.out.println("==========================");
		t1 = "{\n"
				+ "\t\"str\": \"string\",\n"
				+ "\t\"i\": 233\n"
				+ "}";

		System.out.println(JsonUtils.toObj(t1, Target.class));
		target = JsonUtils.toObj(t1, (Type) Target.class);
		System.out.println(target);
		target = JsonUtils.toObj(t1, new TypeReference<Target>() {
		});
		System.out.println(target);

		// 转map
		System.out.println("==========================");
		System.out.println(JsonUtils.toObj(t1, Map.class));
		Map map = JsonUtils.toObj(t1, (Type) Map.class);
		System.out.println(map);
		map = JsonUtils.toObj(t1, new TypeReference<Map>() {
		});
		System.out.println(map);

		t1 = "{\n"
				+ "    \"t1\": {\n"
				+ "        \"str\": \"t1\",\n"
				+ "        \"i\": 1\n"
				+ "    "
				+ "},\n"
				+ "    \"t2\": {\n"
				+ "        \"str\": \"t2\",\n"
				+ "        \"i\": 2\n"
				+ "    "
				+ "}\n"
				+
				"}";
		map = JsonUtils.toObj(t1, new TypeReference<Map<String, Target>>() {
		});
		System.out.println(map);

		// 转list
		System.out.println("==========================");
		//language=JSON
		t1 = "[\n"
				+ "    \"1\", 2, 4\n"
				+
				"]";

		System.out.println(JsonUtils.toObj(t1, List.class));
		List list = JsonUtils.toObj(t1, (Type)List.class);
		System.out.println(list);
		list = JsonUtils.toObj(t1, new TypeReference<List>() {
		});
		System.out.println(list);

		t1 = "[\n"
				+ "    {\n"
				+ "        \"str\": \"1\",\n"
				+ "        \"b\": true\n"
				+ "    },\n"
				+ "    {\n"
				+ "        \"str\": \"2\",\n"
				+ "        \"b\": false\n"
				+ "    "
				+ "}\n"
				+
				"]";

		list = JsonUtils.toObj(t1, new TypeReference<List<Target>>() {
		});
		System.out.println(list);
	}

}
