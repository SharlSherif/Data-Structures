import java.util.concurrent.Future;

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
        System.out.print("--LinkedList--\n");
        LinkedNode CurrentNode = LinkedList;
        while (CurrentNode != null) {
            System.out.print(" -> "+CurrentNode.data);
            CurrentNode = CurrentNode.next;
        }
        System.out.print("\n--LinkedList--\n");
    }

    // insert in specified position
    public static void InsertAtPos(int data, int pos) {
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

    public static void DeleteAtPos(int pos) {
        LinkedNode HeadNode = LinkedList;
        // looking to delete the first Node
        if(pos == 0) {
            HeadNode = HeadNode.next;
            Size--;
        }else {
            LinkedNode CurrentNode = HeadNode;
            // Node before the position wanted
            for(int i=0; i<pos-1 && CurrentNode !=null; i++) {
                CurrentNode = CurrentNode.next;
            }
//            System.out.print("Node before deletion position target : "+CurrentNode.data + "\n");
            // make sure that the CurrentNode is not null and it also has a "next" in order to stay safe
            if(CurrentNode != null && CurrentNode.next != null) {
                CurrentNode.next = CurrentNode.next.next;
                Size--;
            } else {
                System.out.print("Position "+pos + " was not found, nothing was deleted\n");
            }
        }

        LinkedList = HeadNode;
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

    public static Boolean isSorted () {
        LinkedNode HeadNode = LinkedList;
        LinkedNode TailNode = null;
        LinkedNode CurrentNode = HeadNode;
        Boolean isSorted = true;

        while (CurrentNode.next != null) {
            TailNode = CurrentNode;
            CurrentNode = CurrentNode.next;
            System.out.print(TailNode.data+" -> "+CurrentNode.data+" -> ");
            if(CurrentNode.data < TailNode.data){
                isSorted = false;
                break;
            }
        }
        if(isSorted) {
            System.out.print("\nLinkedList is sorted\n");
            return true;
        }else {
            System.out.print("\nLinkedList is not sorted\n");
            return false;
        }
    }

    public static void RemoveDuplicates () {
        LinkedNode HeadNode = LinkedList;
        LinkedNode FutureNode = HeadNode.next;
        LinkedNode CurrentNode = HeadNode;

        while (CurrentNode.next != null) {
            if(CurrentNode.data == FutureNode.data){
                CurrentNode.next = FutureNode.next;
                FutureNode = CurrentNode.next;
                System.out.print("\nDuplicate is found and deleted\n");
            }else {
                FutureNode = FutureNode.next;
                CurrentNode = CurrentNode.next;
            }

        }
        LinkedList = HeadNode;
    }

    public static void ReverseUsingArray() {
        int[] A = new int[Size];

        LinkedNode HeadNode = LinkedList;
        LinkedNode CurrentNode = HeadNode;

        for (int i=1; i<Size; i++) {
            A[i] = CurrentNode.data;
            CurrentNode = CurrentNode.next;
        }
        HeadNode = LastNode;
        CurrentNode = HeadNode;
        for (int k=Size-1; k>=0; k--){
            CurrentNode.next = new LinkedNode(A[k]);
            CurrentNode = CurrentNode.next;
        }
        LinkedList = HeadNode;
    }

    public static void ReverseUsingSlidingPointers(){
        LinkedNode HeadNode = LinkedList;
        LinkedNode p = HeadNode;
        LinkedNode q= null, r = null;

        while(p != null) {
            r = q;
            q = p;
            p = p.next;
            q.next = r;
        }
        // because the linked list is reversed, we set the new head to the cached LastNode
        LinkedList = LastNode;
        DisplayLinkedList();
    }

    public static void main(String[] args) {
        int List[] = {4, 5, 8, 10};

        long startTime = System.nanoTime();

        BuildLinkedListFromArray(List);
        ReverseUsingSlidingPointers();
//        InsertAtPos(20, 3);
//        InsertLast(1);
//        InsertLast(10);
//        InsertLast(30);
//        InsertLast(40);
//        InsertLast(50);
//        ReverseUsingArray();
//        RemoveDuplicates();
//        isSorted();
//        DeleteAtPos(10);
        // print out the LinkedList ordered
//        DisplayLinkedList();
        System.out.print("Size is " + Size + "\n");
        System.out.print("Last Node " + LastNode.data + "\n");
        long endTime = System.nanoTime();
        System.out.println("Execution time in milliseconds : " +
                (endTime - startTime) / 1000000);
    }
}