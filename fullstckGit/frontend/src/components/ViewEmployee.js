import { useState, useEffect } from "react";
import EmployeeService from "../services/EmployeeService";
function ViewEmployee() {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [emailId, setEmailId] = useState("");
  const [emp, setEmp] = useState([]);
  const [id, setId] = useState("");

  var url = new URL(window.location.href);
  let param =url.searchParams.get("a");

  useEffect(() => {
    EmployeeService.getEmployeeById(param).then(
      (res) => {
        setEmp(res.data);
        setFirstName(res.data.firstName);
        setLastName(res.data.lastName);
        setEmailId(res.data.emailId);
        setId(res.data.id);
      },
      (err) => console.log(err)
    );
  }, []);

  return <div>
      <div className="card col-md-6 offset-md-3 mt-3">
          <h3 className="text-center">View Employee Details</h3>
          <div className="card-body">
              <div className="row">
                  <label>Employee First Name :<b> {firstName} </b></label>
              </div>
              <div className="row">
                  <label>Employee Last Name :<b> {lastName} </b></label>
              </div>
              <div className="row">
                  <label>Employee Email id :<b> {emailId} </b></label>
              </div>
          </div>
      </div>
  </div>;
}
export default ViewEmployee;
