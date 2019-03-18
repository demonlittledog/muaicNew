package cn.music.servlet.matchServlet;

import cn.music.entity.MatchClassInfo;
import cn.music.entity.Page;
import cn.music.entity.TeaManInfo;
import cn.music.service.commonManagerService.TeaManInfoService;
import cn.music.service.matchService.MatchClassInfoService;
import cn.music.service.matchService.MatchRecordsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/11/21.
 */
public class QueryMatchRecordsServlet extends HttpServlet{
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


        //接收老师姓名
        String studentName = req.getParameter("studentName");
        if(null==studentName){
            studentName="";
        }else{
            studentName = studentName.trim();
        }

        int matchClassId = Integer.parseInt(req.getParameter("matchClassId"));
        List<Map<String,Object>> list = mrs.getRecordsNow(matchClassId,currentPageNo,pageSize,studentName);

        //获得总页数
        int totalCount = mrs.getRecordsNowCount(matchClassId,studentName);

        Page pages = new Page();
        pages.setCurrentPageNo(currentPageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);
        int totalPageCount = pages.getTotalPageCount();
        if(totalPageCount==0){
            totalPageCount=1;
        }

        req.setAttribute("list",list);
        req.setAttribute("currentPageNo",currentPageNo);
        req.setAttribute("totalPageCount",totalPageCount);
        req.getRequestDispatcher("jsp/queryMatchRecords.jsp").forward(req,resp);
    }
}
