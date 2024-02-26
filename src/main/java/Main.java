import input.Input;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Main implements Runnable {

    private int width = 1280;
    private int height = 720;

    private Thread thread;
    private boolean running = false;

    private long window;

    public void start() {
        running = true;
        thread = new Thread(this, "Game");
        thread.start();
    }

    @Override
    public void run() {
        init();
        while (running) {
            update();
            render();

            if(glfwWindowShouldClose(window)) {
                running = false;
            }
        }
    }

    private void init() {
        if(!glfwInit()) {
            System.out.println("F");
            return;
        }

        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(width, height, "Flappy", NULL, NULL);

        // TO:DO Solve this
        // Get the primary monitor
        //GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        //glfwSetWindowPos(window, GLFWvidmode.width(vidmode), ypos);

        if(window == NULL) {
            System.out.println("F");
            return;
        }

        glfwSetKeyCallback(window, new Input());

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
        GL.createCapabilities();

        glClearColor(0.0f, 1.0f, 1.0f, 1.0f);
        glEnable(GL_DEPTH_TEST);
    }

    private void update() {
        glfwPollEvents();
        if(Input.keys[GLFW_KEY_SPACE]) {
            System.out.printf("FLAP");
        }
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
        glfwSwapBuffers(window);
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
