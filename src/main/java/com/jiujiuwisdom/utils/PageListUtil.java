package com.jiujiuwisdom.utils;

import java.util.List;

public class PageListUtil<T> {

   private int totalPage;

   private int pageNo;

   private int pageSize;

   private List<T> pageResult;

   public PageListUtil(List<T> list, int pageNo, int pageSize){
       this.pageResult=list;
       this.pageSize=pageSize;
       this.pageNo=pageNo;
       init(list,pageSize);
   }


   private void init(List<T> list,int pageSize){
       if (list == null ){
           throw  new NullPointerException("list 不能为null");
       }

       if (pageSize <=0){
           throw new IllegalArgumentException("pageSize 必须大于0");
       }
       this.totalPage=getTotalPage(list,pageSize);
   }


   public List<T> getPageResult(){

       int total=this.pageNo== 0?pageSize:(this.pageNo+1)*pageSize;

       int size=(this.pageNo+0)*pageSize;


       if (size >= pageResult.size()) {
           return null;
       }

       if (  total > pageResult.size() ){
           total=pageResult.size();
       }

       return  pageResult.subList(size, total);
   }


    public static int getTotalPage(List list,int pageSize) {

        if ((list.size() % pageSize ) >0){
            return (list.size() / pageSize) + 1;
        }else{
            return (list.size() / pageSize);
        }
    }


    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
