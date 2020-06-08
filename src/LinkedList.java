class LinkedNode {
    int data;
    LinkedNode next;

    public LinkedNode (int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedList {
    public static LinkedNode LinkedList;
    public static int Size;

    public static LinkedNode BuildLinkedList (int List[]) {
        if(List.length < 1) return null;
        LinkedNode HeadNode = null;
        LinkedNode CurrentNode = null;

        for(int i=0; i< List.length; i++) {
            if(i== 0) {
                HeadNode = new LinkedNode(List[i]);
                CurrentNode = HeadNode;
            }  else {
                CurrentNode.next = new LinkedNode(List[i]);
                CurrentNode = CurrentNode.next;
            }
            Size++;
        }
        return HeadNode;
    }

    public static void DisplayLinkedList () {
        LinkedNode CurrentNode = LinkedList;
        while(CurrentNode != null){
            System.out.print(CurrentNode.data+"\n");
            CurrentNode = CurrentNode.next;
        }
    }

    public static LinkedNode Insert(int NewData, int pos){
        LinkedNode HeadNode = LinkedList;
        LinkedNode CurrentNode = HeadNode;

        // if we're inserting in the first position (HEAD NODE)
        if(pos == 1){
            LinkedNode NewHead = new LinkedNode(NewData);
            NewHead.next = HeadNode;
            HeadNode = NewHead;
            Size++;
            return HeadNode;
        }

        int StopAtPos = pos-1;
        int i=1;

        while(CurrentNode != null){
            if(i == StopAtPos){
                System.out.print("Node Before Position : "+pos+" is value "+CurrentNode.data +"\n");

                LinkedNode TempStoreNode = CurrentNode.next;
                CurrentNode.next = new LinkedNode(NewData);
                CurrentNode.next.next = TempStoreNode;
                Size++;
            }

            CurrentNode = CurrentNode.next;
            i++;
        }
        return HeadNode;
    }

    public static void main(String[] args) {
        int List[] = { 10, 11, 12, 13 };

        long startTime = System.nanoTime();

        // build a LinkedList out of an array of integers
        LinkedList = BuildLinkedList(List);
        // insert a new Node in a specified position
        Insert(20, Size);
        // print out the LinkedList ordered
        DisplayLinkedList();
        System.out.print("Size is "+Size+"\n");
        long endTime = System.nanoTime();
        System.out.println("Execution time in milliseconds : " +
                (endTime-startTime) / 1000000);
    }
}