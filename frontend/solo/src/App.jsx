import "./styles/App.css";
import LoginSection from "./components/LoginSection";
import { useState } from "react";

function App() {
    const [loginState, setLoginState] = useState(false);

    return (
        <>{!loginState && <LoginSection handleLoginState={setLoginState} />}</>
    );
}

export default App;
