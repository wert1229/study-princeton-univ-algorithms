package com.kdpark.stacknqueue.test;

import com.kdpark.stacknqueue.ArrayQueue;
import com.kdpark.stacknqueue.ArrayStack;
import com.kdpark.stacknqueue.Queue;
import com.kdpark.stacknqueue.Stack;
import com.kdpark.utils.FileUtil;
import edu.princeton.cs.algs4.StdOut;

import java.util.Scanner;

public class TestClient {
    public static void main(String[] args) {
        Scanner sc = FileUtil.getFileReadScanner("stacknqueue/testset.txt");

//        Stack stack = new LinkedListStack();
        Stack stack = new ArrayStack();

        while (sc.hasNext()) {
            String s = sc.next();

            if ("-".equals(s))
                StdOut.print(stack.pop());
            else
                stack.push(s);
        }

        StdOut.print(stack.size());

        sc = FileUtil.getFileReadScanner("stacknqueue/test/testset.txt");

        Queue queue = new ArrayQueue();

        while (sc.hasNext()) {
            String s = sc.next();

            if ("-".equals(s))
                StdOut.print(queue.dequeue());
            else
                queue.enqueue(s);
        }

        StdOut.print(stack.size());
    }
}
