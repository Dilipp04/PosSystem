package com.dilip.posSystem.configuration;

public class JwtConstant {

    public static final String JWT_SECRET = System.getenv("JWT_SECRET") != null
            ? System.getenv("JWT_SECRET")
            : "qwertyoeijfowehvoewowehowehgowecfnewmdlwefjuweifpwdjwiejdeifhewpdscmkasld";
    public static final String JWT_HEADER = "Authorization";
}
