#version 330

layout (location = 0) in vec2 position;

out vec2 textureCoord;

void main() {
    /*
    Позиция соответствует текстурным координатам по следующей формуле.
    Так сделано, чтобы передавать меньше атрибутов.
    */
    textureCoord = (position + 1) / 2;

    gl_Position = vec4(position, 0, 1);
}
