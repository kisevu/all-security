import React, {useState} from 'react';


const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const [jwtToken, setJwtToken] = useState("");
    const [profile, setProfile] = useState(null);


    const handleLogin =  async (e) => {
        e.preventDefault();
        try{
             const response = await fetch("http://localhost:8080/sign-in",{
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({username,password}),
             }  
             );
             if(response.ok){
                const data = await response.json();
                console.log(data);
                setJwtToken(data.jwtToken);
                setMessage("Login was successful");
                // fetchUserProfile(data.jwtToken);
             }else{
                setMessage("Login failed, check your credentials.");
             }

        }catch(error){
            console.log("Error: "+error);
            setMessage("An error has occurred.");
        }

    };
    
    const fetchUserProfile =  async (token) => {
        try{
             const response = await fetch("http://localhost:8080/profile",{
                method: "GET",
                headers: {
                    "Authorization": `Bearer ${jwtToken}`,
                },
             }  
             );
             if(response.ok){
                const data = await response.json();
                console.log(data);
                setProfile(data);
             }else{
                setMessage("Failed to fetch the profile.");
             }

        }catch(error){
            console.log("Error: "+error);
            setMessage("An error has occurred.");
        }

    };


    return (
        <div>
            <h2>Welcome to my Login functionality</h2>
            <form onSubmit={handleLogin}>
                <div>
                    <label>Username</label>
                    <input type='text'
                    value={username}
                    onChange={(e)=> setUsername(e.target.value)}
                    />
                </div>
                
                <div>
                    <label>Password</label>
                    <input type='password'
                    value={password}
                    onChange={(e)=> setPassword(e.target.value)}
                    />
                </div>
                <button type='submit'>Login</button>
            </form>
            {message && <p>{message}</p>}
            {jwtToken && <p>{jwtToken}</p>}
            {profile && (
                <div>
                    <h3>User profile</h3>
                    <p>Username:  {profile.username}</p>
                    <p>roles:  {profile.roles.join(", ")}</p>
                    <p>message:  {profile.message}</p>
                </div>
            )}
        </div>
    );
};


export default Login;