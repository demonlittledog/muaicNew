package cn.music.servlet.commonManagerServlet;

import cn.music.entity.StudentInfo;
import cn.music.service.commonManagerService.StudentInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dell on 2018/11/6.
 */
public class ChangeStudentInfoServlet extends HttpServlet{
    private StudentInfo studentInfo = new StudentInfo();
    private StudentInfoService sis = new StudentInfoService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String parentsPhone = req.getParameter("parentsPhone");
        String password = req.getParameter("password");
        String studentName = req.getParameter("studentName");
        String parentsName = req.getParameter("parentsName");
        int studentSex = Integer.parseInt(req.getParameter("studentSex"));
        Date studentBirthdate = null;
        try {
            studentBirthdate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("studentBirthdate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int status = Integer.parseInt(req.getParameter("status"));
        int studentId = Integer.parseInt(req.getParameter("studentId"));

        studentInfo.setParentsPhone(parentsPhone);
        studentInfo.setPassword(password);
        studentInfo.setStudentName(studentName);
        studentInfo.setParentsName(parentsName);
        studentInfo.setStudentSex(studentSex);
        studentInfo.setStudentBirthdate(studentBirthdate);
        studentInfo.setStatus(status);
        studentInfo.setStudentId(studentId);

        int count = sis.updateStudentInfo(studentInfo);

        if (count>0){
            req.setAttribute("error", "修改成功！");
            req.getRequestDispatcher("queryStudentInfo.do").forward(req,resp);
        }else {
            req.setAttribute("error", "修改失败！");
            req.getRequestDispatcher("queryStudentInfo.do").forward(req,resp);
        }
    }
}
