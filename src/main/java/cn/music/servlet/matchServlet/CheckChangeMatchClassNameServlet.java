package cn.music.servlet.matchServlet;

import cn.music.entity.CommonClassInfo;
import cn.music.entity.MatchClassInfo;
import cn.music.service.commonManagerService.CommonClassInfoService;
import cn.music.service.matchService.MatchClassInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dell on 2018/11/21.
 */
public class CheckChangeMatchClassNameServlet extends HttpServlet{
    private MatchClassInfoService mcis = new MatchClassInfoService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String matchClassName = req.getParameter("matchClassName").trim();
        int matchClassId = Integer.parseInt(req.getParameter("matchClassId"));
        String result ="<font color='green'>班级名称可以使用</font>";
        String str = mcis.getMatchClassName(matchClassName);
        if(null!=str){
            //已存在
            result="<font color='red'>班级名称已存在</font>";
            MatchClassInfo matchClassInfo = mcis.getMatchClassById(matchClassId);
            if(matchClassInfo.getMatchClassName().equals(matchClassName)){
                result ="<font color='green'>原先的班级名称</font>";
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
