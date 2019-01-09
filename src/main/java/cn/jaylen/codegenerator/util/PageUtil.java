package cn.jaylen.codegenerator.util;

/**
 * @author ljl
 * @create 2018-09-10 15:57
 * @desc 分页工具类
 **/
public class PageUtil {
    private static ThreadLocal<Integer> pageNum = new ThreadLocal<Integer>();    // 保存第几页
    private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();    // 保存每页记录条数


    public static Integer getPageNum(){
        Integer _pageNum = pageNum.get();
        if (_pageNum == null) {
            return 1;
        } else {
            return _pageNum;
        }
    };

    public static void setPageNum(int _pageNum){
        pageNum.set(_pageNum);
    }

    public static void removePageNum(){
        pageNum.remove();
    }

    public static Integer getPageSize(){
        Integer _pageSize = pageSize.get();
        if (_pageSize == null) {
            return 10;
        } else {
            return _pageSize;
        }
    }

    public static void setPageSize(int _pageSize){
        pageSize.set(_pageSize);
    }

    public static void removePageSize(){
        pageSize.remove();
    }
}
