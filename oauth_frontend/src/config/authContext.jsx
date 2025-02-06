import { createContext, useContext, useEffect, useState } from "react";
import keycloak from "./keycloak";

// 1.
export const AuthContext = createContext();

//2.
export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [keyCloakInstance, setKeyCloakInstance] = useState(null);

  useEffect(() => {
    //app start:

    keycloak
      .init({
        onLoad: "check-sso",
        pkceMethod: "S256",
      })
      .then((authenticated) => {
        console.log("user logged in ");
        setIsAuthenticated(authenticated);
        setKeyCloakInstance(keycloak);
      })
      .catch((error) => {
        console.log("user is not logged in");
        console.log(error);
      });
  }, []);

  return (
    <AuthContext.Provider
      value={{
        isAuthenticated,
        setIsAuthenticated,
        keyCloakInstance,
        setKeyCloakInstance,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

//3.

export const useAuth = () => useContext(AuthContext);
