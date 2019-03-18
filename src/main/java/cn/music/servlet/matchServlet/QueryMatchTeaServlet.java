package cn.music.servlet.matchServlet;

import cn.music.entity.TeaManInfo;
import cn.music.service.commonManagerService.TeaManInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dell on 2018/11/12.
 */
public class QueryMatchTeaServlet extends HttpServlet{
    private TeaManInfoService tmis = new TeaManInfoService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TeaManInfo> teaManInfoList = tmis.getTeaManName();
        req.setAttribute("teaManInfoList",teaManInfoList);
        req.getRequestDispatcher("jsp/addMatchClassInfo.jsp").forward(req,resp);
    }
}
