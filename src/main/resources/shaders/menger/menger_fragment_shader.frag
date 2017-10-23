#version 450 core

out vec4 outColor;

in VS_OUT {
    vec3 fragPos;
    vec3 fragNorm;
} vs_in;

uniform vec3 lightPos;

void main(void){
    vec3 lightColor = vec3(1);
    vec3 cubeColor = vec3(0, 0.8, 0.6);

    //ambient
    float ambientFactor = .2;
    vec3 ambientColor = lightColor * ambientFactor;

    //diffuse
    vec3 lightDir = lightPos - vs_in.fragPos;
    vec3 toLight = normalize(lightDir);
    float diffuseFactor = max( dot(vs_in.fragNorm, toLight), 0 );
    vec3 diffuseColor = diffuseFactor * lightColor;

    outColor = vec4(cubeColor * (ambientColor + diffuseColor), 1);
}