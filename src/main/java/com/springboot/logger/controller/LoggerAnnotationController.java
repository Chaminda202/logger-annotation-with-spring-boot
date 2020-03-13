package com.springboot.logger.controller;

import com.springboot.logger.annotation.MethodLogger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/logger/annotation")
public class LoggerAnnotationController {

    @GetMapping(value = "/{name}")
    @MethodLogger
    public ResponseEntity<String> printName(@PathVariable("name") String name){
        StringBuilder builder = new StringBuilder();
        builder.append("Hello Welcome ");
        builder.append(name.toUpperCase());
        return ResponseEntity.ok().body(builder.toString());
    }

    @GetMapping(value = "/age")
    @MethodLogger
    public ResponseEntity<String> printAge(@RequestParam("age") Integer age){
        StringBuilder builder = new StringBuilder();
        builder.append("Hello your age is ");
        builder.append(age);
        return ResponseEntity.ok().body(builder.toString());
    }
}
