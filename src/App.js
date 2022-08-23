import './App.css';
import Footer from './Components/Footer';
import Header from './Components/Header';
import {BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import EmployeeList from './Components/EmployeeList';
import AddEmployee from './Components/AddEmployee';
import EmployeeCount from './Components/EmployeeCount';


function App() {
  return (
      <div>
        <Router>
          <Header/>
            <div className='container'>
              {/* <h1>Rooturaj</h1> */}
              <Switch>
                <Route exact path="/" component={EmployeeList}></Route>
                <Route exact path="/employees" component={EmployeeList}></Route>
                <Route exact path="/add-employee" component={AddEmployee}></Route>
                <Route exact path="/edit-Employee/:eid" component={AddEmployee}></Route>
                <Route exact path="/count" component={EmployeeCount}></Route>
              </Switch>
            </div>
          {/* <Footer/> */}
        </Router> 
      </div>
  );
}

export default App;