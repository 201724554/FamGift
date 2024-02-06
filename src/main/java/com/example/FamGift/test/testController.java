package com.example.FamGift.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
//@CrossOrigin(origins = {"http://localhost:3000"},
//        methods = {GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE})
public class testController {
    @PostMapping("/kakao")
    public ResponseEntity<?> kakaoLogin(@RequestBody Map<String, String> mp) {
        String code = mp.get("code");
        System.out.println(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
