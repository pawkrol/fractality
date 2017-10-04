#version 450 core

out vec4 outColor;

in VS_OUT {
    vec3 fragPos;
} vs_out;

void main(void){
    float x = vs_out.fragPos.x;
    float y = vs_out.fragPos.y;
    float z = vs_out.fragPos.z;
    outColor = vec4(x, y, z, 1);
}