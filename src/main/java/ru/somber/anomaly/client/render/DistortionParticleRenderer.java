package ru.somber.anomaly.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.particlesystem.particle.IParticle;
import ru.somber.particlesystem.render.GeometryShaderParticleRenderer;
import ru.somber.util.clientutil.opengl.OpenGLUtil;
import ru.somber.util.clientutil.opengl.Shader;
import ru.somber.util.clientutil.opengl.ShaderProgram;
import ru.somber.util.clientutil.opengl.VAO;
import ru.somber.util.clientutil.opengl.texture.Texture;
import ru.somber.util.clientutil.opengl.vbo.VBO;

import java.nio.FloatBuffer;
import java.util.List;

public class DistortionParticleRenderer extends GeometryShaderParticleRenderer {

    private ShaderProgram assembleDistortionShaderProgram;

    private Texture distortionBufferTexture;
    private Texture framebufferCopyTexture;
    private Texture framebufferDefaultTexture;

    private VBO vboDisplayPosition;
    private VAO vaoDistortionRender;


    public DistortionParticleRenderer() {
        super();

        Framebuffer framebuffer = Minecraft.getMinecraft().getFramebuffer();
        distortionBufferTexture = Texture.createTexture(framebuffer.framebufferWidth, framebuffer.framebufferHeight);
        framebufferCopyTexture = Texture.createTexture(framebuffer.framebufferWidth, framebuffer.framebufferHeight);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, distortionBufferTexture.getTextureID());
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebufferCopyTexture.getTextureID());
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

        framebufferDefaultTexture = new Texture(framebuffer.framebufferTexture);
    }

    @Override
    public void preRender(List<IParticle> particleList, float interpolationFactor) {
        super.preRender(particleList, interpolationFactor);

        Framebuffer framebuffer = Minecraft.getMinecraft().getFramebuffer();

        if (distortionBufferTexture.getWidthTexture() != framebuffer.framebufferWidth ||
                distortionBufferTexture.getHeightTexture() != framebuffer.framebufferHeight) {

            Texture.recreateTexture(distortionBufferTexture, framebuffer.framebufferWidth, framebuffer.framebufferHeight);
        }

        if (framebufferCopyTexture.getWidthTexture() != framebuffer.framebufferWidth ||
                framebufferCopyTexture.getHeightTexture() != framebuffer.framebufferHeight) {

            Texture.recreateTexture(framebufferCopyTexture, framebuffer.framebufferWidth, framebuffer.framebufferHeight);
        }

        setFramebufferTexture(GL30.GL_COLOR_ATTACHMENT0, distortionBufferTexture.getTextureID());
        GL11.glClearColor(0.5F, 0.5F, 0.5F, 0);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void postRender(List<IParticle> particleList, float interpolationFactor) {
        super.postRender(particleList, interpolationFactor);

        setFramebufferTexture(GL30.GL_COLOR_ATTACHMENT0, framebufferDefaultTexture.getTextureID());
        //для дебага размытия
        if (! Keyboard.isKeyDown(Keyboard.KEY_Y)) { //рендер кадра с эффектом размытия.

            //сборка эффекта distortion.
            preDistortionRender();
            distortionRender();
            postDistortionRender();
        } else {    //буфера размытия без окружающего мира.
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
            OpenGLUtil.drawTextureOverFramebuffer(distortionBufferTexture.getTextureID());
        }
    }


    @Override
    protected void createShaderProgram() {
        super.createShaderProgram();

        ResourceLocation vertexDistortionShaderLocation = new ResourceLocation(AnomalyMod.MOD_ID, "shaders/assemble_distortion_vert.glsl");
        ResourceLocation fragmentDistortionShaderLocation = new ResourceLocation(AnomalyMod.MOD_ID, "shaders/assemble_distortion_frag.glsl");

        Shader vertDistortionShader = Shader.createShaderObject(GL20.GL_VERTEX_SHADER, vertexDistortionShaderLocation);
        Shader fragDistortionShader = Shader.createShaderObject(GL20.GL_FRAGMENT_SHADER, fragmentDistortionShaderLocation);

        assembleDistortionShaderProgram =  ShaderProgram.createShaderProgram(vertDistortionShader, fragDistortionShader);
    }

    @Override
    protected void createVBOsAndVertAttribVBOs() {
        super.createVBOsAndVertAttribVBOs();


        float[] displayCoordArray = {
                -1, -1,
                 1, -1,
                 1,  1,
                -1,  1
        };

        FloatBuffer displayCoordBuffer = BufferUtils.createFloatBuffer(displayCoordArray.length);
        displayCoordBuffer.put(displayCoordArray);
        displayCoordBuffer.flip();

        vboDisplayPosition = VBO.createVBO();
        vboDisplayPosition.bindBuffer();
        vboDisplayPosition.bufferData(displayCoordBuffer, GL15.GL_STATIC_DRAW);
        vboDisplayPosition.bindNone();
    }

    @Override
    protected void createVAO() {
        super.createVAO();


        vaoDistortionRender = VAO.createVAO();
        VAO.bindVAO(vaoDistortionRender);

        GL20.glEnableVertexAttribArray(0);
        vboDisplayPosition.bindBuffer();
        GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 0, 0);

        VAO.bindNone();

        GL20.glDisableVertexAttribArray(0);
        vboDisplayPosition.bindNone();
    }


    private void prepareDistortionShaderProgramUniform() {
        int uniformLocation;

        uniformLocation = GL20.glGetUniformLocation(assembleDistortionShaderProgram.getShaderProgramID(), "colorBufferTexture");
        GL20.glUniform1i(uniformLocation, 0);

        uniformLocation = GL20.glGetUniformLocation(assembleDistortionShaderProgram.getShaderProgramID(), "distortionBufferTexture");
        GL20.glUniform1i(uniformLocation, 1);
    }

    private void preDistortionRender() {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);

        GL11.glReadBuffer(GL30.GL_COLOR_ATTACHMENT0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebufferCopyTexture.getTextureID());
        GL11.glCopyTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, 0, 0, framebufferCopyTexture.getWidthTexture(), framebufferCopyTexture.getHeightTexture(), 0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebufferCopyTexture.getTextureID());
        GL13.glActiveTexture(GL13.GL_TEXTURE1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, distortionBufferTexture.getTextureID());

        ShaderProgram.useShaderProgram(assembleDistortionShaderProgram);
        prepareDistortionShaderProgramUniform();

        VAO.bindVAO(vaoDistortionRender);
    }

    private void distortionRender() {
        GL11.glDrawArrays(GL11.GL_QUADS, 0, 4);
    }

    private void postDistortionRender() {
        VAO.bindNone();

        ShaderProgram.useNoneShaderProgram();

        GL13.glActiveTexture(GL13.GL_TEXTURE1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    private void setFramebufferTexture(int attachment, int textureID) {
        GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, attachment, GL11.GL_TEXTURE_2D, textureID, 0);
    }

}
