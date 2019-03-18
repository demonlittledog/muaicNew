package cn.music.servlet.commonManagerServlet;

import cn.music.entity.CommonClassInfo;
import cn.music.service.commonManagerService.CommonClassInfoService;
import cn.music.service.commonManagerService.TeaManInfoService;

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
public class ChangeCommonClassInfoServlet extends HttpServlet{
    private CommonClassInfoService ccis = new CommonClassInfoService();
    private TeaManInfoService tmis = new TeaManInfoService();
    private CommonClassInfo cci = new CommonClassInfo();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String commonClassName = req.getParameter("commonClassName");
        int classTypeId = Integer.parseInt(req.getParameter("classTypeId"));
        String teaManName = req.getParameter("teaManName");
        BigDecimal commonCost = new BigDecimal(req.getParameter("commonCost"));
        int commonSum = Integer.parseInt(req.getParameter("commonSum"));
        Date commonClassDate = null;
        Time commonTime = null;
        try {
            commonClassDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("commonClassDate"));
            Date d = new SimpleDateFormat("HH:mm").parse(req.getParameter("commonTime"));
            commonTime = new Time(d.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int commonWeek = Integer.parseInt(req.getParameter("commonWeek"));
        int commonSpan = Integer.parseInt(req.getParameter("commonSpan"));
        int commonClassId = Integer.parseInt(req.getParameter("commonClassId"));
        int teaManId = tmis.getTeaManId(teaManName);
        cci.setCommonClassName(commonClassName);
        cci.setClassTypeId(classTypeId);
        cci.setTeaManId(teaManId);
        cci.setCommonCost(commonCost);
        cci.setCommonSum(commonSum);
        cci.setCommonClassDate(commonClassDate);
        cci.setCommonWeek(commonWeek);
        cci.setCommonTime(commonTime);
        cci.setCommonSpan(commonSpan);
        cci.setCommonClassId(commonClassId);
       int count = ccis.updateCommonClassInfo(cci);
       if(count>0){
           req.setAttribute("error","班级修改成功！");
           req.getRequestDispatcher("queryCommonClassInfo.do").forward(req,resp);
       }else {
           req.setAttribute("error","班级修改失败！");
           req.getRequestDispatcher("queryClassType.do").forward(req,resp);
       }

    }
}
