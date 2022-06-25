import React, { useEffect, useState } from 'react';
import DataService from "../service/dataService";


function Admin() {
  const [adminAccessibleData, setadminAccessibleData] = useState(""); 

  useEffect(() => {
    getAdminAccessibleData();                              
  }, []);               
    

  const getAdminAccessibleData = async()=> {
    const result = await DataService.getAdminPermissableData();
    console.log(result); 
    //console.log("testing");
    setadminAccessibleData(result.data);
}
    return (
    <div>
      <h2>
        welcome  admin <br></br>
        {adminAccessibleData}
      </h2>;
    </div>
    )
    
  }

  export default Admin;