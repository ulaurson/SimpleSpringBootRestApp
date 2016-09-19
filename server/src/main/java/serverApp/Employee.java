package serverApp;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    private int employee_id;

    private String name;

    private int manager_id;

    private String getName() {
        return name;
    }

    private int getEmployeeId() {
        return employee_id;
    }

    private int getManagerId() {
        return manager_id;
    }

    @Transient
    private String workersString = "";

    public String returnWorkers(Iterable<Employee> employeeIterator){
        Map<Integer, Employee> allEmployees = new HashMap<>();

        int lowestManagerId = Integer.MAX_VALUE;
        int highestEmployeeId = 0;

        for(Employee e: employeeIterator){
            int managerId = e.getManagerId();
            int employeeId = e.getEmployeeId();
            allEmployees.put(employeeId, e);
            if(managerId < lowestManagerId){
                lowestManagerId = managerId;
                highestEmployeeId = employeeId;
            }
        }

        Map<String, String> visitedEmployeeNames = new HashMap<>();
        getEmployeeConnections(allEmployees, highestEmployeeId, visitedEmployeeNames, 4);

        return "{<br />\n" + workersString + "}";

    }

    private void getEmployeeConnections(Map<Integer, Employee> allEmployees,
                     int currentId, Map<String, String> visitedEmployeeNames, int indent){

        String currentEmployeeName = allEmployees.get(currentId).getName();
        visitedEmployeeNames.put(currentEmployeeName, "");

        String indentSpace  = new String(new char[indent])
                .replace("\0", "w").replaceAll("w", "&nbsp;");
        workersString += indentSpace + currentEmployeeName + ": {<br />\n";

        Iterator employeeIds = allEmployees.keySet().iterator();
        while(employeeIds.hasNext()){
            Employee employee = allEmployees.get(employeeIds.next());
            int managerId = employee.getManagerId();
            if(managerId == currentId && !visitedEmployeeNames.containsKey(employee.getName())) {
                getEmployeeConnections(allEmployees, employee.getEmployeeId(),
                        visitedEmployeeNames, indent + 4);
            }
        }

        workersString += indentSpace + "}<br />\n";
    }
}
