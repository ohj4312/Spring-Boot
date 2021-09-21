# IoC (Inversion of Control)
- IoC는 제어의 역전이다.
- 스프링에서는 일반적인 Java 객체를 new 로 생성하여 개발자가 관리하는 것이 아닌 Spring Container에 모두 맡긴다. 
- 즉 개발자에서 프레임워크로 제어의 객체 관리의 권한이 넘어갔으므로 "제어의 역전" 이라고 한다.




<br><br>

# DI (Dependency Injection)
- spring container에서 객체의 생명주기를 관리하므로 개발자가 객체를 사용하기 위해서 Spring Container로부터 주입을 받는다.
- DI 장점
  - 의존성으로부터 격리시켜 코드 테스트에 용이하다.
  - DI를 통하여 불가능한 상황을 Mock 객체와 같은 기술을 통하여 안정적으로 테스트 가능하다.
  - 코드를 확장하거나 변경할 때 영향을 최소화한다. (추상화)
  - 순환참조를 막을 수 있다.


<br><br>

## encoder 예제

- Main class

```java
  public class Main {
    public static void main(String[] args) {
        //encoding 예제
        String url="www.naver.com/books/it?page=10&size=20&name=spring-boot";

        //Base 64 encoding
        Encoder encoder=new Encoder();
        String result=encoder.encode(url);
        System.out.println(result);
    }
}
```
- Encoder class

```java
public class Encoder {
    public String encode(String message){
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
```
- console 결과

```
d3d3Lm5hdmVyLmNvbS9ib29rcy9pdD9wYWdlPTEwJnNpemU9MjAmbmFtZT1zcHJpbmctYm9vdA==
```

<br><br>

## 확장

- Main class

```java
  public class Main {
    public static void main(String[] args) {
        //encoding 예제
        String url="www.naver.com/books/it?page=10&size=20&name=spring-boot";

        //url encoding
        UrlEncoder urlEncoder=new UrlEncoder();
        String urlResult=urlEncoder.encode(url);
        System.out.println(urlResult);
    }
}
```
- UrlEncoder class

```java
public class UrlEncoder {
    public String encode(String messege){
        try {
            return URLEncoder.encode(messege,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
```


- console 결과
```
www.naver.com%2Fbooks%2Fit%3Fpage%3D10%26size%3D20%26name%3Dspring-boot
```

<br><br>

## 추상화 적용

- Encoder와 UrlEncoder는 같은 역할을 한다.
- Interface를 통해 추상화한다.

<br>

- IEncoder Interface
```java
public interface IEncoder {
    String encode(String messeage);
}

```

- URLEncoder implemets 추가
```java
public class UrlEncoder implements IEncoder{
```

- Main
```java
  //Interface 추가
  IEncoder base64Encoder=new Base64Encoder();
  String base64result=base64Encoder.encode(url);
  System.out.println(base64result);

  IEncoder urlEncoder=new UrlEncoder();
  String urlResult=urlEncoder.encode(url);
  System.out.println(urlResult);
```

<br><br>

## DI 추가

- Encoder Class
```java
public class Encoder{
    private IEncoder iEncoder;

    public Encoder(IEncoder iEncoder){
        this.iEncoder=iEncoder;
    }

    public String encode(String message){
        return iEncoder.encode(message);
    }
}
```
- Main
```java
  Encoder encoderDI=new Encoder(new Base64Encoder());
  //Encoder encoderDI=new Encoder(new UrlEncoder());
  String resultDI=encoder.encode(url);
  System.out.println(resultDI);
        
```
- 외부에서 사용하는 객체 ( 현재 예제에서는 Base64Encoder, UrlEncoder)를 IEncoder를 통해 주입받는다. (DI)
- Encoder가 변경될 때, Test를 진행할 때 기존 코드와 달리 클래스 내부 코드의 변경 없이 객체의 주입을 통해 동작한다.
- 코드의 관리, 재사용이 편리해진 장점이 있다.
- 다른 Encoder를 추가하여 사용할 때에도 주어진 룰에 따라 동작하도록 주입하면 된다. (확장 용이)
- 하지만 여전히 생성자에서 주입받는 외부객체는 개발자가 관리하고 있다.
- Spring IoC는 이처럼 개발자가 직접 주입하지 않고 Spring Container가 객체의 생명주기를 대신 관리해준다.

<br>

## SpringApplicationContext

- Spring이 실행될 때 @Component Annotation이 붙은 클래스를 찾고, 직접 객체를 싱글톤 형태로 만들어 관리한다.
- SpringApplicationContext를 통해 객체를 가져와서 사용할 수 있다.
- SpringApplicationContext에 접근하여 객체를 가져오기 위한 코드가 필요하다.

<br>

-  ApplicationContextProvider Class
```java
@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException { 
        context=applicationContext;
    }
    public static ApplicationContext getContext(){
        return context;
    }
}
```
- ApplicationContextAware를 implement한다.
- setApplicationContext의 주입은 Spring에서 제공한다.
- Bean을 찾는 방법
  - 이름을 통해 찾기
  - class type을 통해 찾기
- Spring에서 Bean을 주입받을 수 있는 곳은 변수, 생성자, set메소드이다.

<br>

- Encoder class setMethod 추가
```java
    public void setIEncoder(IEncoder iEncoder){
        this.iEncoder=iEncoder;
    }
```

- Main
```java
    ApplicationContext context = ApplicationContextProvider.getContext();
    Base64Encoder base64Encoder=context.getBean(Base64Encoder.class);
    UrlEncoder urlEncoder=context.getBean(UrlEncoder.class);

    Encoder encoder=new Encoder(base64Encoder);
    String url="www.naver.com/books/it?page=10&size=20&name=spring-boot";

    String result=encoder.encode(url);
    System.out.println(result);

    encoder.setIEncoder(urlEncoder);
    result=encoder.encode(url);
    System.out.println(result);
```

- console 출력
```
d3d3Lm5hdmVyLmNvbS9ib29rcy9pdD9wYWdlPTEwJnNpemU9MjAmbmFtZT1zcHJpbmctYm9vdA==
www.naver.com%2Fbooks%2Fit%3Fpage%3D10%26size%3D20%26name%3Dspring-boot
```

- Encoder에 @Component Annotation을 붙일 때 Base64Encoder,UrlEncoder 모두 Component이기 때문에 어떠한 Bean을 매칭할지 알 수 없기 때문에 에러가 발생한다.

### Encoder class

```java
@Component
public class Encoder{

    //생략

    public Encoder(IEncoder iEncoder){ //에러 발생
        this.iEncoder=iEncoder;
    }

    //생략
}
```

<br>

- @Qualifier 적용
```java
    public Encoder(@Qualifier("urlEncoder") IEncoder iEncoder){
        //UrlEncoder 사용
        this.iEncoder=iEncoder;
    }
```
```java
    public Encoder(@Qualifier("base64Encoder") IEncoder iEncoder){ //base64Encoder 사용
        this.iEncoder=iEncoder;
    }
```

- @Quelifier의 컴포넌트 명은 클래스에 아무 명칭 없이 생성시 제일 앞 글자를 소문자로 변한 이름으로 지정한다.
- 만약 컴포넌트의 명칭을 정하고 싶다면 @Component("원하는 이름")으로 지정 가능하다.

```java
@Component("base74Encoder") 
public class Base64Encoder implements IEncoder{
    ...
}

@Component
public class Encoder{
    public Encoder(@Qualifier("base74Encoder") IEncoder iEncoder){ //base64Encoder 사용 (명칭을 74로 바꾼 것)
        this.iEncoder=iEncoder;
    }
}
```

<br>

- Bean을 직접 설정하는 법
- Encoder Class를 @Component로 만들어 @Quelifier를 사용하지 않고 객체를 직접 생성하여 관리하도록 한다.


```java
// 위치 : Main class 아래

@Configuration
class AppConfig{

	@Bean("base64Encode") // Bean을 미리 등록해서 사용한다.
	public Encoder encoder(Base64Encoder base64Encoder){
		return new Encoder(base64Encoder);
	}

	@Bean("urlEncode") 
	public Encoder encoder(UrlEncoder urlEncoder){
		return new Encoder(urlEncoder);
	}
}
```
- @Configuration : 한개의 클래스에서 여러개의 Bean을 등록한다는 의미 (내부에 @Component를 가지고있다)
- @Bean("urlEncode") : UrlEncoder class가 @Component로 관리되므로 Spring Container 내의 동일한 이름이라 충돌발생 -> urlEncode로 이름을 붙인다.
- 마찬가지로 위 클래스에서도 Quelifier를 통해 명시적으로 어떤 클래스를 주입할 것인지 명시할 수 있다

```java
    @Bean("urlEncode") 
	public Encoder encoder(@Qualifier("urlEncoder")  UrlEncoder urlEncoder){
		return new Encoder(urlEncoder);
	}
```

- 객체를 new로 직접 생ㅅ어하는 것이 아닌 SpringContext를 통해 객체가 생성되고 생명주기가 관리된다.
- 이때 변수, 생성자, setter에 Annotation을 통해 주입받아 사용한다.

<br>

### 스프링에서 직접 관리하는 객체를 Bean이라고 하고, Bean들이 관리되는 장소가 Spring Container이며, 이러한 권한을 Spring Container가 가져갔기 때문에 IoC (제어의 역전)이라고 한다.