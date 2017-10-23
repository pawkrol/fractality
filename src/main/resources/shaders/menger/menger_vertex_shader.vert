#version 450 core

layout(location = 0) in vec3 position;
layout(location = 2) in vec3 normal;
layout(location = 3) in vec3 offset;

out VS_OUT {
    vec3 fragPos;
    vec3 fragNorm;
} vs_out;

uniform mat4 vmMatrix;
uniform mat4 pMatrix;

void main(void){
    vec4 vmPos = vmMatrix * vec4(position + offset, 1);
    gl_Position = pMatrix * vmPos;

    vs_out.fragNorm = normalize(vmMatrix * vec4(normal, 0)).xyz;
    vs_out.fragPos = vmPos.xyz;
}