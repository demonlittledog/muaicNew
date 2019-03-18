package cn.music.servlet.commonManagerServlet;

import cn.music.entity.TeaManInfo;
import cn.music.service.commonManagerService.TeaManInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dell on 2018/11/6.
 */
public class ChangePersonalInfoServlet extends HttpServlet{
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
        HttpSession session = req.getSession();
        String teaManPhone = req.getParameter("teaManPhone");
        String teaManName = req.getParameter("teaManName");
        int teaManSex = Integer.parseInt(req.getParameter("teaManSex"));
        Date teaManBirthdate = null;
        try {
            teaManBirthdate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("teaManBirthdate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int teaManId = Integer.parseInt(req.getParameter("teaManId"));
        teaManInfo.setTeaManPhone(teaManPhone);
        teaManInfo.setTeaManName(teaManName);
        teaManInfo.setTeaManSex(teaManSex);
        teaManInfo.setTeaManBirthdate(teaManBirthdate);
        teaManInfo.setTeaManId(teaManId);
        TeaManInfo t = (TeaManInfo)session.getAttribute("teaManInfo");
        teaManInfo.setPassword(t.getPassword());
        teaManInfo.setStatus(t.getStatus());
        teaManInfo.setRoleId(t.getRoleId());
        int count = tmis.updatePersonalInfoInfo(teaManInfo);

        if (count>0){
            session.setAttribute("teaManInfo",teaManInfo);
            req.setAttribute("error", "修改成功！");
            req.getRequestDispatcher("jsp/personalInfo.jsp").forward(req,resp);
        }else {
            req.setAttribute("error", "修改失败！");
            req.getRequestDispatcher("jsp/personalInfo.jsp").forward(req,resp);
        }
    }
}
