## 把Array转换成Map

```java
package com.kq;

import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

public class ArrayConvertMap {

	public static void main(String[] args) {

		String[][] contries={{"China","Beijing"},{"United States","New York"}};
		
		Map<Object, Object> countryCapitals=ArrayUtils.toMap(contries);
		
		System.out.println("Capital of China is "+countryCapitals.get("China"));
		System.out.println("Capital of United States is "+countryCapitals.get("United States"));
		
	}

}
```