import axios from 'axios'

const DEPT_BASE_REST_API_URL = 'http://localhost:9091/api/department';

class DepartmentService {

    getAllDepartment(){
        return axios.get(DEPT_BASE_REST_API_URL + '/get')
    }

    getDepartmentById(deptId){
        return axios.get(DEPT_BASE_REST_API_URL + '/get/' + deptId)
    }

    createDepartment(department){
        return axios.post(DEPT_BASE_REST_API_URL+ '/add', department)
    }

    updateDepartment(deptId, department){
        return axios.put(DEPT_BASE_REST_API_URL + '/update/' +deptId, department);
    }

    deleteDepartment(deptId){
        return axios.delete(DEPT_BASE_REST_API_URL + '/delete' + deptId);
    }

    deleteAllDepartment(){
        return axios.delete(DEPT_BASE_REST_API_URL + '/delete-all' )
    }

}