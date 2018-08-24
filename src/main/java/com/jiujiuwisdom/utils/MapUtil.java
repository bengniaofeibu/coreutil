package com.jiujiuwisdom.utils;

import com.jiujiuwisdom.base.BaseMapUtil;
import lombok.Data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
   map 工具类
 */
@Data
public final class MapUtil<k, V> implements BaseMapUtil<k, V> {

    public Map<k, V> map;

    public MapUtil() {
        map = new HashMap<>();
    }

    public MapUtil(Map<k, V> map) {
        this.map = map;
    }


    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V put(k key, V value) {
        return map.put(key, value);
    }

    @Override
    public V remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends k, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<k> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<k, V>> entrySet() {
        return map.entrySet();
    }
}
