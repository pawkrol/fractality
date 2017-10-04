#version 450 core

layout(location = 0) in vec3 position;

out VS_OUT {
    vec3 fragPos;
} vs_out;

uniform mat4 pvmMatrix;

void main(void){
    vs_out.fragPos = normalize(position);
    gl_Position = pvmMatrix * vec4(position, 1.0);
}