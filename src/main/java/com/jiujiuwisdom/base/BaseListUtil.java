package com.jiujiuwisdom.base;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface BaseListUtil<E> extends List<E> {

    /**
     * bean集合排序
     *
     * @param function 比较lamb
     */
    void sort(Function function);


    /**
     * 集合过滤
     *
     * @param predicate lamb 表达式
     * @return
     */
    List<E> filter(Predicate<E> predicate);


    /**
     * 集合去重
     *
     * @return
     */
    List<E> distinct();

    /**
     * 集合过滤和去重
     *
     * @param predicate lamb 表达式
     * @return
     */
    public List<E> filterAnddistinct(Predicate<E> predicate);


    /**
     * 获取集合多少条数据
     *
     * @param limit 条数
     * @return
     */
    public List<E> limit(long limit);

    /**
     * 检查集合中的元素是否都匹配条件
     *
     * @param predicate
     * @return
     */
    public boolean allMatch(Predicate<E> predicate);

    /**
     * 检查集合中的元素是否都不匹配条件
     *
     * @param predicate
     * @return
     */
    boolean noneMatch(Predicate<E> predicate);

    /**
     * 检查集合中的至少一个元素是否匹配条件
     *
     * @param predicate
     * @return
     */
    boolean anyMatch(Predicate<E> predicate);


    /**
     * 随机获取集合元素
     *
     * @return
     */
    E getRandomElement();

    /**
     * 随机获取集合元素并删除
     *
     * @return
     */
    E getRandomElementAndRemove();
}
