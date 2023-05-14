package com.example.crdemo.Controller;

import com.example.crdemo.model.Employee;
import com.example.crdemo.model.Env;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/env")
public class EnvController {
    @Value("${APP_MOD:#{null}}")
    private String APP_MODE;
    @Value("${listOfValues:#{null}}")
    private String[] valuesArray;
    @Value("#{${valuesMap:{}}}")
    private Map<String, Integer> valuesMap;

    @GetMapping("/")
    public ResponseEntity<Env> getEnv() {
        Env env = new Env();
        env.setAppMode(APP_MODE);
        env.setValuesMap(valuesMap);
        env.setValuesArray(valuesArray);
        System.out.println(env);
        return new ResponseEntity<>(env, HttpStatus.OK);
    }
}
