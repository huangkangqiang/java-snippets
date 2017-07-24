## util.Date转成sql.Date

```java
java.util.Date utilDate = new java.util.Date();  
java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
```