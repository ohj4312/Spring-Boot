# Interceptor

- Interceptor란 Filter와 매우 유사한 형태로 존재하지만, 차이점은 Spring Context에 등록된다.
- AOP와 유사한 기능을 제공할 수 있으며, 주로 인증 단계를 처리하거나 Logging 하는데에 사용한다.
- 이를 선/후 처리함으로써 Service Business Logic과 분리시킨다.