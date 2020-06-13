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
    public LinkedNode LinkedList;
    public LinkedNode LastNode;
    public int Size = 0;

    // convert an array to a LinkedList
    public void BuildLinkedListFromArray(int List[]) {
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
            this.Size++;
        }

        // cache the first Node to store the entire LinkedList
        LinkedList = HeadNode;
        // cache the last Node
        LastNode = CurrentNode;
    }

    // print out the LinkedList in order
    public void DisplayLinkedList() {
        System.out.print("--LinkedList--\n");
        LinkedNode CurrentNode = this.LinkedList;
        while (CurrentNode != null) {
            System.out.print(" -> "+CurrentNode.data);
            CurrentNode = CurrentNode.next;
        }
        System.out.print("\n--LinkedList--\n");
    }

    // insert in specified position
    public void InsertAtPos(int data, int pos) {
        LinkedNode HeadNode = this.LinkedList;
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
                    this.Size++;
                }

                CurrentNode = CurrentNode.next;
                i++;
            }
        }
    }

    public void DeleteAtPos(int pos) {
        LinkedNode HeadNode = LinkedList;
        // looking to delete the first Node
        if(pos == 0) {
            HeadNode = HeadNode.next;
            this.Size--;
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
                this.Size--;
            } else {
                System.out.print("Position "+pos + " was not found, nothing was deleted\n");
            }
        }

        this.LinkedList = HeadNode;
    }

    public void InsertLast(int data) {
        LinkedNode Node = new LinkedNode(data);
        if (LinkedList == null) {
            LinkedList = LastNode = Node;
        } else {
            LastNode.next = Node;
            LastNode = Node;
        }
        this.Size++;
    }

    public Boolean isSorted () {
        LinkedNode HeadNode = this.LinkedList;
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

    public void RemoveDuplicates () {
        LinkedNode HeadNode = this.LinkedList;
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
        this.LinkedList = HeadNode;
    }

    public void ReverseUsingArray() {
        int[] A = new int[this.Size];

        LinkedNode HeadNode = this.LinkedList;
        LinkedNode CurrentNode = HeadNode;

        for (int i=1; i<this.Size; i++) {
            A[i] = CurrentNode.data;
            CurrentNode = CurrentNode.next;
        }
        HeadNode = this.LastNode;
        CurrentNode = HeadNode;
        for (int k=this.Size-1; k>=0; k--){
            CurrentNode.next = new LinkedNode(A[k]);
            CurrentNode = CurrentNode.next;
        }
        this.LinkedList = HeadNode;
    }

    public void ReverseUsingSlidingPointers(){
        LinkedNode HeadNode = this.LinkedList;
        LinkedNode p = HeadNode;
        LinkedNode q= null, r = null;

        while(p != null) {
            r = q;
            q = p;
            p = p.next;
            q.next = r;
        }
        // because the linked list is reversed, we set the new head to the cached LastNode
        this.LinkedList = this.LastNode;
        DisplayLinkedList();
    }

    public void Concatenate(LinkedList List) {
        // make sure that there is a lastNode recorded.
        if(this.LastNode == null) {
            System.out.print("No last node was cached, you probably don't have any items in this LinkedList");
            return;
        }
        // just point the last Node of the LinkedList to the head Node of "List" (another LinkedList)
        this.LastNode.next = List.LinkedList;
        // cache the new last Node for the current LinkedList
        LastNode = List.LastNode;
        // Update Size cache by summing up both LinkedLists sizes
        this.Size += List.Size;
        System.out.print("List is concatenated!");
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        LinkedList One = new LinkedList();
        LinkedList Two = new LinkedList();
        One.BuildLinkedListFromArray(new int[]{1, 2, 3, 4, 5, 6});
//        One.DisplayLinkedList();
        Two.BuildLinkedListFromArray(new int[]{7,8,9,10});
//        Two.DisplayLinkedList();
        One.Concatenate(Two);
        One.DisplayLinkedList();

//        BuildLinkedListFromArray(List);
//        ReverseUsingSlidingPointers();
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
//        System.out.print("Size is " + Size + "\n");
//        System.out.print("Last Node " + LastNode.data + "\n");
        long endTime = System.nanoTime();
        System.out.println("Execution time in milliseconds : " +
                (endTime - startTime) / 1000000);
    }
}