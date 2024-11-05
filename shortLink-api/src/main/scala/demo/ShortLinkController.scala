package demo

import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.{CrossOrigin, RequestBody, RequestMapping, RestController}

import scala.collection.JavaConverters._
import scala.collection.JavaConversions.asScalaBuffer
import scala.util.Random
//import scala.jdk.CollectionConverters._ // 导入 Scala 的转换方法
/**
 * @Author : just do it
 * @Date : 2024/11/5 11:55
 * @ApiNote : 
 */
@RestController
@RequestMapping(Array("/short"))
@CrossOrigin
class ShortLinkController {
  @Autowired
  var redisTemplate: RedisTemplate[String, String] = _

  @Value("${domain.list}")
  var domains: String= _

  //@Autowired
  //var domainProperties: DomainProperties = _


  @RequestMapping(Array("/getLink"))
  def getLink(s_url:String): String = {
    val str = ToBase62.encodeToBase62(IdUtil.nextId())

    //随机取一个域名
    // 将逗号分隔的字符串转换为 List[String]
    val domainList = if (domains != null) domains.split(",").toList else List.empty

    // 随机选择一个域名
    var damin=if (domainList.nonEmpty) {
      Random.shuffle(domainList).head
      //Some(Random.shuffle(domainList).head) // 随机打乱列表并取第一个
    } else {
      None // 如果列表为空，返回 None
    }
    damin+=str
    //domainProperties.getList.foreach(println)
    redisTemplate.opsForValue().set(str, s_url)

    damin.toString
  }
  @RequestMapping(Array("/getLinks"))
  def getLinks(@RequestBody s_url:String): String = {
    var id=0L
    //id=IdUtil.nextId()
    id=getNextId("id")

    val str = ToBase62.encodeToBase62(id)

    //随机取一个域名
    // 将逗号分隔的字符串转换为 List[String]
    val domainList = if (domains != null) domains.split(",").toList else List.empty

    // 随机选择一个域名
    var damin=if (domainList.nonEmpty) {
      Random.shuffle(domainList).head
      //Some(Random.shuffle(domainList).head) // 随机打乱列表并取第一个
    } else {
      None // 如果列表为空，返回 None
    }
    damin+=str
    //domainProperties.getList.foreach(println)
    redisTemplate.opsForValue().set(str, s_url)
    //设置过期时间
    //redisTemplate.expire(str, 60*60*24*7, java.util.concurrent.TimeUnit.SECONDS)
    redisTemplate.expire(str, 7, java.util.concurrent.TimeUnit.SECONDS)



    damin.toString
  }

  def getNextId(key: String): Long = {
    // 使用 INCR 命令自增长并返回新值
    val i = Random.nextInt(1000)
    redisTemplate.opsForValue().increment(key, i)
  }

}
