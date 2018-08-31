package engine.utils;

import org.joml.Matrix4f;

public class MatrixStack {

    private final int MATRIX_SIZE = 4 * 4;

    private int size;
    private float[] stack;
    private int sp;

    /**
     * Creates stack for 4x4 float matrices
     * @param size initial number of matrices
     */
    public MatrixStack(int size) {
        this.size = size;
        this.sp = 0;

        stack = new float[size * MATRIX_SIZE];
    }

    public void push(Matrix4f matrix) {
        stackSizeCheck();

        matrix.get(stack, sp);
        sp += MATRIX_SIZE;
    }

    public void pop(Matrix4f matrix) {
        sp -= MATRIX_SIZE;
        matrix.set(stack, sp);
    }

    public void clear() {
        sp = 0;
    }

    public int getSize() {
        return size;
    }

    private void stackSizeCheck() {
        if (isStackFull()) {
            resizeStack();
        }
    }

    private boolean isStackFull() {
        return sp >= stack.length;
    }

    private void resizeStack() {
        float[] oldStack = stack;
        int newSize = stack.length * size;

        stack = new float[newSize];

        System.arraycopy(oldStack, 0, stack, 0, oldStack.length);
    }
}
