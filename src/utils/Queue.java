package utils;

import java.util.Arrays;


/**
 * Implantación dunha cola (LIFO) en Java
 * @author xavi
 */
public class Queue {
    private Object[] queue;
    private int top;
  
    public Queue(int max) {
        queue=new Object[max];
        top=0;
    }
    
    public int push(Object obj) {
        if (top>=queue.length) throw new IllegalArgumentException("Queue is Full");
        queue[top]=obj;
        top++;
        return top-1;
    }
    
    public Object pop() {
        if (top==0) return null;
        top--;
        return queue[top];
    }
    
    public Object[] toArray() {
        return Arrays.copyOf(queue,top);
    }
}
