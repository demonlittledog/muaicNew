package cn.music.servlet.matchServlet;

import cn.music.entity.CommonClassInfo;
import cn.music.entity.MatchClassInfo;
import cn.music.service.commonManagerService.CommonClassInfoService;
import cn.music.service.commonManagerService.TeaManInfoService;
import cn.music.service.matchService.MatchClassInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dell on 2018/11/12.
 */
public class AddMatchClassInfoServlet extends HttpServlet{
    private MatchClassInfoService mcis = new MatchClassInfoService();
    private TeaManInfoService tmis = new TeaManInfoService();
    private MatchClassInfo mci = new MatchClassInfo();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String matchClassName = req.getParameter("matchClassName");
        String teaManName = req.getParameter("teaManName");
        BigDecimal matchCost = new BigDecimal(req.getParameter("matchCost"));
        Date matchClassDate = null;
        try {
            matchClassDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("matchClassDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int matchspan = Integer.parseInt(req.getParameter("matchSpan"));
        int matchStatus = Integer.parseInt(req.getParameter("matchStatus"));
        String mcn = mcis.getMatchClassName(matchClassName);
        int teaManId = tmis.getTeaManId(teaManName);
        if(null==mcn||mcn.trim().length()==0){
                mci.setMatchClassName(matchClassName);
                mci.setTeaManId(teaManId);
                mci.setMatchCost(matchCost);
                mci.setMatchClassDate(matchClassDate);
                mci.setMatchSpan(matchspan);
                mci.setMatchStatus(matchStatus);
               int count = mcis.addMatchClass(mci);
               if(count>0){
                   req.setAttribute("error","班级增加成功！");
                   req.getRequestDispatcher("queryMatchClassInfo.do").forward(req,resp);
               }else {
                   req.setAttribute("error","班级增加失败！");
                   req.getRequestDispatcher("queryMatchTea.do").forward(req,resp);
               }
        }else{
            req.setAttribute("error","班级名称已经存在！");
            req.getRequestDispatcher("queryMatchTea.do").forward(req,resp);
        }
    }
}
