# Object Mapper
- Text 형태의 JSON을 Object 형태로 바꾸어주고, Object 형태를 Text 형태의 JSON으로 바꿔준다.
- ex ) 앞선 실습의 Controller 에서 request로 json(text)을 object로 바꾸어주고, response를 object로 return 한 것을 json(text)로 바꿨었다.

<br>

## Object Mapper 실습

- test 코드로 작성해본다.

<br>


## object를 text로 변환 >
- ObjectMapper는 getter 메소드를 참조한다.

### Main >
```java
@SpringBootTest
class ResponseApplicationTests {
	@Test
	void contextLoads() throws JsonProcessingException {
		var objectMapper =new ObjectMapper();

		//object->text
		var user=new ObjectUser("steve",10);
		var text=objectMapper.writeValueAsString(user); //value를 string으로 바꾸어준다. (예외처리 필요)
		System.out.println(text);
	}
}
```

### ObjectUser class
```java
public class ObjectUser {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ObjectUser(String name, int age){
        this.name=name;
        this.age=age;
    }

    @Override
    public String toString() {
        return "ObjectUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

<br>

### console 결과>

```
{"name":"steve","age":10}
```

<br><br>

## object를 text로 변환 >
- ObjectMapper는 getter 메소드를 참조한다.


### Main >
```java
    //text->object
	//object mapper는 default 생성자를 필요로 한다.
	var objectUser = objectMapper.readValue(text,ObjectUser.class);
	System.out.println(objectUser);
```
### ObjectUser class 추가>
```java
public class ObjectUser {
    public ObjectUser(){
        this.name=null;
        this.age=0;
    }

//생략
}
```


<br>

### console 결과>

```
ObjectUser{name='steve', age=10}
```

<br><br>

## 자주 실수하는 부분1
### ObjectUser class>
```java
public ObjectUser getDefaultUser(){
        return new ObjectUser("deafult",0);
    }

```
- Object Mapper를 사용하는 클래스에 get이 들어가는 method를 사용한다면 serialize,deserialize 시 애러가 발생한다.
- getDefaultUser() --> defaultUser() 와 같이 변경해야 한다.

<br><br>

## 자주 실수하는 부분2

- 아래와 같이 스네이크 표기법으로 받은 json 데이터를 카멜 표기법의 객체로 받을 때에도 정상 작동한다.


### ObjectUser class>
```java
@JsonProperty("phone_number")
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

```

### console 결과>
```
ObjectUser{name='steve', age=10, phoneNumber='null'}
```

```
{"name":"steve","age":10,"phone_number":"010-1111-2222"}
ObjectUser{name='steve', age=10, phoneNumber='010-1111-2222'}
```
- phone_number -> phoneNumber 정상 작동

