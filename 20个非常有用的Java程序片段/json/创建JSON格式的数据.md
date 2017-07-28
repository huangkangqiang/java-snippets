## 创建JSON格式的数据

```java
package json;

import com.alibaba.fastjson.JSONObject;

/**
 * 创建JSON格式的数据
 * 
 * @author huang
 *
 */
public class JSONDemo {

	public static void main(String[] args) {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("city", "beijing");
		jsonObject.put("name", "zhangsan");

		System.out.println(jsonObject.toJSONString());
	}

}
```
