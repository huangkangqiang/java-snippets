## 使用NIO进行快速的文件拷贝

```java
package com.kq;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 使用NIO进行快速的文件拷贝
 * 
 * @author huang
 *
 */
public class CopyFileByNIO {

	public static void main(String[] args) throws IOException {

		CopyFileByNIO copyFileByNIO = new CopyFileByNIO();

		String srcFileName = "src/com/kq/src.txt";
		String desFileName = "src/com/kq/des.txt";

		copyFileByNIO.copyFile(srcFileName, desFileName);
	}

	private void copyFile(String srcFileName, String desFileName) throws IOException {

		FileChannel srcChannel = new FileInputStream(srcFileName).getChannel();
		FileChannel desChannel = new FileOutputStream(desFileName).getChannel();

		try {

			int maxCount = (64 * 1024 * 1024) - (32 * 1024);
			long size = srcChannel.size();
			long position = 0;

			while (position < size) {
				position += srcChannel.transferTo(position, maxCount, desChannel);
			}

		} finally {
			if (null != srcChannel) {
				srcChannel.close();
			}
			if (null != desChannel) {
				desChannel.close();
			}
		}
	}

}
```