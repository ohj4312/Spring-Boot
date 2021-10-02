# JUnit

## TDD (Test-driven Deveplopment)
- 테스트 주도 개발에서 사용하지만, 코드의 유지 보수 및 운영환경에서의 에러를 미리 방지하기 위해서 단위 별로 검증하는 테스트 프레임워크

## 단위 테스트
- 작성한 코드가 기대하는대로 동작을 하는지 검증하는 절차

## JUnit
- Java 기반의 단위 테스트를위한 프레임워크
- Annotation 기반으로 테스트를 지원하며, Assert를 통하여 ( 예상 , 실제 ) 를 통해 검증


## JAVA Project 실습


### build.gradle 
```
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
}
```
- defualt로 dependency에 junit이 추가되어 있다