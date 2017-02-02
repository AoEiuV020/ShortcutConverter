#百度手机输入法的自定义短语转成UserDictionaryPlus可以导入的格式
自用小工具，  
安卓上的自定义短语不支持批量导入，  
发现app UserDictionaryPlus有备份恢复自定义短语的功能，  
转换后用UserDictionaryPlus导入就可以在谷歌输入法之类的地方用了，  

百度的自定义短语放在res/cphrase.ini,这个文件名是百度默认的，  
输出的文件在res/User Dictionary Plus Backup.txt,  
传到手机/sdcard/UserDictionaryPlus/User Dictionary Plus Backup.txt再用UserDictionaryPlus导入就好了，

```sh
./gradlew run
adb push res/User Dictionary Plus Backup.txt /sdcard/UserDictionaryPlus/
```
