package utils.graphics;

import utils.filesystem.FileUtils;

import static org.lwjgl.opengl.GL20.*;

/*
*   This class is responsible for the file loading and return it as a OpenGL shader ID
*/
public class ShaderUtils {

    private ShaderUtils(){

    }

    private static int load(String vertPath, String fragPath) {
        String vert = FileUtils.loadAsString(vertPath);
        String frag = FileUtils.loadAsString(fragPath);
        return create(vert, frag);
    }

    public static int create(String vert, String frag) {
        int program = glCreateProgram();
        int vertID = glCreateShader(GL_VERTEX_SHADER);
        int fragID = glCreateShader(GL_FRAGMENT_SHADER);
    }

}
