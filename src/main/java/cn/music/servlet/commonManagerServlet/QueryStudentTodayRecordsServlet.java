package cn.music.servlet.commonManagerServlet;

import cn.music.entity.StudentInfo;
import cn.music.service.commonManagerService.StudentInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class QueryStudentTodayRecordsServlet extends HttpServlet {
    private StudentInfoService sis = new StudentInfoService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        int commonClassId = Integer.parseInt(req.getParameter("commonClassId"));
        List<Map<String,Object>> yList = sis.getStudentByToday(commonClassId);
        List<Map<String,Object>> nList = sis.getStudentByNoToday(commonClassId);
        req.setAttribute("yList",yList);
        req.setAttribute("nList",nList);
        req.getRequestDispatcher("jsp/queryStudentTodayRecord.jsp").forward(req,resp);
    }
}
