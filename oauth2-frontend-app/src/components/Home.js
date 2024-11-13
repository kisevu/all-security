import React from "react";
import { FcGoogle } from "react-icons/fc";
import { BsGithub } from "react-icons/bs";

const Home = () => {

    const googleLogin = () =>{
        window.location.href = 'http://localhost:8081/oauth2/authorization/google';
    };

    const gitHubLogin = () => {
        window.location.href = 'http://localhost:8081/oauth2/authorization/github';
    };

    const centerStyle = {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent:'center',
        height: '100vh',
        background: '#E9967A'
    };


    return (
        <div style={centerStyle}>
            <h2>Welcome to my Customized OAuth</h2>
            
            <button onClick={googleLogin} style={{margin:'10px',fontSize:'16px',
                padding: '10px'
            }}>
                <FcGoogle />  Login With Google
            </button>
    
            <button onClick={gitHubLogin} style={{margin:'10px',fontSize:'16px',
                padding: '10px'}}>
               <BsGithub />  Login With GitHub
            </button>
        </div>
    );
}

export default Home;