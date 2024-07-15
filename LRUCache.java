// Time Complexity : get() : O(1), put() : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Yes. Added return statement in put() when checking capacity was full which was restricting creation of new node

// Approach: Used DLL to add the most recent used node in the beginning and remove the tail node as it is LRU. Used Hashmap fo the constant time lookup where key is the key and value is the actual node. Started by creating class Node with variables next, prev, key, value. Had global variables head and tail 1st representing dummy nodes. In get(), check if key doesn't exist return -1 else remove the node and add in the head then return it's value. In put(), check if key already exists then remove and add in the beginning and then update the value. If it doesn't check if capacity is full then remove the tail node from both DLL & HashMap. Then create a new node add in the beginning and in the map
class LRUCache {

    class Node{
        int key, value;
        Node next, prev;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    Node head, tail;
    int capacity;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    private void addToHead(Node node){

        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;

    }

    private void removeFromTail(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public int get(int key) {

        if(!map.containsKey(key)){
            return -1;
        }

        Node node = map.get(key);

        //As this is most recently used we need to remove from the tail and add in the start of the DLL
        removeFromTail(node);
        addToHead(node);

        return node.value;
    }

    public void put(int key, int value) {

        //case where key already exists but we need to update it's value
        if(map.containsKey(key)){
            Node node = map.get(key);

            //As this is most recently used we need to remove from the tail and add in the    start of the DLL
            removeFromTail(node);
            addToHead(node);

            node.value = value;
            return;
        }

        //Case where capacity is full which means we need to remove tail node
        if(capacity == map.size()){
            Node tailNode = tail.prev; //As we have dummny node it's prev node will have the actual LRU node

            removeFromTail(tailNode);
            map.remove(tailNode.key);
        }

        //Case where capacity is not full and key doesn't exist in the map then we need to add a new key value pair

        Node newNode = new Node(key, value);
        addToHead(newNode);
        map.put(key, newNode);

    }
}