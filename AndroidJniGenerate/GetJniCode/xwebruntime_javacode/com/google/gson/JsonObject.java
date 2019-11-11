package com.google.gson;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Set;

public final class JsonObject extends JsonElement {
    private final LinkedTreeMap members;

    public JsonObject() {
        super();
        this.members = new LinkedTreeMap();
    }

    public void add(String arg2, JsonElement arg3) {
        JsonNull v3;
        if(arg3 == null) {
            v3 = JsonNull.INSTANCE;
        }

        this.members.put(arg2, v3);
    }

    public void addProperty(String arg1, Boolean arg2) {
        this.add(arg1, this.createJsonElement(arg2));
    }

    public void addProperty(String arg1, Character arg2) {
        this.add(arg1, this.createJsonElement(arg2));
    }

    public void addProperty(String arg1, Number arg2) {
        this.add(arg1, this.createJsonElement(arg2));
    }

    public void addProperty(String arg1, String arg2) {
        this.add(arg1, this.createJsonElement(arg2));
    }

    private JsonElement createJsonElement(Object arg2) {
        JsonPrimitive v2_1;
        if(arg2 == null) {
            JsonNull v2 = JsonNull.INSTANCE;
        }
        else {
            v2_1 = new JsonPrimitive(arg2);
        }

        return ((JsonElement)v2_1);
    }

    public JsonElement deepCopy() {
        return this.deepCopy();
    }

    public JsonObject deepCopy() {
        JsonObject v0 = new JsonObject();
        Iterator v1 = this.members.entrySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            v0.add(((Map$Entry)v2).getKey(), ((Map$Entry)v2).getValue().deepCopy());
        }

        return v0;
    }

    public Set entrySet() {
        return this.members.entrySet();
    }

    public boolean equals(Object arg2) {
        boolean v2;
        if((((JsonObject)arg2)) != this) {
            if(((arg2 instanceof JsonObject)) && (((JsonObject)arg2).members.equals(this.members))) {
                goto label_10;
            }

            v2 = false;
        }
        else {
        label_10:
            v2 = true;
        }

        return v2;
    }

    public JsonElement get(String arg2) {
        return this.members.get(arg2);
    }

    public JsonArray getAsJsonArray(String arg2) {
        return this.members.get(arg2);
    }

    public JsonObject getAsJsonObject(String arg2) {
        return this.members.get(arg2);
    }

    public JsonPrimitive getAsJsonPrimitive(String arg2) {
        return this.members.get(arg2);
    }

    public boolean has(String arg2) {
        return this.members.containsKey(arg2);
    }

    public int hashCode() {
        return this.members.hashCode();
    }

    public Set keySet() {
        return this.members.keySet();
    }

    public JsonElement remove(String arg2) {
        return this.members.remove(arg2);
    }

    public int size() {
        return this.members.size();
    }
}

