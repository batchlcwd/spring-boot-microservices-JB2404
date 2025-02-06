import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import { useAuth } from "./config/authContext";

function App() {
  const { isAuthenticated, setIsAuthenticated, keyCloakInstance } = useAuth();

  console.log(keyCloakInstance?.token);

  return (
    <>
      {isAuthenticated ? (
        <div>
          <div>
            <h1>Welcome to the app</h1>
            <h1>
              username : {keyCloakInstance.tokenParsed.preferred_username}
            </h1>
            <h1>Email : {keyCloakInstance.tokenParsed.email}</h1>

            <button
              onClick={() => {
                keyCloakInstance.logout();
              }}
            >
              Logout
            </button>
          </div>
        </div>
      ) : (
        <div>
          <h1>You are not logged in</h1>
          <button
            onClick={() => {
              keyCloakInstance.login();
            }}
          >
            Login
          </button>
        </div>
      )}
    </>
  );
}

export default App;
