import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import EmployeeService from '../Employee/EmployeeService'

export default function EmployeeCount() {

    const[empCount,setEmpCount]=useState([]);

    const getEmployeeCount = () => {
        EmployeeService.getAllEmployeeByDepartmentId().then((response) => {
            setEmpCount(response.data);
            console.log("Emp Count : ");
            console.log(response.data);
        }).catch((error) => {
            console.log(error);
        })
    }

    useEffect(() => {
        getEmployeeCount();
    },[])

  return (
    <div className='container'>
        <h3 className='text-center mt-3'>Employee Count</h3>
        <Link to='/employees' className='btn btn-primary mb-2'>Back</Link>
        <table className='table table-hover'>
            <thead style={{textAlign:'center'}}>
                <tr>
                    <th>Department Number</th>
                    <th>Employee Count</th>
                </tr>
            </thead>
            <tbody style={{textAlign:'center'}}>
                {
                    empCount.map( count => 
                        <tr key={count.dept}>
                            <td>{count.dept}</td>
                            <td>{count.empCount}</td>
                        </tr>
                    )
                }
            </tbody>
        </table>
    </div>
  )
}
