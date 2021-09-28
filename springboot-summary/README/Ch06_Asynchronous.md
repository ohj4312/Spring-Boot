# 비동기 처리하기
- Web MVC 패턴에서는 비동기 처리가 많이 활용하는 부분은 아니다.
- java 8버전 Completablefuture의 동작방식을 알고 있다면 좋다.
- 간단히 별도의 Thread를 통해 처리하는 부분이다 정도로 생각할 수도 있다.

### 비동기 처리 실습

### Main - AsyncApplication class
```java
@SpringBootApplication
@EnableAsync // 비동기 처리를 위한 설정
public class AsyncApplication {
	public static void main(String[] args)
	{
		SpringApplication.run(AsyncApplication.class, args);
	}
}
```

### ApiController class
```java
@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    //이부분은 lombok 을 사용하면 간단히 만들수있다.
    @Autowired 
    private AsyncService asyncService;

    public ApiController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }
    //----------------------------

    @GetMapping("/hello")
    public String hello(){
        asyncService.hello();
        log.info("method end");
        return "hello";
    }
}

```

### AsyncService class
```java
@Slf4j
@Service
public class AsyncService {
    @Async //비동기를 위한 설정
    public void hello() {
        for(int i=0; i<10;i++){
            try {
                Thread.sleep(2000);
                log.info("thread sleep...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 요청 : `http://localhost:8080/api/hello`

### Console 결과
```
2021-09-29 02:01:39.512  INFO 31548 --- [nio-8080-exec-1] c.e.async.controller.ApiController       : method end
2021-09-29 02:01:41.544  INFO 31548 --- [         task-1] com.example.async.service.AsyncService   : thread sleep...
```
- nio-8080-exec-1 와 task-1, 즉 서로 다른 Thread로 동작하고 있음을 볼 수 있다.
- Spring에서는 Application에 @EnableAsync 를 설정하고, 비동기처리를 하고 싶은 부분에 @Async 어노테이션을 추가하면 된다.

<br><br>

## Response 를 주는 법 - CompletableFuture

### ApiController class
```java
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    private AsyncService asyncService;

    @GetMapping("/hello")
    public CompletableFuture hello(){
        log.info("completable future init");
        return asyncService.run();
    }
}
```
### AsyncService class
```java
@Slf4j
@Service
public class AsyncService {

    @Async
    public CompletableFuture run(){
      return new AsyncResult(hello()).completable();
    }

    public String hello() {
        for(int i=0; i<10;i++){
            try {
                Thread.sleep(2000);
                log.info("thread sleep...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "async hello";
    }
}
```

### 요청 : `http://localhost:8080/api/hello`

### Console 결과
```
2021-09-29 02:16:16.150  INFO 25116 --- [nio-8080-exec-1] c.e.async.controller.ApiController       : completable future init
2021-09-29 02:16:18.173  INFO 25116 --- [         task-1] com.example.async.service.AsyncService   : thread sleep...
```
- 마찬가지로 별도의 Thread에서 동작한다.
- CompletableFuture 는 여러개의 API를 동시에 전송하고 그 결과를 Join해서 받을 때 사용하기 편리하다.
- 기본적으로 Spring이 정한 Thread를 사용하게 되는데 Auto Configuration에 들어있는 기본적인 값을 보고 사용할 수 있다. 
- 하지만 개수가 한정적이라 일정 개수가 넘어가면 기본적으로 spring에서 제공하는 thread를 사용할 수 없기 때문에 직접 Thread를 만들어서 사용할 수도 있다.

<br><br>

## Thread 만들기

```java
@Configuration
public class AppConfig {

    @Bean("async-thread")
    public Executor asyncThread(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(100); //최대 몇개까지 할 것인지
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(10);
        threadPoolTaskExecutor.setThreadNamePrefix("Async-");
        
        return threadPoolTaskExecutor;
    }
}
```
-  corepoosize 10개 다쓰면 queue에 내용이 들어간다.
-   queue 10개 차면 다시 poosize 10개만큼 한번 더늘어나서 20개가된다.
- queue에 다시 10개 이런식으로 늘어난다. 
- MaxPoolSize까지 차고 queue에 들어가는 것이아니라 queue에 maxpoolsize까지 들어간다. 
- Thread의 사용, 환경에 따라 많이 달라지는 부분이다.

<br>

### AsyncService class
```java
@Slf4j
@Service
public class AsyncService {
    @Async("async-thread")
    public CompletableFuture run(){
      return new AsyncResult(hello()).completable();
    }

    //생략
}
```

### Console 결과
```
2021-09-29 02:38:21.304  INFO 36088 --- [        Async-1] com.example.async.service.AsyncService   : thread sleep...
```
- 이제 지정한 이름의 Thread로 동작한다.
- Ansync 는 AOP 기반이기 때문에 Proxy 패턴이 적용되어 public 메소드에만 적용 가능하다.
- 같은 클래스 내에서 메소드를 호출할 때에는 비동기처리 되지 않는다.
- ex >
```java
    @Async("async-thread")
    public CompletableFuture run(){
        hello(); // (비동기 x)
        return new AsyncResult(hello()).completable();
    }
```
- MongoDB , NoSQL을 사용할 때에는 Spring Webflux를 추천한다.
- RDBMS를 사용하면 트랜잭션에 의해 동기방식을 사용하므로 비동기를 사용하지 않는 편이다.


