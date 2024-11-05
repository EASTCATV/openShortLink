package demo

import org.springframework.web.bind.annotation.{CrossOrigin, GetMapping, RequestMapping, RestController}
/**
  * @Author :
  * @Date : 2020/8/5 12:03 PM 
  * @Version : 1.2.1
  */
@RestController
@CrossOrigin
@RequestMapping( Array( "/demo" ) )
class Demo {


  @GetMapping(Array("/a"))
  def a(): String ={
    "a"
  }
}
