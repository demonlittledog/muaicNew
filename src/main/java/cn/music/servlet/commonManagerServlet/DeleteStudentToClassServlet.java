package cn.music.servlet.commonManagerServlet;

import cn.music.service.commonManagerService.CommonTuitionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteStudentToClassServlet extends HttpServlet {
    CommonTuitionService cts = new CommonTuitionService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");

        int commonClassId = Integer.parseInt(req.getParameter("commonClassId"));
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        int count = 0;
        count =  cts .deleteStudent(commonClassId,studentId);
        PrintWriter writer = resp.getWriter();
        writer.write(count+"");
        writer.flush();
        writer.close();
    }
}
