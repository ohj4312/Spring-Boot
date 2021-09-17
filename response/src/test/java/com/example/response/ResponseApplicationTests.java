package com.example.response;

import com.example.response.dto.ObjectUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ResponseApplicationTests {

	@Test
	void contextLoads() throws JsonProcessingException {
		System.out.println("---------");

		//Text JSON -> Object
		//Objcet -> Text JSON

		//controller request : json(text)->object
		//response : object -> json(text)
		//object mapper 는 get method를 활용한다.
		var objectMapper =new ObjectMapper();

		//object->text
		var user=new ObjectUser("steve",10,"010-1111-2222");
		var text=objectMapper.writeValueAsString(user); //value를 string으로 바꾸어준다. (예외처리 필요)
		System.out.println(text);

		//text->object
		//object mapper는 default 생성자를 필요로 한다.
		var objectUser=objectMapper.readValue(text,ObjectUser.class);
		System.out.println(objectUser);

	}

}
