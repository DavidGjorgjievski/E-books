// import React from 'react';
// import {useHistory} from 'react-router-dom';
// import EShopService from "../../repository/eshopRepository";
//
// const Login = (props) => {
//
//     const history = useHistory();
//     const [formData, updateFormData] = React.useState({
//         username: "",
//         password: ""
//     })
//
//     const handleChange = (e) => {
//         updateFormData({
//             ...formData,
//             [e.target.name]: e.target.value.trim()
//         })
//     }
//
//     const onFormSubmit = (e) => {
//         e.preventDefault();
//         EShopService.login(formData.username, formData.password).then(resp => {
//             localStorage.setItem("JWT", resp.data);
//             props.onLogin()
//             history.push("/products");
//         })
//
//     }
//
//     return (
//         <div className="row mt-5">
//             <div className="col-md-5">
//                 <form onSubmit={onFormSubmit}>
//                     <div className="form-group">
//                         <label htmlFor="name">Username</label>
//                         <input type="text"
//                                className="form-control"
//                                name="username"
//                                required
//                                placeholder="Enter username"
//                                onChange={handleChange}
//                         />
//                     </div>
//                     <div className="form-group">
//                         <label htmlFor="price">Password</label>
//                         <input type="password"
//                                className="form-control"
//                                name="password"
//                                placeholder="Enter password"
//                                required
//                                onChange={handleChange}
//                         />
//                     </div>
//                     <button id="submit" type="submit" className="btn btn-primary">Submit</button>
//                 </form>
//             </div>
//         </div>
//     )
// }
//
// export default Login;