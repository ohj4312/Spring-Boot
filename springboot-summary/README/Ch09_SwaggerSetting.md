# Swagger 설정하기
- swagger dependency를 maven repository에서 찾아온다.
- 기존에는 springfox swagger2와 Springfox Swqgger UI 두개를 추가해서 사용했었다.
- 최근에는 Springfox Boot Starter를 사용한다.
- https://mvnrepository.com/artifact/io.springfox/springfox-boot-starter

### build.gradle
```gradle
dependencies {
	// 생략
	// https://mvnrepository.com/artifact/io.springfox/springfox-boot-starter
	implementation group: 'io.springfox', name: 'springfox-boot-starter', version: '3.0.0'
}
```

- 이후 작성하는 모든 Controller가 기본적으로 공개된다.

### `http://localhost:8080/swagger-ui/`

<img src="./img/swagger_ui.PNG">

- Project에 추가한 ApiController가 노출된다.
- Basic-error-controller는 swagger에서 제공하는 기본 controller이다.

<br>

### ApiController excute 시키기
<img src="./img/swagger_response.PNG">

- talend API Tester, Browser 실행과 마찬가지로 Swagger를 통해 요청과 응답 (header,status code 등)을 확인할 수 있다.
- 내외부 사용자에게 swagger-ui를 제공함으로써 테스트를 확인할 수 있다.