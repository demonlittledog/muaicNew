package cn.music.servlet.matchServlet;

import cn.music.entity.Page;
import cn.music.service.matchService.MatchRecordsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/11/7.
 */
public class QueryHistoryMatchRecordsServlet extends HttpServlet{
    private MatchRecordsService mrs = new MatchRecordsService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        //获得当前页面
        int currentPageNo = 1;
        String str = req.getParameter("currentPageNo");
        if(null!=str&&str.length()>0){
            currentPageNo = Integer.parseInt(str);
        }
        //当前显示页的最大记录数
        int pageSize=5;
        str=req.getParameter("pageSize");
        if(null!=str&&str.length()>0){
            pageSize = Integer.parseInt(str);
        }

        //接收班级名称
        String matchClassName = req.getParameter("matchClassName");
        if(null==matchClassName){
            matchClassName="";
        }else{
            matchClassName = matchClassName.trim();
        }

        //接收学生姓名
        String studentName = req.getParameter("studentName");
        if(null==studentName){
            studentName="";
        }else{
            studentName = studentName.trim();
        }

        //得到开始时间和结束时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year =  sdf.format(new Date());
        String da1 = req.getParameter("da1");
        String da2 = req.getParameter("da2");
        if((null==da1||da1=="") && (null==da2||da2=="")){
            da1=year+"-01-01";
            da2=year+"-12-31";
        }else if ((null==da1||da1=="") && null!=da2){
            da1="";
        }else if ((null==da2||da2=="") && null!=da1){
            da2="2030-12-31";
        }

        List<Map<String,Object>> list = mrs.getHistoryMatchRecords(currentPageNo,pageSize,da1,da2,matchClassName,studentName);

        //获得总页数
        int totalCount = mrs.getHistoryMatchRecordsCount(da1,da2,matchClassName,studentName);

        Page pages = new Page();
        pages.setCurrentPageNo(currentPageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);
        int totalPageCount = pages.getTotalPageCount();
        if(totalPageCount==0){
            totalPageCount=1;
        }

        req.setAttribute("list",list);
        req.setAttribute("da1",da1);
        req.setAttribute("da2",da2);
        req.setAttribute("currentPageNo",currentPageNo);
        req.setAttribute("totalPageCount",totalPageCount);
        req.getRequestDispatcher("jsp/queryHistoryMatchRecords.jsp").forward(req,resp);
    }
}
