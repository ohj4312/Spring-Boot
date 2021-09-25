import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.Car;
import dto.User;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("main");

        ObjectMapper objectMapper=new ObjectMapper();

        User user=new User();
        user.setName("hong");
        user.setAge(10);

        Car car1=new Car();
        car1.setName("K5");
        car1.setCarNumber("11가 1111");
        car1.setType("sedan");

        Car car2=new Car();
        car2.setName("Q5");
        car2.setCarNumber("22가 1234");
        car2.setType("SUV");

        List<Car> carList= Arrays.asList(car1,car2);
        user.setCars(carList);

        System.out.println(user);

        String json=objectMapper.writeValueAsString(user);
        System.out.println(json);

        //json parsing
        JsonNode jsonNode=objectMapper.readTree(json);
        String _name=jsonNode.get("name").asText();
        int _age=jsonNode.get("age").asInt();
        System.out.println("name : "+_name);
        System.out.println("age : "+_age);

        //String _list=jsonNode.get("cars").asText();
        //System.out.println(_list);

        JsonNode cars=jsonNode.get("cars");
        ArrayNode arrayNode=(ArrayNode)cars;
        //objectMapper.convertValue() : MAP을 객체로 바꾸는 등 여러가지 Object를 json이 아닌 클래스로 매핑시킬수 있다.
        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
        System.out.println(_cars);

        //AOP, Filter, Intersepter등 Body 내용을 특정 값을 각각의 Node에 대해 변경시킬 때 에 활용가능하다.
        ObjectNode objectNode=(ObjectNode) jsonNode;
        objectNode.put("name","steve");
        objectNode.put("age",20);

        System.out.println(objectNode.toPrettyString()); //json처럼 출력되도록 함
    }
}
