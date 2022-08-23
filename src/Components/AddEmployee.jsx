import React, {useState, useEffect} from 'react'
import {Link, useHistory, useParams} from 'react-router-dom';
import EmployeeService from '../Employee/EmployeeService'
import Swal from 'sweetalert2';

export default function AddEmployee() {

    const[empId,setEmpId]=useState()
    const[empName,setEmpName]=useState('')
    const[empAge,setEmpAge]=useState()
    const[empEmail,setEmpEmail]=useState('')
    const[empSal,setEmpSal]=useState()
    const[joiningDate,setJoiningDate]=useState()
    const[deptId,setDeptId]=useState()
    const[deptName,setDeptName]=useState('')
    const history=useHistory();
    const {eid} = useParams();    
    const department={deptId,deptName};
    

    const saveOrUpdate = (e) => {
        e.preventDefault();
        const employee={empId,empName,empAge,empEmail,empSal,joiningDate,department}
        if(eid){
            // console.log(eid);
            Swal.fire({
                icon: 'warning',
                title: 'Are you sure?',
                text: "Do you want to Update it really ?",
                showCancelButton: true,
                confirmButtonText: 'Yes, Update it!',
                cancelButtonText: 'No, cancel!',
            })
            .then((response) => {
                if(response.value){
                    EmployeeService.updateEmployee(eid,employee);
                    console.log(response.data);
                    Swal.fire({
                        icon: 'success',
                        title: 'Updated!',
                        text: `${employee.empName}'s data has been updated.`,
                        showConfirmButton: false,
                        timer: 1500
                    });
                    history.push('/employees')
                }   
            }).catch((error) => {
                console.log(error);
            })
        }
        else{
            Swal.fire({
                icon: 'warning',
                title: 'Are you sure?',
                text: "Do you want to Add this record ?",
                showCancelButton: true,
                confirmButtonText: 'Yes, Add it!',
                cancelButtonText: 'No, cancel!',
            })
            .then((response) => {
                if(response.value){
                    EmployeeService.createEmployee(employee);
                    console.log(response.data);
                    Swal.fire({
                        icon: 'success',
                        title: 'Added!',
                        text: `${employee.empName}'s data has been Added.`,
                        showConfirmButton: false,
                        timer: 1500
                    });
                history.push('/employees')
                }    
            }).catch((error) => {
                console.log(error)
            })
        }  
    }

    //by using react hook useEffect we tell react that your component needs to do something after render
    useEffect(()=> {
        EmployeeService.getEmployeeById(eid).then((response) => {
            setEmpId(response.data.empId)
            setEmpName(response.data.empName)
            setEmpAge(response.data.empAge)
            setEmpEmail(response.data.empEmail)
            setEmpSal(response.data.empSal)
            setJoiningDate(response.data.joiningDate)
            setDeptId(response.data.department.deptId)
            // setDeptName(response.data.deptName)
        }).catch((error) => {
            console.log(error);
        })
    },[])

    const title = () => {
        if(eid) {
            return <h2 className='text-center'>Update Employee</h2>
        }
        else {
            return <h2 className='text-center'>Add Employee</h2>
        }
    }

  return (
    <div>
        <br/><br/>
        <div className='container'>
            <div className='row'>
                <div className='card col-md-6 offset-md-3 offset-md-3'>
                   { 
                        title() 
                   }
                </div>

                <div className='card-body'>
                    <form>
                        {/* <div className='form-group mb-2'>
                            <label className='form-label'>Employee Id : </label>
                            <input type='number' placeholder='Enter Employee Id' name='empId' className='form-control' value={empId} onChange={(e) => setEmpId(e.target.value)}/>
                        </div> */}
                        <div className='form-group mb-2'>
                            <label className='form-label'>Employee Name : </label>
                            <input type='text' placeholder='Enter Employee Name' name='empName' className='form-control' value={empName} onChange={(e) => setEmpName(e.target.value)}/>
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Employee Age : </label>
                            <input type='number' placeholder='Enter Employee Age' name='empAge' className='form-control' value={empAge} onChange={(e) => setEmpAge(e.target.value)}/>
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Employee Email : </label>
                            <input type='email' placeholder='Enter Employee Email' name='empEmail' className='form-control' value={empEmail} onChange={(e) => setEmpEmail(e.target.value)}/>
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Employee Joining Date : </label>
                            <input type='date' placeholder='Enter Joining Date' name='joiningDate' className='form-control' value={joiningDate} onChange={(e) => setJoiningDate(e.target.value)}/>
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Employee Salary : </label>
                            <input type='number' placeholder='Enter Employee Salary' name='empSal' className='form-control' value={empSal} onChange={(e) => setEmpSal(e.target.value)}/>
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Department Id : </label>
                            <input type='number' placeholder='Enter Department Id' name='deptId' className='form-control' value={deptId} onChange={(e) => setDeptId(e.target.value)}/>
                        </div>
                        {/* <div className='form-group mb-2'>
                            <label className='form-label'>Department Name : </label>
                            <input type='text' placeholder='Enter Department Name' name='deptName' className='form-control' value={deptName} onChange={(e) => setDeptName(e.target.value)}/>
                        </div> */}

                        <button className='btn btn-success mt-2' onClick={(e) => saveOrUpdate(e)}>Submit</button> &nbsp;&nbsp;
                        <Link to='/employees' className="btn btn-danger mt-2">Cancel</Link>
                    </form>
                </div>
            </div>
        </div>
    </div>
  )
}
