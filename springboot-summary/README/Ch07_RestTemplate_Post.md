# Rest Template 사용하기 -POST 예제
- [Chapter07](./Ch07_RestTemplate.md). server, clinet project에 이어서 실습을 진행한다.


## Rest Template

## POST 실습

### Client - ApiController class
```java
public class ApiController {
    //생략
    @GetMapping("/hello")
    public UserResponse hello(){
        return restTempleteService.post();
    }
}
```

### Client - RestTempleteService class

```java
@Service
public class RestTempleteService {
    public UserResponse post(){
        //URI 만들기
        //http://localhost:9090/api/server/user/{userId}/name/{userName}
        URI uri=UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve")
                .toUri();
        
        System.out.println(uri);
        // http body -> object -> object mapper -> json ->rest tempalte ->http body json
        UserRequest req=new UserRequest();
        req.setName("steve");
        req.setAge(10);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri,req,UserResponse.class);
        
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());
        return response.getBody();
    }
}
```
- UriComponentsBuilder의 expand()에 들어가는 값이 중괄호를 사용한 {userId},{userName}에 순차적으로 (,)로 구분되어 맞춰 들어간다.
- buildAndExpand() 메소드도 있다.
- Post는 http body를 필요로 하는데, 요청을 Object 형태로 받고, Object Mapper가 내부적으로 Json Text로 변경시키고 그것을 Rest Templete에 가져다 쓸 수 있다. 
  - http body -> object -> object mapper -> json ->rest tempalte ->http body json
- ResponseEntity<String> response = restTemplate.postForEntity(uri,req,String.class)
  - Generic Type으로 String을 사용할 수 있는데 ,응답이 무엇인지 모를 때 String으로 확인하는 것도 가능하다! 응답을 확인하고 class를 디자인해도 좋다.

### Server - ServerApiController

```java
@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {
    @PostMapping("user/{userId}/name/{userName}")
    public User post(@RequestBody User user, @PathVariable int userId, @PathVariable String userName){
        log.info("userId : {} , userName : {} ",userId,userName);
        log.info("client req : {}",user);
        return user;
    }
}
```

### 요청 : `http://localhost:8080/api/client/hello`

### Client - Console 결과
```
http://localhost:9090/api/server/user/100/name/steve
200 OK
[Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Wed, 29 Sep 2021 08:13:05 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]
UserResponse{name='steve', age=10}
```

### Server - Console 결과 log 일부
```
INFO 6136 --- [nio-9090-exec-1] c.e.s.controller.ServerApiController     : userId : 100 , userName : steve 
INFO 6136 --- [nio-9090-exec-1] c.e.s.controller.ServerApiController     : client req : User(name=steve, age=10)
```


<br><br>

