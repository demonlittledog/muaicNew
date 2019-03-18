package cn.music.servlet.commonManagerServlet;

import cn.music.entity.TeaManInfo;
import cn.music.service.commonManagerService.TeaManInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dell on 2018/11/9.
 */
public class ChangePwdServlet extends HttpServlet{
    private TeaManInfoService tmis = new TeaManInfoService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();

        String ypwd = req.getParameter("ypwd");
        String password = req.getParameter("qpwd");
        TeaManInfo teaManInfo = (TeaManInfo) session.getAttribute("teaManInfo");
        teaManInfo.setPassword(password);

        int count = tmis.changePwd(teaManInfo,ypwd);
        if(count>0){
            out.println("<script>alert('密码修改成功！');location.href='jsp/content.jsp';</script>");
        }else{
            out.println("<script>alert('密码修改失败！');location.href='jsp/changePwd.jsp';</script>");
        }
    }
}

