import axios from 'axios'

const EMPLOYEE_BASE_REST_API_URL = 'http://localhost:9091/api/employee';

class EmployeeService {

    getAllEmployees(){
        return axios.get(EMPLOYEE_BASE_REST_API_URL + '/get' )
    }

    getEmployeeById(empId){
        return axios.get(EMPLOYEE_BASE_REST_API_URL + '/get/' + empId)
    }

    createEmployee(employee){
        return axios.post(EMPLOYEE_BASE_REST_API_URL + '/add' , employee)
    }

    updateEmployee(empId, employee){
        return axios.put(EMPLOYEE_BASE_REST_API_URL + '/update/' + empId, employee);
    }

    deleteEmployee(empId){
        return axios.delete(EMPLOYEE_BASE_REST_API_URL + '/delete/' + empId);
    }

    deleteAllEmployee(){
        return axios.delete(EMPLOYEE_BASE_REST_API_URL + '/delete-all' )
    }

    getEmployeeByAscName(){
        return axios.get(EMPLOYEE_BASE_REST_API_URL + '/asc' )
    }

    getEmployeeByDescName(){
        return axios.get(EMPLOYEE_BASE_REST_API_URL + '/desc' )
    }

    getAllEmployeeByDepartmentId(){
        return axios.get(EMPLOYEE_BASE_REST_API_URL + '/emp-count' )
    }

    getAllEmployeeByPages(page,pageSize){
        return axios.get(EMPLOYEE_BASE_REST_API_URL + '/paging/' + page + '/' + pageSize)
    }
}

export default new EmployeeService();