class LinkedNode {
    int data;
    LinkedNode next;

    public LinkedNode(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedList {
    // cache variables for later use
    public static LinkedNode LinkedList;
    public static LinkedNode LastNode;
    public static int Size;

    // convert an array to a LinkedList
    public static void BuildLinkedListFromArray(int List[]) {
        // array is empty, stop
        if (List.length < 1) return;

        LinkedNode HeadNode = null;
        LinkedNode CurrentNode = null;

        for (int i = 0; i < List.length; i++) {
            LinkedNode Node = new LinkedNode(List[i]);
            if (i == 0) {
                HeadNode = Node;
                CurrentNode = HeadNode;
            } else {
                CurrentNode.next = Node;
                CurrentNode = CurrentNode.next;
            }
            Size++;
        }

        // cache the first Node to store the entire LinkedList
        LinkedList = HeadNode;
        // cache the last Node
        LastNode = CurrentNode;
    }

    // print out the LinkedList in order
    public static void DisplayLinkedList() {
        LinkedNode CurrentNode = LinkedList;
        while (CurrentNode != null) {
            System.out.print(CurrentNode.data + "\n");
            CurrentNode = CurrentNode.next;
        }
    }

    // insert in specified position
    public static void Insert(int data, int pos) {
        LinkedNode HeadNode = LinkedList;
        LinkedNode CurrentNode = HeadNode;
        LinkedNode Node = new LinkedNode(data);

        // if we're inserting in the first position (HEAD NODE)
        if (pos == 1) {
            LinkedNode NewHead = Node;
            NewHead.next = HeadNode;
            Size++;
        } else {
            int StopAtPos = pos - 1;
            int i = 1;

            while (CurrentNode != null) {
                if (i == StopAtPos) {
                    System.out.print("Node Before Position : " + pos + " is value " + CurrentNode.data + "\n");

                    LinkedNode TempStoreNode = CurrentNode.next;
                    CurrentNode.next = Node;
                    CurrentNode.next.next = TempStoreNode;
                    Size++;
                }

                CurrentNode = CurrentNode.next;
                i++;
            }
        }
    }

    public static void InsertLast(int data) {
        LinkedNode Node = new LinkedNode(data);
        if (LinkedList == null) {
            LinkedList = LastNode = Node;
        } else {
            LastNode.next = Node;
            LastNode = Node;
        }
        Size++;
    }

    public static void main(String[] args) {
        int List[] = {10, 11, 12, 13};

        long startTime = System.nanoTime();

        BuildLinkedListFromArray(List);

        Insert(20, 3);

        InsertLast(10);
        InsertLast(20);
        InsertLast(30);
        // print out the LinkedList ordered
        DisplayLinkedList();
        System.out.print("Size is " + Size + "\n");
        System.out.print("Last Node " + LastNode.data + "\n");
        long endTime = System.nanoTime();
        System.out.println("Execution time in milliseconds : " +
                (endTime - startTime) / 1000000);
    }
}