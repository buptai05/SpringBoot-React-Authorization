import axios from "axios";
import authHeader from "./auth-header";
const API_BASE_URL = "http://localhost:4000";

class DataService {
  

  getUserPermissableData() {
    return axios.get(API_BASE_URL + "/user", { headers: authHeader() } );
  }

  getAdminPermissableData() {
    return axios.get(API_BASE_URL + "/admin", { headers: authHeader() } );
  }
}

export default new DataService();