import axios from "axios";

const BASE_URl="http://localhost:8080/api/v1/employees";
class EmployeeService{
    getEmployees(){
        return axios.get(BASE_URl);
    }

    createEmployee(employee){
        return axios.post(BASE_URl,employee);
    }

    getEmployeeById(employeeId){
        return axios.get(BASE_URl + '/' + employeeId);
    }

    updateEmployee(employee,employeeId){
        return axios.put(BASE_URl + "/" +employeeId , employee);
    }

    deleteEmployee(employeeId){
        return axios.delete(BASE_URl + "/" + employeeId);
    }
}

export default new EmployeeService();