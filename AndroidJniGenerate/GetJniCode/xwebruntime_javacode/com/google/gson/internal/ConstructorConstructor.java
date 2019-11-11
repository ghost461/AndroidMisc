package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.internal.reflect.ReflectionAccessor;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public final class ConstructorConstructor {
    private final ReflectionAccessor accessor;
    private final Map instanceCreators;

    public ConstructorConstructor(Map arg2) {
        super();
        this.accessor = ReflectionAccessor.getInstance();
        this.instanceCreators = arg2;
    }

    public ObjectConstructor get(TypeToken arg3) {
        Type v0 = arg3.getType();
        Class v3 = arg3.getRawType();
        Object v1 = this.instanceCreators.get(v0);
        if(v1 != null) {
            return new ObjectConstructor(((InstanceCreator)v1), v0) {
                public Object construct() {
                    return this.val$typeCreator.createInstance(this.val$type);
                }
            };
        }

        v1 = this.instanceCreators.get(v3);
        if(v1 != null) {
            return new ObjectConstructor(((InstanceCreator)v1), v0) {
                public Object construct() {
                    return this.val$rawTypeCreator.createInstance(this.val$type);
                }
            };
        }

        ObjectConstructor v1_1 = this.newDefaultConstructor(v3);
        if(v1_1 != null) {
            return v1_1;
        }

        v1_1 = this.newDefaultImplementationConstructor(v0, v3);
        if(v1_1 != null) {
            return v1_1;
        }

        return this.newUnsafeAllocator(v0, v3);
    }

    private ObjectConstructor newDefaultConstructor(Class arg2) {
        try {
            Constructor v2 = arg2.getDeclaredConstructor();
            if(!v2.isAccessible()) {
                this.accessor.makeAccessible(((AccessibleObject)v2));
            }

            return new ObjectConstructor(v2) {
                public Object construct() {
                    StringBuilder v2;
                    Object[] v0 = null;
                    try {
                        return this.val$constructor.newInstance(v0);
                    }
                    catch(IllegalAccessException v0_1) {
                        throw new AssertionError(v0_1);
                    }
                    catch(InvocationTargetException v0_2) {
                        v2 = new StringBuilder();
                        v2.append("Failed to invoke ");
                        v2.append(this.val$constructor);
                        v2.append(" with no args");
                        throw new RuntimeException(v2.toString(), v0_2.getTargetException());
                    }
                    catch(InstantiationException v0_3) {
                        v2 = new StringBuilder();
                        v2.append("Failed to invoke ");
                        v2.append(this.val$constructor);
                        v2.append(" with no args");
                        throw new RuntimeException(v2.toString(), ((Throwable)v0_3));
                    }
                }
            };
        }
        catch(NoSuchMethodException ) {
            return null;
        }
    }

    private ObjectConstructor newDefaultImplementationConstructor(Type arg2, Class arg3) {
        if(Collection.class.isAssignableFrom(arg3)) {
            if(SortedSet.class.isAssignableFrom(arg3)) {
                return new ObjectConstructor() {
                    public Object construct() {
                        return new TreeSet();
                    }
                };
            }

            if(EnumSet.class.isAssignableFrom(arg3)) {
                return new ObjectConstructor(arg2) {
                    public Object construct() {
                        StringBuilder v1;
                        if((this.val$type instanceof ParameterizedType)) {
                            Type v0 = this.val$type.getActualTypeArguments()[0];
                            if((v0 instanceof Class)) {
                                return EnumSet.noneOf(((Class)v0));
                            }

                            v1 = new StringBuilder();
                            v1.append("Invalid EnumSet type: ");
                            v1.append(this.val$type.toString());
                            throw new JsonIOException(v1.toString());
                        }

                        v1 = new StringBuilder();
                        v1.append("Invalid EnumSet type: ");
                        v1.append(this.val$type.toString());
                        throw new JsonIOException(v1.toString());
                    }
                };
            }

            if(Set.class.isAssignableFrom(arg3)) {
                return new ObjectConstructor() {
                    public Object construct() {
                        return new LinkedHashSet();
                    }
                };
            }

            if(Queue.class.isAssignableFrom(arg3)) {
                return new ObjectConstructor() {
                    public Object construct() {
                        return new ArrayDeque();
                    }
                };
            }

            return new ObjectConstructor() {
                public Object construct() {
                    return new ArrayList();
                }
            };
        }

        if(Map.class.isAssignableFrom(arg3)) {
            if(ConcurrentNavigableMap.class.isAssignableFrom(arg3)) {
                return new ObjectConstructor() {
                    public Object construct() {
                        return new ConcurrentSkipListMap();
                    }
                };
            }

            if(ConcurrentMap.class.isAssignableFrom(arg3)) {
                return new ObjectConstructor() {
                    public Object construct() {
                        return new ConcurrentHashMap();
                    }
                };
            }

            if(SortedMap.class.isAssignableFrom(arg3)) {
                return new ObjectConstructor() {
                    public Object construct() {
                        return new TreeMap();
                    }
                };
            }

            if(((arg2 instanceof ParameterizedType)) && !String.class.isAssignableFrom(TypeToken.get(((ParameterizedType)arg2).getActualTypeArguments()[0]).getRawType())) {
                return new ObjectConstructor() {
                    public Object construct() {
                        return new LinkedHashMap();
                    }
                };
            }

            return new ObjectConstructor() {
                public Object construct() {
                    return new LinkedTreeMap();
                }
            };
        }

        return null;
    }

    private ObjectConstructor newUnsafeAllocator(Type arg2, Class arg3) {
        return new ObjectConstructor(arg3, arg2) {
            private final UnsafeAllocator unsafeAllocator;

            public Object construct() {
                try {
                    return this.unsafeAllocator.newInstance(this.val$rawType);
                }
                catch(Exception v0) {
                    StringBuilder v2 = new StringBuilder();
                    v2.append("Unable to invoke no-args constructor for ");
                    v2.append(this.val$type);
                    v2.append(". Registering an InstanceCreator with Gson for this type may fix this problem.");
                    throw new RuntimeException(v2.toString(), ((Throwable)v0));
                }
            }
        };
    }

    public String toString() {
        return this.instanceCreators.toString();
    }
}

