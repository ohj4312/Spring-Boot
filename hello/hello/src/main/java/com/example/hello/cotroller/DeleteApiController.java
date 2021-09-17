package com.example.hello.cotroller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeleteApiController {

    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable String userId, @RequestParam String account){
        System.out.println(userId);
        System.out.println(account);
        

        //리소스가 없다는 값을 던질 필요가 없다.
        // delete -> 리소스 삭제이므로 있어서 삭제를 해서 없는 상태와 원래 없는 상태와 모두 삭제된 상태인 것으로 보아 200 code를 준다.
    }
}
