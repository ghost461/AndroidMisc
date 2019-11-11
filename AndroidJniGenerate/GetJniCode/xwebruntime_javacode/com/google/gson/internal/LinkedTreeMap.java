package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map$Entry;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedTreeMap extends AbstractMap implements Serializable {
    final class com.google.gson.internal.LinkedTreeMap$1 implements Comparator {
        com.google.gson.internal.LinkedTreeMap$1() {
            super();
        }

        public int compare(Comparable arg1, Comparable arg2) {
            return arg1.compareTo(arg2);
        }

        public int compare(Object arg1, Object arg2) {
            return this.compare(((Comparable)arg1), ((Comparable)arg2));
        }
    }

    class EntrySet extends AbstractSet {
        EntrySet(LinkedTreeMap arg1) {
            LinkedTreeMap.this = arg1;
            super();
        }

        public void clear() {
            LinkedTreeMap.this.clear();
        }

        public boolean contains(Object arg2) {
            boolean v2 = !(arg2 instanceof Map$Entry) || LinkedTreeMap.this.findByEntry(((Map$Entry)arg2)) == null ? false : true;
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

            Node v3 = LinkedTreeMap.this.findByEntry(((Map$Entry)arg3));
            if(v3 == null) {
                return 0;
            }

            LinkedTreeMap.this.removeInternal(v3, true);
            return 1;
        }

        public int size() {
            return LinkedTreeMap.this.size;
        }
    }

    final class KeySet extends AbstractSet {
        KeySet(LinkedTreeMap arg1) {
            LinkedTreeMap.this = arg1;
            super();
        }

        public void clear() {
            LinkedTreeMap.this.clear();
        }

        public boolean contains(Object arg2) {
            return LinkedTreeMap.this.containsKey(arg2);
        }

        public Iterator iterator() {
            return new LinkedTreeMapIterator() {
                public Object next() {
                    return this.nextNode().key;
                }
            };
        }

        public boolean remove(Object arg2) {
            boolean v2 = LinkedTreeMap.this.removeInternalByKey(arg2) != null ? true : false;
            return v2;
        }

        public int size() {
            return LinkedTreeMap.this.size;
        }
    }

    abstract class LinkedTreeMapIterator implements Iterator {
        int expectedModCount;
        Node lastReturned;
        Node next;

        LinkedTreeMapIterator(LinkedTreeMap arg1) {
            LinkedTreeMap.this = arg1;
            super();
            this.next = LinkedTreeMap.this.header.next;
            this.lastReturned = null;
            this.expectedModCount = LinkedTreeMap.this.modCount;
        }

        public final boolean hasNext() {
            boolean v0 = this.next != LinkedTreeMap.this.header ? true : false;
            return v0;
        }

        final Node nextNode() {
            Node v0 = this.next;
            if(v0 == LinkedTreeMap.this.header) {
                throw new NoSuchElementException();
            }

            if(LinkedTreeMap.this.modCount != this.expectedModCount) {
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

            LinkedTreeMap.this.removeInternal(this.lastReturned, true);
            this.lastReturned = null;
            this.expectedModCount = LinkedTreeMap.this.modCount;
        }
    }

    final class Node implements Map$Entry {
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
            this.prev = this;
            this.next = this;
        }

        Node(Node arg1, Object arg2, Node arg3, Node arg4) {
            super();
            this.parent = arg1;
            this.key = arg2;
            this.height = 1;
            this.next = arg3;
            this.prev = arg4;
            arg4.next = this;
            arg3.prev = this;
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
    Node root;
    int size;

    static {
        LinkedTreeMap.NATURAL_ORDER = new com.google.gson.internal.LinkedTreeMap$1();
    }

    public LinkedTreeMap() {
        this(LinkedTreeMap.NATURAL_ORDER);
    }

    public LinkedTreeMap(Comparator arg2) {
        super();
        this.size = 0;
        this.modCount = 0;
        this.header = new Node();
        if(arg2 != null) {
        }
        else {
            arg2 = LinkedTreeMap.NATURAL_ORDER;
        }

        this.comparator = arg2;
    }

    public void clear() {
        // Method was not decompiled
    }

    public boolean containsKey(Object arg1) {
        boolean v1 = this.findByObject(arg1) != null ? true : false;
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

    Node find(Object arg7, boolean arg8) {
        Node v0_2;
        int v4;
        Comparator v0 = this.comparator;
        Node v1 = this.root;
        Node v2 = null;
        if(v1 != null) {
            Object v3 = v0 == LinkedTreeMap.NATURAL_ORDER ? arg7 : v2;
            while(true) {
                v4 = v3 != null ? ((Comparable)v3).compareTo(v1.key) : v0.compare(arg7, v1.key);
                if(v4 == 0) {
                    return v1;
                }

                Node v5 = v4 < 0 ? v1.left : v1.right;
                if(v5 == null) {
                    break;
                }

                v1 = v5;
            }
        }
        else {
            v4 = 0;
        }

        if(!arg8) {
            return v2;
        }

        Node v8 = this.header;
        if(v1 == null) {
            if(v0 == LinkedTreeMap.NATURAL_ORDER && !(arg7 instanceof Comparable)) {
                StringBuilder v0_1 = new StringBuilder();
                v0_1.append(arg7.getClass().getName());
                v0_1.append(" is not Comparable");
                throw new ClassCastException(v0_1.toString());
            }

            v0_2 = new Node(v1, arg7, v8, v8.prev);
            this.root = v0_2;
        }
        else {
            v0_2 = new Node(v1, arg7, v8, v8.prev);
            if(v4 < 0) {
                v1.left = v0_2;
            }
            else {
                v1.right = v0_2;
            }

            this.rebalance(v1, true);
        }

        ++this.size;
        ++this.modCount;
        return v0_2;
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
        int v1_1;
        if(arg7) {
            arg6.prev.next = arg6.next;
            arg6.next.prev = arg6.prev;
        }

        Node v7 = arg6.left;
        Node v0 = arg6.right;
        Node v1 = arg6.parent;
        int v2 = 0;
        Node v3 = null;
        if(v7 != null && v0 != null) {
            v7 = v7.height > v0.height ? v7.last() : v0.first();
            this.removeInternal(v7, false);
            v0 = arg6.left;
            if(v0 != null) {
                v1_1 = v0.height;
                v7.left = v0;
                v0.parent = v7;
                arg6.left = v3;
            }
            else {
                v1_1 = 0;
            }

            v0 = arg6.right;
            if(v0 != null) {
                v2 = v0.height;
                v7.right = v0;
                v0.parent = v7;
                arg6.right = v3;
            }

            v7.height = Math.max(v1_1, v2) + 1;
            this.replaceInParent(arg6, v7);
            return;
        }

        if(v7 != null) {
            this.replaceInParent(arg6, v7);
            arg6.left = v3;
        }
        else if(v0 != null) {
            this.replaceInParent(arg6, v0);
            arg6.right = v3;
        }
        else {
            this.replaceInParent(arg6, v3);
        }

        this.rebalance(v1, false);
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
            this.root = arg4;
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

    public int size() {
        return this.size;
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(((Map)this));
    }
}

