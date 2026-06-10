import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { ShoppingCart } from "lucide-react";
import React, { useState } from "react";

const Login = () => {
    const [formData, setFormData] = useState({
        email: "",
        password: "",
    });
    const [showForgetPassword, setShowForgetPassword] = useState(false);
    const handleSubmit = (e) => {
        e.preventDefault()
        console.log("login....", formData);
    };
    const handleInputChange = (e) => {
        setFormData({ ...formData, [e.target.id]: e.target.value });
    };
    return (
        <div className="min-h-screen bg-gradient-to br from-primary/5 to-primary/10 flex items-center justify-center p-4 relative">
            <div className="w-full max-w-md">
                <div className="text-center mb-8">
                    <div className="flex items-center justify-center space-x-2 mb-4">
                        <div className="w-10 h-10 bg-primary rounded-lg flex items-center justify-center">
                            <ShoppingCart className="w-6 h-6 text-primary-foreground" />
                        </div>
                        <span className="text-2xl font-bold text-foreground">Pos Pro</span>
                    </div>

                    <h1 className="text-2xl font-bold text-foreground">
                        {showForgetPassword ? "Reset Password" : "Welcome Back"}
                    </h1>
                    <p className="text-muted-foreground mt-2">
                        {showForgetPassword
                            ? "Enter the email to receive reset instructions"
                            : "Sign in to your account to continue"}
                    </p>
                </div>

                {!showForgetPassword && (
                    <div className="bg-card rounded-2xl shadow-xl p-8">
                        <form onSubmit={handleSubmit} className="space-y-5">
                            <div className="space-y-3">
                                <Label>Email Address</Label>
                                <Input
                                    placeholder="Enter the Email..."
                                    type="email"
                                    id="email"
                                    value={FormData.email}
                                    onChange={handleInputChange}
                                />
                            </div>
                            <div className="space-y-3">
                                <Label>Password</Label>
                                <Input
                                    placeholder="Enter the password..."
                                    type="password"
                                    id="password"
                                    value={FormData.password}
                                    onChange={handleInputChange}
                                />
                            </div>
                            <div>
                                <Button className="py-4 w-full" type="submit">Login</Button>
                            </div>
                        </form>
                    </div>
                )}
            </div>
        </div>
    );
};

export default Login;
