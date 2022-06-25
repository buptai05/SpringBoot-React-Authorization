import React, { useEffect, useState } from 'react';
import DataService from "../service/dataService";

function User(props) {
  const [userAccessibleData, setUserAccessibleData] = useState(""); 

  useEffect(() => {
    getUserAccessibleData();                              
  }, []);               
    

  const getUserAccessibleData = async()=> {
    const result = await DataService.getUserPermissableData();
    console.log(result); 
    //console.log("testing");
    setUserAccessibleData(result.data);
}
  
  
  return (
    <div>
      <h2>
        welcome  {props.name} <br></br>
         {userAccessibleData}
      </h2>;
    </div>
    )
    
  }

  export default User;