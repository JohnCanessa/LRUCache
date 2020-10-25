import java.util.Scanner;


/**
 * First attempt:
 * Runtime: 457 ms, faster than 5.04% of Java online submissions.
 * Memory Usage: 47.6 MB, less than 13.73% of Java online submissions.
 * 
 * Second attempt:
 * Runtime: 197 ms, faster than 5.04% of Java online submissions.
 * Memory Usage: 48.1 MB, less than 13.73% of Java online submissions.
 * 
 * Third attempt:
 * Runtime: 78 ms, faster than 14.37% of Java online submissions.
 * Memory Usage: 47.2 MB, less than 13.73% of Java online submissions .
 * 
 * Fourth attempt:
 * Runtime: 11 ms, faster than 99.71% of Java online submissions.
 * Memory Usage: 47.6 MB, less than 12.48% of Java online submissions.
 */
public class LRUCache {


    // **** hashmap capacity ****
    final int HM_SIZE = 3001;


    /**
     * Supports the queue, hashmap and nodes used by the LRU cache.
     */
    class QNode {

        // **** class members ****
        int     capacity;
        int     count;
        QNode   next;
        QNode   prev;

        int     key;
        int     value;

        // **** constructor for actual the queue ****
        public QNode(int capacity) {
            this.capacity   = capacity;
            this.count      = 0;

            this.next       = this;
            this.prev       = this;
        }

        // **** constructor for a node in the queue ****
        public QNode(int key, int value) {
            this.key    = key;
            this.value  = value;

            this.next   = null;
            this.prev   = null;
        }

        // **** ****
        @Override
        public String toString() {
            return "(" + key + "," + value + ")"; 
        }
    }

    
    // **** class members ****
    public int      capacity = 0;
    public int      count = 0;

    public QNode[]  arrHM = null;         
    public QNode    queue = null;

    
    // **** used in alternate (slower) implementations ****
    // public HashMap<Integer, Integer>        hm = null;
    // public LinkedList<Integer>              ll = null;
    // public LinkedHashMap<Integer, Integer>  lhm = null;


    /**
     * Constructor
     * Initialize the LRU cache with a positive size capacity.
     */
    public LRUCache(int capacity) {

        // **** sanity check(s) ****
        if (capacity <= 0)
            return;

        // **** initialize class members ****
        this.capacity   = capacity;
        arrHM           = new QNode[HM_SIZE];
        queue           = new QNode(capacity);
    }


    /**
     * Return the value of the key if the key exists, otherwise return -1.
     */
    // public int get0(int key) {

    //     // **** initialization ****
    //     int val = -1;

    //     // **** get the key from the queue ****
    //     if (ll.contains(key)) {

    //         // **** check the location of the key in the queue ****
    //         int index = ll.indexOf(key);

    //         // **** determine if we need to move the key to the front of the queue ****
    //         if (index != ll.size() - 1) {
    //             ll.addLast(ll.remove(index));
    //         }

    //         // **** get the value for the key from the hashmap ****
    //         val = hm.get(key);
    //     }

    //     // **** return the value associated with the key ****
    //     return val;
    // }


    /**
     * Update the value of the key if the key exists.
     * Otherwise, add the key-value pair to the cache.
     * If the number of keys exceeds the capacity from this operation, 
     * evict the least recently used key.
     */
    // public void put0(int key, int value) {
        
    //     // **** remove key from the queue (if needed) ****
    //     if (this.ll.contains(key)) {
    //         ll.remove(ll.indexOf(key));
    //     }

    //     // **** remove from the tail of the queue and hashmap ****
    //     while (this.ll.size() >= this.capacity) {
    //         hm.remove(ll.removeFirst());
    //     }

    //     // **** add key to the queue ****
    //     ll.add(key);

    //     // **** put key and value into the hashmap ****
    //     hm.put(key, value);
    // }


    /**
     * Return the value of the key if the key exists, otherwise return -1.
     * Move the key to the head of the queue.
     */
    // public int get2(int key) {

    //     // **** check if the key is in the queue ****
    //     if (lhm.containsKey(key)) {

    //         // **** remove the key ****
    //         int value = lhm.remove(key);

    //         // **** add the key ****
    //         lhm.put(key, value);

    //         // **** return the value associated with the key ****
    //         return value;
    //     }

    //     // **** key not found ****
    //     return -1;
    // }


    /**
     * Update the value of the key if the key exists.
     * Move the key to the head of the queue.
     * Otherwise, add the key-value pair to the cache.
     * If the number of keys exceeds the capacity from this operation, 
     * evict the least recently used key.
     */
    // public void put2(int key, int value) {
        
    //     // **** if the key exists (remove the key:value pair) ****
    //     if (lhm.containsKey(key)) {
    //         lhm.remove(key);
    //     }

    //     // **** remove the LRU key (if needed) ****
    //     if (lhm.size() >= this.capacity) {

    //         // **** get the LRU key ****
    //         Object obj = lhm.keySet().toArray()[0];
            
    //         // **** remove LRU key:value pair ****
    //         lhm.remove(obj);
    //     }

    //     // **** add the key:value pair ****
    //     lhm.put(key, value);
    // }


    /**
     * 
     */
    // public int get1(int key) {

    //     // **** check in the hashmap does not contain key ****
    //     if (!hm.containsKey(key))
    //         return -1;

    //     // **** value associated with the key ****
    //     int value = hm.get(key);

    //     // ***** ****
    //     int index = ll.indexOf(key);

    //     // **** move key in linked list ****
    //     ll.remove(index);
    //     ll.add(key);

    //     // ???? ????
    //     // System.out.println("get <<< hm: " + hm.toString());
    //     // System.out.println("get <<< ll: " + ll.toString());

    //     // **** return the associated value ****
    //     return value;
    // }


    /**
     * Update the value of the key if the key exists.
     * Otherwise, add the key-value pair to the cache.
     * If the number of keys exceeds the capacity from this operation, 
     * evict the least recently used key.
     */
    // public void put1(int key, int value) {

    //     // **** check if we already have the key ****
    //     if (hm.containsKey(key)) {

    //         // **** get index in the list ****
    //         int index = ll.indexOf(key);

    //         // **** move to last (if needed) ****
    //         if (index !=  ll.size() - 1) {
    //             int k = ll.remove(index);
    //             ll.addLast(k);
    //         }
    //     }

    //     // *** add to linked list and hashmap ****
    //     else {

    //         // **** evict key (if needed) ****
    //         if (ll.size() >= this.capacity) {
    //             int k = ll.remove();
    //             hm.remove(k);
    //         }

    //         // **** add key to linked list ****
    //         ll.add(key);
    //     }

    //     // **** add key:value pair to refresh value ****
    //     hm.put(key, value);
    // }




    /**
     * Enqueue node at the head of the queue.
     * If the queue is full delete the node at the tail of the queue.
     */
    public void enqueue(QNode node) {

        // **** check if the queue is full ****
        if (queue.count >= queue.capacity) {

            // **** remove node at the tail of the queue ****
            QNode n = remove(queue.prev);

            // **** remove node from the hashmap ****
            arrHM[n.key] = null;
        }

        // **** insert the node at the head of the queue ****
        node.next = queue.next;
        node.prev = queue;

        node.next.prev = node;
        queue.next = node;

        // **** increment the queue count ****
        queue.count++;
    }


    /**
     * Dequeue the node at the tail of the queue.
     * If the queue is empty return null.
     */
    public QNode dequeue() {

        // **** check if the queue is empty ****
        if (queue.count == 0)
            return null;

        // **** node being removed and returned ****
        QNode node = queue.prev;
        
        // **** ****
        queue.prev = node.prev;
        node.prev.next = queue;

        // **** decrement the queue count ****
        queue.count--;

        // **** return node ****
        return node;
    }


    /**
     * Remove specified node from the queue.
     */
    public QNode remove(QNode node) {

        // **** sanity check(s) ****
        if (queue.count == 0)
            return null;

        // **** update pointers ****
        node.prev.next = node.next;
        node.next.prev = node.prev;

        // **** decrement the queue count ****
        queue.count--;

        // **** return the node removed from the queue ****
        return node;
    }


    /**
     * Print the contents of the queue.
     * Traverses queue from left (tail) to right (head).
     * Traverses queue from right (head) to left (tail).
     */
    public void printQ() {
        
        // **** ****
        System.out.println("<<< queue: " + queue.count);

        // **** left (tail) to right (head) ****
        System.out.print("<<< tail => ");
        for (QNode p = queue.prev; p != queue; p = p.prev) {
            System.out.print("(" + p.key + "," + p.value + ") ");
            if (p.prev != queue)
                System.out.print("->");
            else
                System.out.println("<= head");
        }

        // **** right (head) to left (tail) ****
        System.out.print("<<< head => ");
        for (QNode p = queue.next; p != queue; p = p.next) {
            System.out.print("(" + p.key + "," + p.value + ") ");
            if (p.next != queue)
                System.out.print("->");
            else
                System.out.println("<= tail");
        }
    }


    /**
     * Get value associated with the specified key from the LRU cache.
     */
    public int get(int key) {

        // **** check if key not in LRU cache ****
        if (arrHM[key] == null)
            return -1;

        // **** get the node ****
        QNode node = arrHM[key];

        // **** check if node NOT head of queue ****
        if (queue.next != node) {

            // **** remove node from the queue ****
            remove(node);

            // **** remove node from the hashmap ****
            arrHM[node.key] = null;

            // **** enqueue node ****
            enqueue(node);

            // **** remove node from the hashmap ****
            arrHM[node.key] = node;
        }

        // **** return the node value ****
        return node.value;
    }


    /**
     * Put key:value pair into the LRU cache.
     */
    public void put(int key, int value) {

        // **** key not in hashmap ****
        if (arrHM[key] == null) {

            // **** new node ****
            QNode node = new QNode(key, value);

            // **** enqueue node (node at head of the queue) ****
            enqueue(node);

            // **** put the node in hashmap ****
            arrHM[key] = node;
        }

        // **** key in hashmap ****
        else {

            // **** get the node from the hashmap ****
            QNode node = arrHM[key];

            // **** remove node from queue ****
            node = remove(node);

            // **** remove the node from the hashmap ****
            arrHM[key] = null;         

            // **** update value (if needed) ****
            if (node.value != value)
                node.value = value;

            // **** enqueue node (node at head of queue) ****
            enqueue(node);

             // **** put the node in hashmap ****
             arrHM[key] = node;           
        }
    }


    /**
     * Test scaffolding.
     */
    public static void main(String[] args) {
        
        // **** open scanner ****
        Scanner sc = new Scanner(System.in);

        // **** read operation(s) ****
        String[] ops = sc.nextLine().trim().split(",");

        // **** read argument(s) ****
        String str = sc.nextLine().trim();

        // **** close scanner ****
        sc.close();

        // **** remove leading and trailing "s ****
        for (int i = 0; i < ops.length; i++) {
            ops[i] = ops[i].substring(1, ops[i].length() - 1);
        }

        // **** split the arguments ****
        String[] strArgs = str.split("\\],\\[");

        // **** take care of the first '[' ****
        if (strArgs[0].startsWith("["))
            strArgs[0] = strArgs[0].substring(1);

        // **** take care of the last ']'s ****
        String s = strArgs[strArgs.length - 1];
        while (s.endsWith("]"))
            s = s.substring(0, s.length() - 1);
        strArgs[strArgs.length - 1] = s;

        // ???? ????
        for (int i = 0; i < ops.length; i++)
            System.out.println("main <<< i: " + i + 
                                " op ==>" + ops[i] + "<== arg ==>" + strArgs[i] + "<==");
        System.out.println();

        // **** LRU cache ****
        LRUCache lruCache = null;

        // **** loop performing all operations ****
        int i = -1;
        for (String op : ops) {

            // **** increment index ****
            i++;

            // **** create and initializa the LRU cache ****
            if (op.equals("LRUCache")) {
                lruCache = new LRUCache(Integer.parseInt(strArgs[i]));

                // **** inform 'create LRU cache' operation completed ****
                System.out.println(null + "");
            }

            // **** put the specified value into the LRU cache ****
            else if (op.equals("put")) {

                // **** ****
                String arg = strArgs[i];

                // **** extract key and value ****
                String[] argStr = arg.split(",");

                // **** put key and val in the LRU cache ****
                lruCache.put(Integer.parseInt(argStr[0]), Integer.parseInt(argStr[1]));

                // **** inform 'put' operation completed ****
                System.out.println(null + "");
            }

            // **** get the specified value from the LRU cache ****
            else if (op.equals("get")) {

                // **** get and display the value from the LRU cache ****
                System.out.println(lruCache.get(Integer.parseInt(strArgs[i])));
            }
        }

    }
}