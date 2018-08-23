package com.jiujiuwisdom.utils;


import com.jiujiuwisdom.base.BaseListUtil;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

/*
   list集合工具类
 */
public class ListUtil<E> implements BaseListUtil<E> {


    private List<E> list;

    /** 排序后是否需要倒序 **/
    private boolean isReversed;

    public ListUtil(){
        list = new ArrayList<>();
    }

    public ListUtil(List<E> list){
        this(list,false);
    }


    public ListUtil(List<E> list,boolean isReversed){
        HashMap hashMap = new HashMap();
         this.list = list;
         this.isReversed = isReversed;
    }


    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return list.addAll(index,c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean equals(Object o) {
        return list.equals(o);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E remove(int index) {
        return list.get(index);
    }


    @Override
    public E set(int index, E element) {
        return list.set(index,element);
    }

    @Override
    public void add(int index, E element) {
        list.add(index,element);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex,toIndex);
    }

    /**
     *  bean集合排序
     * @param function 比较lamb
     */
    @Override
    public void sort(Function function){

        //判断是否需要倒序
        if (isReversed) {

            list.stream().sorted(Comparator.comparing(function).reversed());

        }else {

            list.stream().sorted(Comparator.comparing(function));
        }
    }


    /**
     * 集合过滤
     * @param predicate lamb 表达式
     * @return
     */
      @Override
      public List<E> filter(Predicate<E> predicate){
         return list.stream().filter(predicate).collect(toList());
      }


    /**
     * 集合去重
     * @return
     */
     @Override
     public List<E> distinct(){
        return list.stream().distinct().collect(toList());
    }

    /**
     * 集合过滤和去重
     * @param predicate lamb 表达式
     * @return
     */
    @Override
    public List<E> filterAnddistinct(Predicate<E> predicate){
        return list.stream().filter(predicate).distinct().collect(toList());
    }


    /**
     * 获取集合多少条数据
     * @param limit 条数
     * @return
     */
    @Override
    public List<E> limit(long limit){
        return list.stream().limit(limit).collect(toList());
    }

    /**
     * 检查集合中的元素是否都匹配条件
     * @param predicate
     * @return
     */
    @Override
    public boolean allMatch(Predicate<E> predicate){
        return list.stream().allMatch(predicate);
    }

    /**
     * 检查集合中的元素是否都不匹配条件
     * @param predicate
     * @return
     */
    @Override
    public boolean noneMatch(Predicate<E> predicate){
        return list.stream().noneMatch(predicate);
    }

    /**
     * 检查集合中的至少一个元素是否匹配条件
     * @param predicate
     * @return
     */
    @Override
    public boolean anyMatch(Predicate<E> predicate){
        return list.stream().anyMatch(predicate);
    }


    /**
     * 随机获取集合元素
     * @return
     */
    @Override
    public E getRandomElement(){

      int index = new Random().nextInt(list.size());

      return list.get(index);
    }

    /**
     * 随机获取集合元素并删除
     * @return
     */
    @Override
    public E getRandomElementAndRemove(){

        final E randomElement = getRandomElement();

        boolean isRemove = list.remove(randomElement);

        if (isRemove) return randomElement;

        return null;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return list.add(e);
    }

    @Override
    public int size() {
        return list.size();
    }
}
