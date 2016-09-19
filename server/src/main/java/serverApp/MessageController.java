package serverApp;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import calculator.Calculator;
import serverApp.Employee;
import serverApp.EmployeeRepository;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
public class MessageController {


    @RequestMapping(value="/hello", method=GET)
    @ResponseBody
    public String hello(){
        return "Hello World!";
    }


    @RequestMapping(value="/calculate", method=GET)
    @ResponseBody
    public double calculate(@RequestParam(value="num1") double num1,
                            @RequestParam(value="num2") double num2,
                            @RequestParam(value="op", defaultValue="sum") String op) {
        return new Calculator().calculate(num1, num2, op);
    }

    @RequestMapping(value="/calculate",
            method=POST,
            consumes = "application/json")
    @ResponseBody
    public double calculateJSON(@RequestBody String json) {
        JSONObject obj = new JSONObject(json);
        return new Calculator().calculate(obj.getInt("num1"),
                obj.getInt("num2"), obj.getString("op"));
    }


    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value="/employees", method=GET)
    @ResponseBody
    public String employees() {
        Iterable<Employee> employees = employeeRepository.findAll();
        return new Employee().returnWorkers(employees);
    }

}
