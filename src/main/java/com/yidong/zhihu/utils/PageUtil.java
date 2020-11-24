package com.yidong.zhihu.utils;

import com.yidong.zhihu.entity.PageBean;

public class PageUtil {

    /**
     *  处理分页参数,封装pageBean
     */
    public static Object[] getPageParams(String currentPageStr, String pageSizeStr,int totalCount){
//        //接受参数
//        String currentPageStr = request.getParameter("currentPage");
//        String pageSizeStr = request.getParameter("pageSize");

        //当前页码，如果不传递，则默认为第一页
        int currentPage = 1;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        }
        if(currentPage <= 0 ) {
            currentPage = 1;
        }

        //每页显示条数，如果不传递，默认每页显示5条记录
        int pageSize = 5;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        // 总页数，如果 当前页数大于总页数，则 将当前页设置为 最后页
        int totalPage = (totalCount % pageSize)  == 0 ? totalCount/pageSize : (totalCount/pageSize) + 1;

        if (totalPage != 0 && currentPage >= totalPage){
            currentPage = totalPage;
        }

        //计算开始的记录索引
        int start = (currentPage - 1) * pageSize;

        // 将参数封装为 pageBean 对象
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);

        // 返回pageB对象 和 开始页
        return new Object[]{pageBean,start};
    }


}
