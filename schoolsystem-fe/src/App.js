import './App.css';
import Appnavbar from './pages/components/Appnavbar';
import Student from './pages/Student';
import Teacher from './pages/Teacher';
import Home from './pages/Home'
import AddStudent from './pages/AddStudent';
import AddTeacher from './pages/AddTeacher';
import {Route, Routes} from "react-router-dom"

function App() {
  return (
    <div className="App" >
      <Appnavbar/>
      <div>
        <Routes>
          <Route path='/student' element={<Student/>}/>
          <Route path='/teacher' element={<Teacher/>}/>
          <Route path='/addstudent' element={<AddStudent/>}/>
          <Route path='/addteacher' element={<AddTeacher/>}/>
        </Routes>
      </div>
    </div>
  );
}



export default App;
