package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map$Entry;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedHashTreeMap extends AbstractMap implements Serializable {
    final class com.google.gson.internal.LinkedHashTreeMap$1 implements Comparator {
        com.google.gson.internal.LinkedHashTreeMap$1() {
            super();
        }

        public int compare(Comparable arg1, Comparable arg2) {
            return arg1.compareTo(arg2);
        }

        public int compare(Object arg1, Object arg2) {
            return this.compare(((Comparable)arg1), ((Comparable)arg2));
        }
    }

    final class AvlBuilder {
        private int leavesSkipped;
        private int leavesToSkip;
        private int size;
        private Node stack;

        AvlBuilder() {
            super();
        }

        void add(Node arg6) {
            Node v3;
            Node v1;
            arg6.right = null;
            arg6.parent = null;
            arg6.left = null;
            arg6.height = 1;
            if(this.leavesToSkip > 0 && (this.size & 1) == 0) {
                ++this.size;
                --this.leavesToSkip;
                ++this.leavesSkipped;
            }

            arg6.parent = this.stack;
            this.stack = arg6;
            ++this.size;
            if(this.leavesToSkip > 0 && (this.size & 1) == 0) {
                ++this.size;
                --this.leavesToSkip;
                ++this.leavesSkipped;
            }

            int v6;
            for(v6 = 4; true; v6 *= 2) {
                int v2 = v6 - 1;
                if((this.size & v2) != v2) {
                    return;
                }

                if(this.leavesSkipped == 0) {
                    v1 = this.stack;
                    Node v2_1 = v1.parent;
                    v3 = v2_1.parent;
                    v2_1.parent = v3.parent;
                    this.stack = v2_1;
                    v2_1.left = v3;
                    v2_1.right = v1;
                    v2_1.height = v1.height + 1;
                    v3.parent = v2_1;
                    v1.parent = v2_1;
                }
                else if(this.leavesSkipped == 1) {
                    v1 = this.stack;
                    v3 = v1.parent;
                    this.stack = v3;
                    v3.right = v1;
                    v3.height = v1.height + 1;
                    v1.parent = v3;
                    this.leavesSkipped = 0;
                }
                else if(this.leavesSkipped == 2) {
                    this.leavesSkipped = 0;
                }
            }
        }

        void reset(int arg2) {
            this.leavesToSkip = Integer.highestOneBit(arg2) * 2 - 1 - arg2;
            this.size = 0;
            this.leavesSkipped = 0;
            this.stack = null;
        }

        Node root() {
            Node v0 = this.stack;
            if(v0.parent != null) {
                throw new IllegalStateException();
            }

            return v0;
        }
    }

    class AvlIterator {
        private Node stackTop;

        AvlIterator() {
            super();
        }

        public Node next() {
            Node v0 = this.stackTop;
            Node v1 = null;
            if(v0 == null) {
                return v1;
            }

            Node v2 = v0.parent;
            v0.parent = v1;
            for(v1 = v0.right; true; v1 = v2.left) {
                Node v3 = v2;
                v2 = v1;
                v1 = v3;
                if(v2 == null) {
                    break;
                }

                v2.parent = v1;
            }

            this.stackTop = v1;
            return v0;
        }

        void reset(Node arg3) {
            Node v0 = null;
            while(true) {
                Node v1 = v0;
                v0 = arg3;
                arg3 = v1;
                if(v0 == null) {
                    break;
                }

                v0.parent = arg3;
                arg3 = v0.left;
            }

            this.stackTop = arg3;
        }
    }

    final class EntrySet extends AbstractSet {
        EntrySet(LinkedHashTreeMap arg1) {
            LinkedHashTreeMap.this = arg1;
            super();
        }

        public void clear() {
            LinkedHashTreeMap.this.clear();
        }

        public boolean contains(Object arg2) {
            boolean v2 = !(arg2 instanceof Map$Entry) || LinkedHashTreeMap.this.findByEntry(((Map$Entry)arg2)) == null ? false : true;
            return v2;
        }

        public Iterator iterator() {
            return new LinkedTreeMapIterator() {
                public Object next() {
                    return this.next();
                }

                public Map$Entry next() {
                    return this.nextNode();
                }
            };
        }

        public boolean remove(Object arg3) {
            if(!(arg3 instanceof Map$Entry)) {
                return 0;
            }

            Node v3 = LinkedHashTreeMap.this.findByEntry(((Map$Entry)arg3));
            if(v3 == null) {
                return 0;
            }

            LinkedHashTreeMap.this.removeInternal(v3, true);
            return 1;
        }

        public int size() {
            return LinkedHashTreeMap.this.size;
        }
    }

    final class KeySet extends AbstractSet {
        KeySet(LinkedHashTreeMap arg1) {
            LinkedHashTreeMap.this = arg1;
            super();
        }

        public void clear() {
            LinkedHashTreeMap.this.clear();
        }

        public boolean contains(Object arg2) {
            return LinkedHashTreeMap.this.containsKey(arg2);
        }

        public Iterator iterator() {
            return new LinkedTreeMapIterator() {
                public Object next() {
                    return this.nextNode().key;
                }
            };
        }

        public boolean remove(Object arg2) {
            boolean v2 = LinkedHashTreeMap.this.removeInternalByKey(arg2) != null ? true : false;
            return v2;
        }

        public int size() {
            return LinkedHashTreeMap.this.size;
        }
    }

    abstract class LinkedTreeMapIterator implements Iterator {
        int expectedModCount;
        Node lastReturned;
        Node next;

        LinkedTreeMapIterator(LinkedHashTreeMap arg1) {
            LinkedHashTreeMap.this = arg1;
            super();
            this.next = LinkedHashTreeMap.this.header.next;
            this.lastReturned = null;
            this.expectedModCount = LinkedHashTreeMap.this.modCount;
        }

        public final boolean hasNext() {
            boolean v0 = this.next != LinkedHashTreeMap.this.header ? true : false;
            return v0;
        }

        final Node nextNode() {
            Node v0 = this.next;
            if(v0 == LinkedHashTreeMap.this.header) {
                throw new NoSuchElementException();
            }

            if(LinkedHashTreeMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }

            this.next = v0.next;
            this.lastReturned = v0;
            return v0;
        }

        public final void remove() {
            if(this.lastReturned == null) {
                throw new IllegalStateException();
            }

            LinkedHashTreeMap.this.removeInternal(this.lastReturned, true);
            this.lastReturned = null;
            this.expectedModCount = LinkedHashTreeMap.this.modCount;
        }
    }

    final class Node implements Map$Entry {
        final int hash;
        int height;
        final Object key;
        Node left;
        Node next;
        Node parent;
        Node prev;
        Node right;
        Object value;

        Node() {
            super();
            this.key = null;
            this.hash = -1;
            this.prev = this;
            this.next = this;
        }

        Node(Node arg1, Object arg2, int arg3, Node arg4, Node arg5) {
            super();
            this.parent = arg1;
            this.key = arg2;
            this.hash = arg3;
            this.height = 1;
            this.next = arg4;
            this.prev = arg5;
            arg5.next = this;
            arg4.prev = this;
        }

        public boolean equals(Object arg4) {
            boolean v1 = false;
            if((arg4 instanceof Map$Entry)) {
                if(this.key == null) {
                    if(((Map$Entry)arg4).getKey() == null) {
                        goto label_12;
                    }
                }
                else if(this.key.equals(((Map$Entry)arg4).getKey())) {
                label_12:
                    if(this.value != null) {
                        if(this.value.equals(((Map$Entry)arg4).getValue())) {
                            goto label_21;
                        }

                        return v1;
                    }
                    else if(((Map$Entry)arg4).getValue() == null) {
                    }
                    else {
                        return v1;
                    }

                label_21:
                    v1 = true;
                }

                return v1;
            }

            return 0;
        }

        public Node first() {
            Node v0 = this.left;
            Node v1 = this;
            while(v0 != null) {
                v1 = v0;
                v0 = v0.left;
            }

            return v1;
        }

        public Object getKey() {
            return this.key;
        }

        public Object getValue() {
            return this.value;
        }

        public int hashCode() {
            int v1 = 0;
            int v0 = this.key == null ? 0 : this.key.hashCode();
            if(this.value == null) {
            }
            else {
                v1 = this.value.hashCode();
            }

            return v0 ^ v1;
        }

        public Node last() {
            Node v0 = this.right;
            Node v1 = this;
            while(v0 != null) {
                v1 = v0;
                v0 = v0.right;
            }

            return v1;
        }

        public Object setValue(Object arg2) {
            Object v0 = this.value;
            this.value = arg2;
            return v0;
        }

        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    private static final Comparator NATURAL_ORDER;
    Comparator comparator;
    private EntrySet entrySet;
    final Node header;
    private KeySet keySet;
    int modCount;
    int size;
    Node[] table;
    int threshold;

    static {
        LinkedHashTreeMap.NATURAL_ORDER = new com.google.gson.internal.LinkedHashTreeMap$1();
    }

    public LinkedHashTreeMap() {
        this(LinkedHashTreeMap.NATURAL_ORDER);
    }

    public LinkedHashTreeMap(Comparator arg2) {
        super();
        this.size = 0;
        this.modCount = 0;
        if(arg2 != null) {
        }
        else {
            arg2 = LinkedHashTreeMap.NATURAL_ORDER;
        }

        this.comparator = arg2;
        this.header = new Node();
        this.table = new Node[16];
        this.threshold = this.table.length / 2 + this.table.length / 4;
    }

    public void clear() {
        Object v1 = null;
        Arrays.fill(this.table, v1);
        this.size = 0;
        ++this.modCount;
        Node v0 = this.header;
        Node v2;
        for(v2 = v0.next; v2 != v0; v2 = v3) {
            Node v3 = v2.next;
            v2.prev = ((Node)v1);
            v2.next = ((Node)v1);
        }

        v0.prev = v0;
        v0.next = v0;
    }

    public boolean containsKey(Object arg1) {
        boolean v1 = this.findByObject(arg1) != null ? true : false;
        return v1;
    }

    private void doubleCapacity() {
        this.table = LinkedHashTreeMap.doubleCapacity(this.table);
        this.threshold = this.table.length / 2 + this.table.length / 4;
    }

    static Node[] doubleCapacity(Node[] arg11) {
        int v0 = arg11.length;
        Node[] v1 = new Node[v0 * 2];
        AvlIterator v2 = new AvlIterator();
        AvlBuilder v3 = new AvlBuilder();
        AvlBuilder v4 = new AvlBuilder();
        int v6;
        for(v6 = 0; v6 < v0; ++v6) {
            Node v7 = arg11[v6];
            if(v7 == null) {
            }
            else {
                v2.reset(v7);
                int v8 = 0;
                int v9 = 0;
                while(true) {
                    Node v10 = v2.next();
                    if(v10 == null) {
                        break;
                    }

                    if((v10.hash & v0) == 0) {
                        ++v8;
                    }
                    else {
                        ++v9;
                    }
                }

                v3.reset(v8);
                v4.reset(v9);
                v2.reset(v7);
                while(true) {
                    v7 = v2.next();
                    if(v7 == null) {
                        break;
                    }

                    if((v7.hash & v0) == 0) {
                        v3.add(v7);
                    }
                    else {
                        v4.add(v7);
                    }
                }

                v7 = null;
                Node v8_1 = v8 > 0 ? v3.root() : v7;
                v1[v6] = v8_1;
                v8 = v6 + v0;
                if(v9 > 0) {
                    v7 = v4.root();
                }

                v1[v8] = v7;
            }
        }

        return v1;
    }

    public Set entrySet() {
        EntrySet v0 = this.entrySet;
        if(v0 != null) {
        }
        else {
            v0 = new EntrySet(this);
            this.entrySet = v0;
        }

        return ((Set)v0);
    }

    private boolean equal(Object arg1, Object arg2) {
        boolean v1;
        if(arg1 != arg2) {
            if(arg1 != null && (arg1.equals(arg2))) {
                goto label_7;
            }

            v1 = false;
        }
        else {
        label_7:
            v1 = true;
        }

        return v1;
    }

    Node find(Object arg13, boolean arg14) {
        Node v14;
        int v11;
        Node v10;
        Comparator v0 = this.comparator;
        Node[] v1 = this.table;
        int v6 = LinkedHashTreeMap.secondaryHash(arg13.hashCode());
        int v2 = v1.length - 1 & v6;
        Node v3 = v1[v2];
        Node v4 = null;
        if(v3 != null) {
            Object v5 = v0 == LinkedHashTreeMap.NATURAL_ORDER ? arg13 : v4;
            while(true) {
                int v7 = v5 != null ? ((Comparable)v5).compareTo(v3.key) : v0.compare(arg13, v3.key);
                if(v7 == 0) {
                    return v3;
                }

                Node v8 = v7 < 0 ? v3.left : v3.right;
                if(v8 == null) {
                    v10 = v3;
                    v11 = v7;
                    break;
                }

                v3 = v8;
            }
        }
        else {
            v10 = v3;
            v11 = 0;
        }

        if(!arg14) {
            return v4;
        }

        Node v7_1 = this.header;
        if(v10 == null) {
            if(v0 == LinkedHashTreeMap.NATURAL_ORDER && !(arg13 instanceof Comparable)) {
                StringBuilder v0_1 = new StringBuilder();
                v0_1.append(arg13.getClass().getName());
                v0_1.append(" is not Comparable");
                throw new ClassCastException(v0_1.toString());
            }

            v14 = new Node(v10, arg13, v6, v7_1, v7_1.prev);
            v1[v2] = v14;
        }
        else {
            v14 = new Node(v10, arg13, v6, v7_1, v7_1.prev);
            if(v11 < 0) {
                v10.left = v14;
            }
            else {
                v10.right = v14;
            }

            this.rebalance(v10, true);
        }

        int v13 = this.size;
        this.size = v13 + 1;
        if(v13 > this.threshold) {
            this.doubleCapacity();
        }

        ++this.modCount;
        return v14;
    }

    Node findByEntry(Map$Entry arg3) {
        Node v0 = this.findByObject(arg3.getKey());
        int v3 = v0 == null || !this.equal(v0.value, arg3.getValue()) ? 0 : 1;
        if(v3 != 0) {
        }
        else {
            v0 = null;
        }

        return v0;
    }

    Node findByObject(Object arg3) {
        Node v3;
        Node v0 = null;
        if(arg3 != null) {
            try {
                v3 = this.find(arg3, false);
            }
            catch(ClassCastException ) {
                return v0;
            }
        }
        else {
            v3 = v0;
        }

        return v3;
    }

    public Object get(Object arg1) {
        Node v1 = this.findByObject(arg1);
        return v1 != null ? v1.value : null;
    }

    public Set keySet() {
        KeySet v0 = this.keySet;
        if(v0 != null) {
        }
        else {
            v0 = new KeySet(this);
            this.keySet = v0;
        }

        return ((Set)v0);
    }

    public Object put(Object arg2, Object arg3) {
        if(arg2 == null) {
            throw new NullPointerException("key == null");
        }

        Node v2 = this.find(arg2, true);
        Object v0 = v2.value;
        v2.value = arg3;
        return v0;
    }

    private void rebalance(Node arg8, boolean arg9) {
        Node v3_1;
        while(arg8 != null) {
            Node v0 = arg8.left;
            Node v1 = arg8.right;
            int v2 = 0;
            int v3 = v0 != null ? v0.height : 0;
            int v4 = v1 != null ? v1.height : 0;
            int v5 = v3 - v4;
            if(v5 == -2) {
                v0 = v1.left;
                v3_1 = v1.right;
                v3 = v3_1 != null ? v3_1.height : 0;
                if(v0 != null) {
                    v2 = v0.height;
                }

                v2 -= v3;
                if(v2 != -1) {
                    if(v2 == 0 && !arg9) {
                        goto label_32;
                    }

                    this.rotateRight(v1);
                    this.rotateLeft(arg8);
                }
                else {
                label_32:
                    this.rotateLeft(arg8);
                }

                if(!arg9) {
                    goto label_67;
                }
            }
            else {
                if(v5 == 2) {
                    v1 = v0.left;
                    v3_1 = v0.right;
                    v3 = v3_1 != null ? v3_1.height : 0;
                    if(v1 != null) {
                        v2 = v1.height;
                    }

                    v2 -= v3;
                    if(v2 != 1) {
                        if(v2 == 0 && !arg9) {
                            goto label_54;
                        }

                        this.rotateLeft(v0);
                        this.rotateRight(arg8);
                    }
                    else {
                    label_54:
                        this.rotateRight(arg8);
                    }

                    if(!arg9) {
                        goto label_67;
                    }

                    return;
                }

                if(v5 == 0) {
                    arg8.height = v3 + 1;
                    if(!arg9) {
                        goto label_67;
                    }

                    return;
                }

                arg8.height = Math.max(v3, v4) + 1;
                if(arg9) {
                    goto label_67;
                }
            }

            return;
        label_67:
            arg8 = arg8.parent;
        }
    }

    public Object remove(Object arg1) {
        Node v1 = this.removeInternalByKey(arg1);
        return v1 != null ? v1.value : null;
    }

    void removeInternal(Node arg6, boolean arg7) {
        int v2_1;
        Node v0 = null;
        if(arg7) {
            arg6.prev.next = arg6.next;
            arg6.next.prev = arg6.prev;
            arg6.prev = v0;
            arg6.next = v0;
        }

        Node v7 = arg6.left;
        Node v1 = arg6.right;
        Node v2 = arg6.parent;
        int v3 = 0;
        if(v7 != null && v1 != null) {
            v7 = v7.height > v1.height ? v7.last() : v1.first();
            this.removeInternal(v7, false);
            v1 = arg6.left;
            if(v1 != null) {
                v2_1 = v1.height;
                v7.left = v1;
                v1.parent = v7;
                arg6.left = v0;
            }
            else {
                v2_1 = 0;
            }

            v1 = arg6.right;
            if(v1 != null) {
                v3 = v1.height;
                v7.right = v1;
                v1.parent = v7;
                arg6.right = v0;
            }

            v7.height = Math.max(v2_1, v3) + 1;
            this.replaceInParent(arg6, v7);
            return;
        }

        if(v7 != null) {
            this.replaceInParent(arg6, v7);
            arg6.left = v0;
        }
        else if(v1 != null) {
            this.replaceInParent(arg6, v1);
            arg6.right = v0;
        }
        else {
            this.replaceInParent(arg6, v0);
        }

        this.rebalance(v2, false);
        --this.size;
        ++this.modCount;
    }

    Node removeInternalByKey(Object arg2) {
        Node v2 = this.findByObject(arg2);
        if(v2 != null) {
            this.removeInternal(v2, true);
        }

        return v2;
    }

    private void replaceInParent(Node arg3, Node arg4) {
        Node v0 = arg3.parent;
        arg3.parent = null;
        if(arg4 != null) {
            arg4.parent = v0;
        }

        if(v0 == null) {
            this.table[arg3.hash & this.table.length - 1] = arg4;
        }
        else if(v0.left == arg3) {
            v0.left = arg4;
        }
        else {
            v0.right = arg4;
        }
    }

    private void rotateLeft(Node arg6) {
        Node v0 = arg6.left;
        Node v1 = arg6.right;
        Node v2 = v1.left;
        Node v3 = v1.right;
        arg6.right = v2;
        if(v2 != null) {
            v2.parent = arg6;
        }

        this.replaceInParent(arg6, v1);
        v1.left = arg6;
        arg6.parent = v1;
        int v4 = 0;
        int v0_1 = v0 != null ? v0.height : 0;
        int v2_1 = v2 != null ? v2.height : 0;
        arg6.height = Math.max(v0_1, v2_1) + 1;
        int v6 = arg6.height;
        if(v3 != null) {
            v4 = v3.height;
        }

        v1.height = Math.max(v6, v4) + 1;
    }

    private void rotateRight(Node arg6) {
        Node v0 = arg6.left;
        Node v1 = arg6.right;
        Node v2 = v0.left;
        Node v3 = v0.right;
        arg6.left = v3;
        if(v3 != null) {
            v3.parent = arg6;
        }

        this.replaceInParent(arg6, v0);
        v0.right = arg6;
        arg6.parent = v0;
        int v4 = 0;
        int v1_1 = v1 != null ? v1.height : 0;
        int v3_1 = v3 != null ? v3.height : 0;
        arg6.height = Math.max(v1_1, v3_1) + 1;
        int v6 = arg6.height;
        if(v2 != null) {
            v4 = v2.height;
        }

        v0.height = Math.max(v6, v4) + 1;
    }

    private static int secondaryHash(int arg2) {
        arg2 ^= arg2 >>> 20 ^ arg2 >>> 12;
        return arg2 >>> 4 ^ (arg2 >>> 7 ^ arg2);
    }

    public int size() {
        return this.size;
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(((Map)this));
    }
}

