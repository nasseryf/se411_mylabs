package edu.psu.se411.model;

import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StackTest {

    @Test
    public void testPushAndPop() {
        Stack<String> stringStack = new Stack<>();

        stringStack.push("Z");
        stringStack.push("A");

        assertEquals("A", stringStack.pop());
    }

    @Test
    public void popEmptyStack() {
        Stack<String> stringStack = new Stack<>();

        NoSuchElementException thrown =
                assertThrows(
                        NoSuchElementException.class,
                        () -> stringStack.pop(),
                        "Expected pop from empty Stack to throw, but it didn't"
                );

        assertEquals(
                "Stack is empty, cannot pop",
                thrown.getMessage()
        );
    }

    @Test
    public void testReverseOrder() {
        Stack<String> stringStack = new Stack<>();

        stringStack.push("A");
        stringStack.push("B");
        stringStack.push("C");

        assertEquals("C", stringStack.pop());
        assertEquals("B", stringStack.pop());
        assertEquals("A", stringStack.pop());
    }

    @Test
    public void pushNullAndPopReturnsNull() {
        Stack<String> stack = new Stack<>();

        stack.push(null);

        assertNull(stack.pop());
    }

    @Test
    public void pushEmptyStringAndPopReturnsEmptyString() {
        Stack<String> stack = new Stack<>();

        stack.push("");

        assertEquals("", stack.pop());
    }

    @Test
    public void stackIsEmptyAfterAllItemsArePopped() {
        Stack<String> stack = new Stack<>();

        stack.push("A");
        stack.push("B");

        assertEquals("B", stack.pop());
        assertEquals("A", stack.pop());

        assertThrows(
                NoSuchElementException.class,
                () -> stack.pop()
        );
    }

    @Test
    public void nonPositiveCapacityCreatesUsableStack() {
        Stack<String> stack = new Stack<>(0);

        stack.push("Test");

        assertEquals("Test", stack.pop());
    }
}