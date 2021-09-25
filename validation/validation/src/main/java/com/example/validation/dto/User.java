package com.example.validation.dto;

import com.example.validation.annotation.YearMonth;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User {
    @NotBlank
    private String name;

    @Max(value = 90)
    private int age;

    @Email //email 양식에 맞지 않다면 에러 발생
    private String email;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$",message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
    private String phoneNumber;

    //@Size(min = 6,max = 6)
    @YearMonth
    private String reqYearMonth; // yyyyMM

    public String getReqYearMonth() {
        return reqYearMonth;
    }

    public void setReqYearMonth(String reqYearMonth) {
        this.reqYearMonth = reqYearMonth;
    }

/*    @AssertTrue(message = "yyyyMM의 형식에 맞지 않습니다.")
    public boolean isReqYearMonthValidation(){ //boolean을 리턴하도록 is를 붙여서 method이름을 작성해야 정상적으로 찾아 들어온다.

        try{
            //LocalDate localDate=LocalDate.parse(this.reqYearMonth, DateTimeFormatter.ofPattern("yyyyMMdd"));
            //this.reqYearMonth = getReqYearMonth()+"01"; //기본적으로 LocalDate이기 때문에 yyyyMMdd의 형태로 들어가기 때문에 전처리가 필요
            //위처럼 this.reqYearMonth값을 직접 변경하게 되면 @MAX Validation에서 max 6 을 넘어가기 때문에 유효하지 않은 값이 된다.
            LocalDate localDate=LocalDate.parse(getReqYearMonth()+"01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        }catch (Exception e){
            return false;
        }
        return true; // ture면 정상, false면 parsing이 제대로 안된것
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", reqYearMonth='" + reqYearMonth + '\'' +
                '}';
    }
}
