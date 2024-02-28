package graphics;

import utils.graphics.ShaderUtils;
import utils.math.Matrix4f;
import utils.math.Vector3f;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

/*
*   This is a representation class
*/
public class Shader {
    private final int ID;
    private Map<String, Integer> locationCache = new HashMap<String, Integer>();

    public Shader(String vertex, String fragment) {
        ID = ShaderUtils.load(vertex, fragment);
    }


    public int getUniform(String name) {
        if(locationCache.containsKey(name))
            return locationCache.get(name);

        int result = glGetUniformLocation(ID, name);

        if(result == -1) {
            System.err.println("Could not find uniform variable '" + name + "'");
        } else {
            locationCache.put(name, result);
        }
        return result;
    }

    // Transmit data from the CPU to GPU
    public void setUniform1i(String name, int value) {
        glUniform1i(getUniform(name), value);
    }

    public void setUniform2f(String name, float x, float y) {
        glUniform2f(getUniform(name), x, y);
    }

    public void setUniform3f(String name, Vector3f vec) {
        glUniform3f(getUniform(name), vec.x, vec.y, vec.z);
    }

    public void setUniformMat4f(String name, Matrix4f matrix) {
        glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
    }

    public void enable(){
        glUseProgram(ID);
    }

    public void disable() {
        glUseProgram(0);
    }
}