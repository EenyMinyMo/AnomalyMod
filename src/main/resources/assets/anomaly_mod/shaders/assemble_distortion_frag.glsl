#version 330

uniform sampler2D colorTexture;
uniform sampler2D distortionTexture;

in vec2 textureCoord;

out vec4 fragColor;

void main() {
    float distortionFactor = 0.1;
    vec4 distortionColor = texture2D(distortionTexture, textureCoord);

    float xOffset = textureCoord.x + (distortionColor.r - 0.5) * distortionColor.a * distortionFactor;
    float yOffset = textureCoord.y + (distortionColor.g - 0.5) * distortionColor.a * distortionFactor;

    fragColor = vec4(texture2D(colorTexture, vec2(xOffset, yOffset)).rgb, 1);

//    fragColor = vec4(distortionColor.rgb, 1.0);
}
