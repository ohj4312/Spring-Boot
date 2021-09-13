package com.example.hello.cotroller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path = "/hello") //http://localhost:8080/api/get/hello
    public String hello(){
        return "get Hello";
    }

    //@RequestMapping("hi") //get/post/put/delete 모든 메소드가 매핑되어 동작한다. (옛날 방식)
    //@RequestMapping(method=RequstMethod.GET)  == @GetMapping()
    @RequestMapping(path="/hi",method = RequestMethod.GET) //http://localhost:8080/api/get/hi Get으로 지정한다.
    public String hi(){
        return "hi";
    }

    //변화하는 구간은 path variable로 받는다.
    //http://localhost:8080/api/get/path-variable/{name} -> name은 바뀌는 부분
    //http://localhost:8080/api/get/path-variable/{spring-boot}
    //http://localhost:8080/api/get/path-variable/{spring}
    //http://localhost:8080/api/get/path-variable/{java}
    //http://localhost:8080/api/get/path-variable/{jpa}
    @GetMapping("/path-variable/{name}")
    // 만약 path-variable로 받는 name이라는 이름과 함수가 받는 name이라는 이름이 같아야 한다면
    //@PathVariable에 name="name을 추가하여 작성하여 pathVarialbe name을 전달받는다고 지정해준다.
    public String pathVariable(@PathVariable(name="name") String pathName /*, String name*/){
//    public String pathVariable(@PathVariable String name){ //변수의 이름은 path-variable과 동일해야 한다.
        System.out.println("PathVariable : "+pathName);
        return pathName;
    }

    //query parameter
    //?key=value&key2=value2
    // url(주소) 뒤 ? 부터 시작하여 key = value 형태로 오며, &로 key,value를 연결해준다.
    // http://localhost:8080/api/get/query-param?user=steve&email=steve@gmail.com&age=30
    @GetMapping(path="query-param")
    public String queryParam(@RequestParam Map<String,String> queryParam){

        StringBuilder sb=new StringBuilder();

        queryParam.entrySet().forEach( entry->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey()+"="+entry.getValue()+"\n");
        });

        return sb.toString();
    }

    //명확하게 API를 지정하여 query parameter에 오는 값을 정확히 알수 있을 때 명시적으로 코딩
    //위의queryParam처럼 작성하면 queryParam의 특정 key에 맞는 값을 가져오고싶을때 .get 등을 통해 각각 가져와야한다.
    @GetMapping("query-param02")
    public String queryParam02(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age)
    {
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name+" "+email+" "+age;
    }

    //만약 qeuryparam을 받아야하는 변수가 많아진다면 불편해진다.
    //이를 해결하기 위해 DTO를 만들어서 DTO의 객체형태로 받는다.
    @GetMapping("query-param03")
    public String queryParam03(UserRequest userRequest) //RequestParam 어노테이션을 붙이지 않는다.
    {
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }
}
