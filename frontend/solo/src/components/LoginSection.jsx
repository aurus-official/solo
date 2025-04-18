import "./../styles/AuthSection.css";
import Logo from "./../assets/logo.svg";
import Panel1 from "./../assets/panel1.png";
import { useEffect, useReducer, useRef, useState } from "react";
import { useQuery } from "@tanstack/react-query";

async function loginFn({ queryKey }) {
    const [
        _key,
        userInfo,
        setAttemptToLoginState,
        handleLoginState,
        handleUIState,
    ] = queryKey;
    try {
        const response = await fetch("http://localhost:8080/v1/auth/login", {
            method: "POST",
            headers: {
                Authorization: `Basic ${btoa(`${userInfo.current.username}:${userInfo.current.password}`)}`,
            },
        });
        console.log(response);
        const data = await response.text();
        const response_info_summary = {
            ok: response.ok,
            body: data,
        };

        if (response.ok) {
            handleLoginState(true);
            handleUIState((prev) => {
                return {
                    ...prev,
                    login: false,
                    register: false,
                };
            });
        }

        setAttemptToLoginState(false);
        return response_info_summary;
    } catch (err) {
        console.error(err);
    }
}

export default function LoginSection({ handleLoginState, handleUIState }) {
    const [attemptToLoginState, setAttemptToLoginState] = useState(false);
    const [userInfo, setUserInfo] = useReducer(
        (state, action) => {
            if (action.type == "reset") {
                return {
                    username: "",
                    password: "",
                };
            }

            return {
                ...state,
                [action.type]: action.value,
            };
        },
        {
            username: "",
            password: "",
        }
    );

    const userInfoRef = useRef({
        username: "",
        password: "",
    });

    const { data, status } = useQuery({
        queryKey: [
            "session",
            userInfoRef,
            setAttemptToLoginState,
            handleLoginState,
            handleUIState,
        ],
        queryFn: loginFn,
        enabled: attemptToLoginState,
        staleTime: 1800,
    });

    const handleChange = (event) => {
        setUserInfo({
            type: event.target.name,
            value: event.target.value,
        });
    };

    const handleLogin = (event) => {
        event.preventDefault();

        userInfoRef.current = {
            username: userInfo.username,
            password: userInfo.password,
        };
        setAttemptToLoginState(true);
        setUserInfo({
            type: "reset",
        });
    };

    const handleRegister = (event) => {
        event.preventDefault();
        handleUIState((prev) => {
            return {
                ...prev,
                login: false,
                register: true,
            };
        });
    };

    useEffect(() => {
        document.addEventListener("keypress", (event) => {
            if (event.key == "Enter") {
                event.preventDefault();
                document.getElementById("login-button").click();
            }
        });
        return () => {
            document.removeEventListener("keypress", () => {});
        };
    }, []);

    return (
        <div className="whole-window-container">
            <div className="display-container">
                <img className="panel1" src={Panel1} alt="panel1" />
            </div>
            <div className="division-line"></div>
            <div className="login-container">
                <img className="logo" src={Logo} alt="logo" />
                <div className="interactable-container">
                    {!(status == "pending") && (
                        <div className="status-log-container-error">
                            <p>{!data.ok && data.body}</p>
                        </div>
                    )}
                    <div className="username-container">
                        <input
                            name="username"
                            onChange={handleChange}
                            placeholder="Username"
                            className="username-field"
                            value={userInfo.username}
                        />
                    </div>
                    <div className="password-container">
                        <input
                            name="password"
                            onChange={handleChange}
                            placeholder="Password"
                            className="password-field"
                            value={userInfo.password}
                            type="password"
                        />
                    </div>
                    <button
                        id="login-button"
                        className="login-button"
                        onClick={handleLogin}
                        type="button"
                    >
                        Login
                    </button>
                    <button className="register-link" onClick={handleRegister}>
                        Create An Account
                    </button>
                </div>
            </div>
        </div>
    );
}
