package com.shihua.collections;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Map<String, String> map = new HashMap<String, String>();
		map.put("name", "xijinping");
		map.remove("name");*/
		
		Map<TestKey, String> map2 = new HashMap<TestKey, String>();
		TestKey k1 = new TestKey("sh",28);
		TestKey k2 = new TestKey("sh2",28);
		TestKey k22 = new TestKey("sh3",28);
		TestKey k23 = new TestKey("sh4",28);
		
		TestKey k3 = new TestKey("yht",30);
		TestKey k4 = new TestKey("qqb",31);

		map2.put(k1, "v1");
		map2.put(k2, "v2");
		map2.put(k22, "v22");
		map2.put(k23, "v23");
//		map2.put(k3, "v3");
//		map2.put(k4, "v4");
		
	}

}
