import logo from './logo.svg';
import './App.css';
import { useState, useEffect } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter , Routes, Route, Redirect} from "react-router-dom";
import AuthService from "./service/auth-service";

import User from "./components/user";
import Admin from "./components/admin";
import Login from "./components/login";

function App() {

  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    if (user) {
      setCurrentUser(user);
    }

    console.log(currentUser);
  }, []);

  const logOut = () => {
    AuthService.logout();
  };


  if(!currentUser) {
    //return <Login setToken={setToken} />
    return <Login />
  }

  return (
    <BrowserRouter>
    {/* <Navbar /> */}
      <div className="App">
      <Routes>
      {/* {currentUser ? <Redirect to="/"/> : <Login />} */}
      <Route  path="/"  element={< User name={ currentUser.username} />} />
      {/* <Route  path="/login" element={< Login />} /> */}
      <Route  path="/user" element={< User name={ currentUser.username} />} />
      <Route  path="/admin" element={< Admin />} />
      </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
