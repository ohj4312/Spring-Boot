package com.example.filter.dto;

import lombok.*;

//@Getter //멤버 변수에 대해 getter를 만들어준다
//@Setter
@Data //getter,setter를 포함해서 equals,hashcode,toString 등 모두 관리해준다.
@NoArgsConstructor //기본 생성자
@AllArgsConstructor // 전체 Argument를 받는 생성자 생성
public class User {
    private String name;
    private int age;
}
