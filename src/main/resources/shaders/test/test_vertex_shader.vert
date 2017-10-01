#version 450 core

layout(location = 0) in vec3 position;
layout(location = 1) in vec2 texture_coords;

out vec2 p_texture_coords;

uniform mat4 pvmMatrix;

void main(void){
    gl_Position = pvmMatrix * vec4(position, 1.0);
    p_texture_coords = texture_coords;
}