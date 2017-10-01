#version 450 core

layout (binding = 0) uniform sampler2D texture_sampler;

in vec2 p_texture_coords;

out vec4 outColor;

void main(void){
    outColor =  texture(texture_sampler, p_texture_coords);
}