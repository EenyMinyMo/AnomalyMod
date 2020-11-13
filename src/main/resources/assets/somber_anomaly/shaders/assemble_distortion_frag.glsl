#version 330

uniform sampler2D colorBufferTexture;
uniform sampler2D distortionBufferTexture;

in vec2 textureCoord;

out vec4 fragColor;

void main() {
    float distortionFactor = 0.1;
    vec4 distortionColor = texture2D(distortionBufferTexture, textureCoord);

    vec2 distortionTexCoord = (textureCoord.xy + (distortionColor.rg - 0.5) * distortionColor.a * distortionFactor);

    fragColor = vec4(texture2D(colorBufferTexture, distortionTexCoord).rgb, 1);

//    fragColor = vec4(distortionColor.rgb, 1.0);
}
