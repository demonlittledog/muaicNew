package cn.music.servlet.commonManagerServlet;

import cn.music.service.commonManagerService.StudentInfoService;
import cn.music.service.commonManagerService.TeaManInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dell on 2018/11/19.
 */
public class CheckChangeTeaManPhoneServlet extends HttpServlet{
    private TeaManInfoService tmis = new TeaManInfoService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teaManPhone = req.getParameter("teaManPhone");
        int teaManId = Integer.parseInt(req.getParameter("teaManId").trim());
        String result ="<font color='green'>手机号可以使用</font>";
        String str = tmis.queryTeaManPhone(teaManPhone);
        if(null!=str){
            //已存在
            result="<font color='red'>手机号已存在</font>";
            str = tmis.getTeaManPhoneById(teaManId);
            if(str.equals(teaManPhone)){
                result ="<font color='green'>原先的手机号</font>";
            }
        }
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }
}
