## 使用JDBC连接MySQL

[学习资料](http://blog.csdn.net/zhanghong0603/article/details/52621304)

### 加载你JDBC驱动程序

连接数据库之前，首先要加载想要连接的数据库驱动到JVM。

通过java.lang.Class类的静态方法`forName(String className)`可以实现。

```java
Class.forName(className);
```

成功加载后，会将Driver类的实例注册到DriverManager类中。

### 提供JDBC连接的URL

连接URL定义了连接数据库时的协议、子协议、数据源标识。

+ 协议：在JDBC中总是以jdbc开始
+ 子协议：是连接的驱动程序或是数据库管理系统的名称。
+ 数据源标识：标记找到数据库来源的地址与连接端口。

>jdbc:mysql://localhost:3306/test?useSSL=false

### 创建数据库的连接

要连接数据库，需要向java.sql.DriverManager请求并获得Connection对象，该对象就代表一个数据库的连接。

使用DriverManager的getConnection(String url,String username,String password)方法传入指定的要连接的数据库的路径、数据库的用户名和密码来获得。

```java
String url = properties.getProperty("db.url");
String user = properties.getProperty("db.username");
String password = properties.getProperty("db.password");
String className = properties.getProperty("db.driverClass");
```

### 创建一个Statement

要执行sql语句，必须获得java.sql.Statement实例，Statement实例分为以下三种类型：

+ 执行静态sql语句，通常通过Statement实例实现
+ 执行动态sql语句，通常通过PreparedStatement实例实现。
+ 执行数据库存储过程，通常通过CallableStratement实例实现。

```java
PreparedStatement preparedStatement = connection.prepareStatement("select * from user");
```

### 执行sql语句

Statement接口提供了三种执行sql语句的方法：

+ Results executeQuery(String sqlString)：执行查询数据库的sql语句，返回一个结果集对象。
+ int executeUpdate(String sqlString)：用于执行INSERT、UPDATE或DELETE语句以及SQL DDL语句，如：CREATE TABLE和DROP TABLE等。
+ execute(String sqlString)：用于执行返回多个结果集、多个更新技术或二者组合的语句。

```java
ResultSet resultSet = preparedStatement.executeQuery();
```

### 处理结果

+ 执行更新返回的是本次操作影响到的记录数
+ 执行查询返回的结果时一个ResultSet对象。
  + ResultSet包含符合sql语句中条件的所有行，并且通过一套get方法提供了对这些行中数据的访问。
  + 使用结果集对象的访问方法获取数据。

```java
while (resultSet.next()) {
	System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
}
```

### 关闭JDBC对象

操作完成后要把所有使用的JDBC对象全都关闭，以释放JDBC资源，关闭顺序和声明顺序相反：

+ 关闭记录集
+ 关闭声明
+ 关闭连接对象

```java
resultSet.close();
preparedStatement.close();
connection.close();
```

```java
package com.kq;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 使用JDBC连接MySQL
 * 
 * @author huang
 *
 */
public class JDBCConnectMySQL {

	private Connection connection;

	public static void main(String[] args)
			throws ClassNotFoundException, FileNotFoundException, IOException, SQLException {

		JDBCConnectMySQL jdbcConnectMySQL = new JDBCConnectMySQL();
		jdbcConnectMySQL.init(new FileInputStream("src/com/kq/config.properties"));
		jdbcConnectMySQL.fetch();
	}

	public void init(FileInputStream fs) throws IOException, ClassNotFoundException, SQLException {

		Properties properties = new Properties();
		properties.load(fs);

		String url = properties.getProperty("db.url");
		String user = properties.getProperty("db.username");
		String password = properties.getProperty("db.password");
		String className = properties.getProperty("db.driverClass");

		Class.forName(className);

		connection = DriverManager.getConnection(url, user, password);
	}

	public void fetch() throws SQLException {

		PreparedStatement preparedStatement = connection.prepareStatement("select * from user");
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
		}

		resultSet.close();
		preparedStatement.close();
		connection.close();
	}

}
```
