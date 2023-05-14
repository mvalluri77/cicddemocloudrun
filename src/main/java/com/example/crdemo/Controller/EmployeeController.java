package com.example.crdemo.Controller;

import com.example.crdemo.model.Employee;
import com.example.crdemo.service.EmployeeService;
import org.aspectj.lang.reflect.CatchClauseSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {



    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public List<Employee> getAllEmployees() {
        logger.info("getAllEmployees start");
        logger.info("getAllEmployees end");
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        System.out.println("getEmployeeById called");
        logger.info("getEmployeeById called");
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        System.out.println("addEmployee called");
        logger.info("addEmployee called");
        employeeService.addEmployee(employee);
        return new ResponseEntity<>("Employee created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        logger.info("deleteEmployee called");
        boolean removed = employeeService.deleteEmployee(id);
        if (removed) {
            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
    }

    @Value("${PAYROLL_URL:#{null}}")
    private String PAYROLL_URL;

    @GetMapping("/salary/{employeeId}")
    public String getSalary(@PathVariable long employeeId) throws URISyntaxException, IOException, InterruptedException {
        try {
            logger.info("getSalary called");
            HttpClient client = HttpClient.newHttpClient();
            String url = PAYROLL_URL + "salary/" + employeeId;
            logger.info("url for salary {}", url);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                logger.info("response {}", response.body());
                return "The salary of emp id " + employeeId + " is USD " + response.body();
            } else {
                throw new RuntimeException("Failed to retrieve employee salary: " + response.body());
            }
        }catch (Exception e){
            return " PAYTOLL_URL not working "+e.getMessage();
        }
    }

}
