::切换到D盘
D:

::基础路径
set hour=%time:~0,2%
if %hour% leq 9 (set hour=0%hour:~1,1%)
set basePath=D:\test\%date:~0,4%%date:~5,2%%date:~8,2%\%hour%%time:~3,2%%time:~6,2%

::需要打包的文件夹路径
set SEQPath=%basePath%\SEQ.FBB_WEBUI

::前端包路径
set frontPath=D:\test\Front\WEBUI

::中间件包路径
set backPath=D:\test\Back\SEQ.FBB_WEBUI\app

::jar包路径
set jarPath=D:\test\archive-0.0.1-SNAPSHOT.jar

::创建gz包最终生成路径
md %SEQPath%

::复制中间件代码
xcopy %backPath% %SEQPath% /E

::pages路径
set pagesPath=%SEQPath%\WEB\WEB-INF\pages

::创建pages文件夹
md %pagesPath%

::删除中间件代码不需要的文件和文件夹
::删除web.xml
del %SEQPath%\WEB\WEB-INF\web.xml
::将web.xml.local改为web.xml
ren %SEQPath%\WEB\WEB-INF\web.xml.local web.xml

::复制前端代码
xcopy %frontPath% %pagesPath% /E

::删除前端不需要的文件和文件夹
::删除mock文件夹
rmdir /s /q %pagesPath%\libs\mock

::调用程序打成tar.gz包
java -jar %jarPath% %SEQPath%

::打开.tar.gz包所在目录
start %basePath%

pause