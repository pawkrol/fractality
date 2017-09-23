#version 400 core

layout(location = 0) in vec3 position;
layout(location = 1) in vec2 texture_coords;

void main(void){
    gl_Position = vec4(position, 1.0);
}