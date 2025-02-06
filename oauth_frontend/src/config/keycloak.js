import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
  realm: "org1",
  url: "http://localhost:8086",
  clientId: "react-app",
  pkceMethod: "S256",
});

export default keycloak;
