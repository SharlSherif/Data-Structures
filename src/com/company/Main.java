package com.company;
import java.util.*;

class Node{
    int key;
    Node right, left;

    public Node (int item){
        key = item;
        left = right = null;
    }
}

public class Main {
    public static Stack<Node> stack = new Stack<Node>();

    public static Node BuildTree(int[] list, int min, int max, int size){
        // base case, list size is probably less than 1
//        if(index >= size) {
//            return null;
//        }
        int index = 0;
        int currentKey;
        // first key in the list
        Node root = new Node(list[0]);
        Node currentNode = root;

        while(index < size){
            currentKey = currentNode.key;
            index = index + 1;
//            if(stack.isEmpty() == false && stack.firstElement().key== currentKey) {
//                continue;
//            }
            if(index >= size) {
                return root;
            }
            int nextKey = list[index];
            if(nextKey < currentKey){
                Node newNode = new Node(nextKey);
                // should be added to the left side
                currentNode.left = newNode;
                stack.push(currentNode);
                currentNode = newNode;
            }else {
                if(nextKey > currentKey && nextKey < stack.firstElement().key){
                    Node newNode = new Node(nextKey);
                    // should be added to the left side
                    currentNode.right = newNode;
                    stack.push(currentNode);
                    currentNode = newNode;
                }else {
                    currentNode = stack.pop();
                }
            }
        }
        return root;
    }
    public static Node InsertRecord(Node mNode, int item){
        while(mNode != null){
            // item is already inserted
            if(mNode.key == item){
                return mNode;
            }
            // this one is the last available node so we need to save it in order to link a new node to it later
            if(mNode.left == null && mNode.right == null){
                // create a new Node to link to the last found Node (mNode)
                Node newNode = new Node(item);

                if (mNode.key < item){
                    mNode.right = newNode;
                } else { // exact opposite of the above
                    mNode.left = newNode;
                }
            }else {
                // if the item is less than the root key, go to the right side because it will have lower values
                if (mNode.key < item){
                    mNode = mNode.right;
                } else { // exact opposite of the above
                    mNode = mNode.left;
                }
            }

        }
        return mNode;
    }

    public static void printTreeInOrder(Node mNode, String type){
        if(mNode != null) {
            printTreeInOrder(mNode.left, "left");
            System.out.print(type+":"+mNode.key+"\n");
            printTreeInOrder(mNode.right, "right");
        }
    }

    public static void main(String[] args) {
        int i=1;
        int[] arr = {63, 34, 20, 16, 23, 44,70,69,74,86};
        Node OurRoot = BuildTree(arr, Integer.MIN_VALUE, Integer.MAX_VALUE, arr.length);
//        Node root = new Node(arr[0]);
//        Node lastNodeCache = root;
//        while(i<= arr.length-1){
//            lastNodeCache= InsertRecord(lastNodeCache, arr[i]);
//            i++;
//        }
        System.out.print("Tree :- \n");
        printTreeInOrder(OurRoot, "root");
    }
}