package engine.render;

import engine.event.Event;
import engine.event.EventBus;
import engine.event.EventObserver;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Camera implements EventObserver {//FPS like camera

    private final float mouseSensitivity = 0.001f;
    private final float keySensitivity = 0.05f;
    private final float pitchLimit = 1.5f;

    private Vector2f previousPosition;
    private Vector2f deltaPosition;

    private Vector3f direction;
    private Vector3f position;
    private Vector3f front;
    private Vector3f up;

    private Matrix4f viewMatrix;

    private Vector3f buffMoveVector;

    private float yaw;
    private float pitch;

    private boolean moveFront = false;
    private boolean moveBack = false;
    private boolean moveRight = false;
    private boolean moveLeft = false;

    public Camera() {
        EventBus.getInstance().attach(this);

        previousPosition = new Vector2f();
        deltaPosition = new Vector2f();

        viewMatrix = new Matrix4f();

        direction = new Vector3f();
        position = new Vector3f();
        front = new Vector3f(0, 0, 1);
        up = new Vector3f(0, 1, 0);

        buffMoveVector = new Vector3f();
    }

    @Override
    public void receiveEvent(int event, Object... params) {
        if (event == Event.CURSOR_MOVED) {
            updateMouse( (float) params[0], (float) params[1] );
        } else if (event == Event.KEY_PRESSED) {
            updateKeyboard( Event.KEY_PRESSED, (int) params[0] );
        } else if (event == Event.KEY_RELEASED) {
            updateKeyboard( Event.KEY_RELEASED, (int) params[0] );
        }
    }

    public void update() {
        if (moveFront) {
            position.add(front.x * keySensitivity, front.y * keySensitivity, front.z * keySensitivity);
        }

        if (moveBack) {
            position.sub(front.x * keySensitivity, front.y * keySensitivity, front.z * keySensitivity);
        }

        if (moveRight) {
            position.add(
                    buffMoveVector.set(front)
                            .cross(up)
                            .normalize()
                            .mul(keySensitivity)
            );
        }

        if (moveLeft) {
            position.sub(
                    buffMoveVector.set(front)
                            .cross(up)
                            .normalize()
                            .mul(keySensitivity)
            );
        }
    }

    public Matrix4f getViewMatrix() {
        return viewMatrix.identity().lookAt(
                position,
                direction.set(position.x + front.x, position.y + front.y, position.z + front.z),
                up
        );
    }

    private void updateMouse(float x, float y) {
        deltaPosition.set(
                previousPosition.x - x,
                previousPosition.y - y
        );
        previousPosition.set(x, y);


        pitch += mouseSensitivity * deltaPosition.y;
        yaw += mouseSensitivity * deltaPosition.x;

        if (pitch > pitchLimit) {
            pitch = pitchLimit;
        }

        if (pitch < -pitchLimit) {
            pitch = -pitchLimit;
        }

        front.x = (float) (Math.cos(pitch) * Math.sin(yaw));
        front.y = (float) (Math.sin(pitch));
        front.z = (float) (Math.cos(pitch) * Math.cos(yaw));
        front.normalize();
    }

    private void updateKeyboard(int event, int key) {
        if (key == GLFW.GLFW_KEY_W) {
            moveFront = (event == Event.KEY_PRESSED);
        }

        if (key == GLFW.GLFW_KEY_S) {
            moveBack = (event == Event.KEY_PRESSED);
        }

        if (key == GLFW.GLFW_KEY_A) {
            moveLeft = (event == Event.KEY_PRESSED);
        }

        if (key == GLFW.GLFW_KEY_D) {
            moveRight = (event == Event.KEY_PRESSED);
        }
    }
}
