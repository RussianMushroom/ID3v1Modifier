package io.github.russianmushroom.ID3v1Modifer;

public class Tuple<K, V> {

    private K key;
    private V val;

    public Tuple(K key, V val) {
        this.key = key;
        this.val = val;
    }

    public K getKey() {
        return key;
    }

    public V getVal() {
        return val;
    }

}
