package cn.music.servlet.commonManagerServlet;

import cn.music.service.commonManagerService.StudentInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dell on 2018/11/19.
 */
public class CheckParentsPhoneServlet extends HttpServlet{
    private StudentInfoService sis = new StudentInfoService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parentsPhone = req.getParameter("parentsPhone");
        String result ="<font color='green'>手机号可以使用</font>";
        String str = sis.queryParentsPhone(parentsPhone);
        if(null!=str){
            //已存在
            result="<font color='red'>手机号已存在</font>";
        }
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }
}
