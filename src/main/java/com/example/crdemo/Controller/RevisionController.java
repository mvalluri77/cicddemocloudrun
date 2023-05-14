package com.example.crdemo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/revision")
public class RevisionController {
    @GetMapping("/apiversion")
    public String apiVersion(){
        return "This is VERSION V2";
    }
}
