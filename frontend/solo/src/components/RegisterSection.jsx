import "./../styles/AuthSection.css";
import Logo from "./../assets/logo.svg";
import Panel1 from "./../assets/panel1.png";
import { useEffect, useReducer } from "react";
import { useMutation } from "@tanstack/react-query";

export default function RegisterSection({ handleUIState }) {
    const registerMutation = useMutation({
        mutationFn: async (formData) => {
            const response = await fetch(
                "http://127.0.0.1:8080/v1/auth/register",
                {
                    headers: {
                        "Content-Type": "application/json",
                    },
                    method: "POST",
                    body: JSON.stringify(formData),
                }
            );

            const data = await response.text();
            return {
                ok: response.ok,
                body: data,
            };
        },
        onSuccess: (data) => {
            console.log(data);
        },
    });

    const [userInfo, setUserInfo] = useReducer(
        (state, action) => {
            if (action.type == "reset") {
                return {
                    username: "",
                    password: "",
                    confirmPassword: "",
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
            confirmPassword: "",
        }
    );

    const handleChange = (event) => {
        setUserInfo({
            type: event.target.name,
            value: event.target.value,
        });
    };

    const handleClick = (event) => {
        event.preventDefault();
        registerMutation.mutate(userInfo);
        setUserInfo({
            type: "reset",
        });
    };

    const handleUILogin = (event) => {
        event.preventDefault();
        handleUIState((prev) => {
            return {
                ...prev,
                login: true,
                register: false,
            };
        });
    };

    useEffect(() => {
        document.addEventListener("keypress", (event) => {
            if (event.key == "Enter") {
                event.preventDefault();
                document.getElementById("register-button").click();
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
                    {!registerMutation.isIdle && registerMutation.isSuccess && (
                        <div
                            className={
                                registerMutation.data.ok
                                    ? "status-log-container-success"
                                    : "status-log-container-error"
                            }
                        >
                            <p>
                                {registerMutation.data !== undefined &&
                                    registerMutation.data.body}
                            </p>
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
                    <div className="confirm-password-container">
                        <input
                            name="confirmPassword"
                            onChange={handleChange}
                            placeholder="Confirm Password"
                            className="confirm-password-field"
                            value={userInfo.confirmPassword}
                            type="password"
                        />
                    </div>
                    <button
                        id="register-button"
                        className="register-button"
                        onClick={handleClick}
                        type="button"
                    >
                        Register
                    </button>
                    <button className="login-link" onClick={handleUILogin}>
                        Already Have An Account
                    </button>
                </div>
            </div>
        </div>
    );
}
