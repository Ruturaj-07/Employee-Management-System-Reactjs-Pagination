import React, {useState, useEffect} from 'react'
import { Link } from 'react-router-dom'
import EmployeeService from '../Employee/EmployeeService'
import Swal from 'sweetalert2';
import Pagination from "@material-ui/lab/Pagination";

export default function EmployeeList() {

    const[employees,setEmployees]=useState([])
    const [page, setPage] = useState(1);
    const [count, setCount] = useState(0);
    const [size, setSize] = useState(3);
    const pageSizes = [3,4,5,6];

    // useEffect(() => {
    //     getAllEmployees();
    // },[])

    useEffect(() => 
        retrieveEmployees()
    ,[page, size]);

    const getRequestParams = (page, size) => {
        let params = {};
        if (page) {
          params["page"] = page - 1;
        }
        if (size) {
          params["size"] = size;
        }
        return params;
      }; 
    
    const retrieveEmployees = () => {
    const params = getRequestParams(page, size);
    EmployeeService.getAllEmployeeByPages(params.page,params.size)
        .then((response) => {
        const { employees, totalPages } = response.data;
        setEmployees(employees);
        setCount(totalPages);
        console.log(response.data);
        })
        .catch((e) => {
        console.log(e);
        });
    };

    const handlePageChange = (event, value) => {
        setPage(value);
    };
    const handlePageSizeChange = (event) => {
        setSize(event.target.value);
        setPage(1);
    };

    const getAllEmployees = () => {
        EmployeeService.getAllEmployees().then((response) => {
            setEmployees(response.data);
            console.log("Get all Employees : ");
            console.log(response.data);
        }).catch((error) => {
            console.log(error);
        })
    }

    const deleteEmployeeById = (empId) => {
        Swal.fire({
            icon: 'warning',
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel!',
        }).
        then((response) => {
            if(response.value){
                EmployeeService.deleteEmployee(empId); 
                getAllEmployees();
                Swal.fire({
                    icon: 'success',
                    title: 'Deleted!',
                    text: `${employees.empName}'s data has been deleted.`,
                    showConfirmButton: false,
                    timer: 1500,
                }); 
            }   
        }).catch((error) => {
            console.log(error)
        })
    }

  return (
    <div className='container'>
        {/* <div className='mt-5'></div> */}
        <Link to='/add-employee' className='btn btn-success mb-2 mt-5'>Add Employee</Link> &nbsp;&nbsp;
        <Link to='/count' className='btn btn-success mb-2 mt-5'>View Employee Count</Link> &nbsp;&nbsp;
        <Link to='/department' className='btn btn-success mb-2 mt-5'>Departments</Link>
        <h3 className='text-center mt-2'>Employee List</h3>
        <table className='table table-hover mt-3'>
            <thead style={{textAlign:'center'}}>
                <tr>
                    {/* <th>Employee Id</th> */}
                    <th >Name</th>
                    <th>Age</th>
                    <th>Email</th>
                    <th>Joining Date</th>
                    <th>Salary</th>
                    <th>Department Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody style={{textAlign:'center'}}>
                {
                    employees.map( emp => 
                        <tr key={emp.empId}>
                            <td>{emp.empName}</td>
                            <td>{emp.empAge}</td>
                            <td>{emp.empEmail}</td>
                            <td>{emp.joiningDate}</td>
                            <td>{emp.empSal}</td>
                            <td>{emp.department.deptName}</td>
                            <td>
                                <Link className="btn btn-info" to={`/edit-employee/${emp.empId}`} >Update</Link>
                                <button className='btn btn-danger' onClick={() => deleteEmployeeById(emp.empId)} 
                                style={{marginLeft:"10px"}}>Delete</button>
                            </td>
                        </tr>
                    )
                }
            </tbody>
        </table>
        <div className="mt-3">
          {"Items per Page: "}
          <select onChange={handlePageSizeChange} value={size}>
            {pageSizes.map((size) => (
              <option key={size} value={size}>
                {size}
              </option>
            ))}
          </select>
          <Pagination
            className="my-3"
            count={count}
            page={page}
            siblingCount={1}
            boundaryCount={1}
            variant="outlined"
            shape="rounded"
            onChange={handlePageChange}
          />
        </div>
    </div>
  )
}
