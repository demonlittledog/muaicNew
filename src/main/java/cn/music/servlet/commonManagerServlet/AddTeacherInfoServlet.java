package cn.music.servlet.commonManagerServlet;

import cn.music.entity.StudentInfo;
import cn.music.entity.TeaManInfo;
import cn.music.service.commonManagerService.StudentInfoService;
import cn.music.service.commonManagerService.TeaManInfoService;

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
public class AddTeacherInfoServlet extends HttpServlet{
    private TeaManInfo teaManInfo = new TeaManInfo();
    private TeaManInfoService tmis = new TeaManInfoService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String teaManPhone = req.getParameter("teaManPhone");
        String password = req.getParameter("password");
        String teaManName = req.getParameter("teaManName");
        int teaManSex = Integer.parseInt(req.getParameter("teaManSex"));
        Date teaManBirthdate = null;
        try {
            teaManBirthdate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("teaManBirthdate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int status = Integer.parseInt(req.getParameter("status"));

        teaManInfo.setTeaManPhone(teaManPhone);
        teaManInfo.setPassword(password);
        teaManInfo.setTeaManName(teaManName);
        teaManInfo.setTeaManSex(teaManSex);
        teaManInfo.setTeaManBirthdate(teaManBirthdate);
        teaManInfo.setStatus(status);
        teaManInfo.setRoleId(1);

        int count = tmis.addTeacherInfo(teaManInfo);

        if (count>0){
            req.setAttribute("error", "增加成功！");
            req.getRequestDispatcher("queryTeacherInfo.do").forward(req,resp);
        }else {
            req.setAttribute("error", "增加失败！");
            req.getRequestDispatcher("queryTeacherInfo.do").forward(req,resp);
        }
    }
}
