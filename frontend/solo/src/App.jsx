import "./styles/App.css";
import LoginSection from "./components/LoginSection";
import RegisterSection from "./components/RegisterSection";
import { useState } from "react";

function App() {
    const [uiState, setUIState] = useState({
        login: true,
        register: false,
    });
    const [loginState, setLoginState] = useState(false);

    return (
        <>
            {!loginState && uiState.login && (
                <LoginSection
                    handleLoginState={setLoginState}
                    handleUIState={setUIState}
                />
            )}
            {!loginState && uiState.register && (
                <RegisterSection handleUIState={setUIState} />
            )}
        </>
    );
}

export default App;
