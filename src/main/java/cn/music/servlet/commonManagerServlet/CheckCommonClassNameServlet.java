package cn.music.servlet.commonManagerServlet;

import cn.music.service.commonManagerService.CommonClassInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dell on 2018/11/21.
 */
public class CheckCommonClassNameServlet extends HttpServlet{
    private CommonClassInfoService ccis = new CommonClassInfoService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commonClassName = req.getParameter("commonClassName");
        String result ="<font color='green'>班级姓名可以使用</font>";
        String str = ccis.getCommonClassNameByCommonClassName(commonClassName);
        if(null!=str){
            //已存在
            result="<font color='red'>班级姓名已存在</font>";
        }
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }
}
