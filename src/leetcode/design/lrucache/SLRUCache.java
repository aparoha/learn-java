package leetcode.design.lrucache;

import java.util.HashMap;
import java.util.Map;

//https://medium.com/swlh/design-and-implement-cache-systems-with-least-recently-used-and-least-frequently-used-policies-in-1bedc4c7f328
public class SLRUCache {

    class LRUNode {
        private int key;
        private int value;
        private LRUNode previous;
        private LRUNode next;
        public LRUNode() {

        }
        public LRUNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public class LRUDoubleLinkedList {

        private LRUNode head;
        private LRUNode tail;

        public LRUDoubleLinkedList() {
            this.head = new LRUNode();
            this.tail = new LRUNode();
            this.head.next = this.tail;
            this.tail.previous = this.head;
        }

        public void addToTop(LRUNode node) {
            node.next = head.next;
            head.next.previous = node;
            node.previous = head;
            head.next = node;
        }

        public void removeNode(LRUNode node) {
            node.previous.next = node.next;
            node.next.previous = node.previous;
            node.next = null;
            node.previous = null;
        }

        public LRUNode removeLRUNode() {
            LRUNode target = tail.previous;
            removeNode(target);
            return target;
        }
    }

    private int capacity;
    private int count;
    private Map<Integer, LRUNode> cache;
    private LRUDoubleLinkedList lruDoubleLinkedList;

    public SLRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.cache = new HashMap<>();
        this.lruDoubleLinkedList = new LRUDoubleLinkedList();
    }

    // each time when access the node, we move it to the top
    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        LRUNode node = cache.get(key);
        lruDoubleLinkedList.removeNode(node);
        lruDoubleLinkedList.addToTop(node);
        return node.value;
    }

    public void put(int key, int value) {
        // just need to update value and move it to the top
        if (cache.containsKey(key)) {
            LRUNode node = cache.get(key);
            lruDoubleLinkedList.removeNode(node);
            node.value = value;
            lruDoubleLinkedList.addToTop(node);
        } else {
            // if cache is full, then remove the least recently used node
            if (count == capacity) {
                LRUNode lru = lruDoubleLinkedList.removeLRUNode();
                cache.remove(lru.key);
                count--;
            }
            // add a new node
            LRUNode node = new LRUNode(key, value);
            lruDoubleLinkedList.addToTop(node);
            cache.put(key, node);
            count++;
        }

    }
}
