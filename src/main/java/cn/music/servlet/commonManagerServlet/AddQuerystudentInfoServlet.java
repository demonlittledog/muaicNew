package cn.music.servlet.commonManagerServlet;

import cn.music.entity.Page;
import cn.music.entity.StudentInfo;
import cn.music.service.commonManagerService.StudentInfoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/11/15.
 */
public class AddQuerystudentInfoServlet extends HttpServlet{
    StudentInfoService sis = new StudentInfoService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");
        int cci = Integer.parseInt(req.getParameter("cci"));

        //获得当前页面
        int pageNo = 1;
        String str = req.getParameter("pageNo");
        if(null!=str&&str.length()>0){
            pageNo = Integer.parseInt(str);
        }
        //当前显示页的最大记录数
        int pageSize=5;


        //接收学生名称
        String studentName = req.getParameter("studentName");
        if(null==studentName){
            studentName="";
        }else{
            studentName = studentName.trim();
        }


        List<Map<String,Object>> list = sis.getStudentByTuition(pageNo,pageSize,cci,studentName);
        int total = sis.getStudentByTuitionCount(cci,studentName);

        Page pages = new Page();
        pages.setCurrentPageNo(pageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(total);
        int totalPage = pages.getTotalPageCount();
        if(totalPage==0){
            totalPage=1;
        }

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("total",total);
        map.put("totalPage",totalPage);
        map.put("page",pageNo);
        map.put("pageSize",pageSize);
        map.put("list",list);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String result = gson.toJson(map);
        PrintWriter writer = resp.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


}
