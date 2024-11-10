# 神行短链

---

保姆级教程,5分钟打造属于自己的短链
免费短链平台
---

### 免费使用
短链生成
免费使用
### 地址: short.godsdo.com 
[short.godsdo.com ](http://short.godsdo.com)

<img width="406" alt="image" src="https://github.com/user-attachments/assets/f6ca722f-6e1c-4192-af8d-a1bc4158a15b">


<img width="763" alt="image" src="https://github.com/user-attachments/assets/0f449222-e804-4aa5-a494-1334b970d2eb">

打包命令
```angular2html
sbt clean && sbt package
```
```
docker run -d \
--name shotlink \
-p 8080:8080 \
just.do.it:5000/short
```
api 接口
```yaml
http://short-api.godsdo.com:8080/api/short/getLinks?s_url=http://www.baidu.com
```
