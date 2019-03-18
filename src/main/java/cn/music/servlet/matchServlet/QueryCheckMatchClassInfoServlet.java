package cn.music.servlet.matchServlet;

import cn.music.entity.ClassType;
import cn.music.entity.CommonClassInfo;
import cn.music.entity.MatchClassInfo;
import cn.music.entity.TeaManInfo;
import cn.music.service.commonManagerService.ClassTypeService;
import cn.music.service.commonManagerService.CommonClassInfoService;
import cn.music.service.commonManagerService.TeaManInfoService;
import cn.music.service.matchService.MatchClassInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dell on 2018/11/21.
 */
public class QueryCheckMatchClassInfoServlet extends HttpServlet{
    private TeaManInfoService tmis = new TeaManInfoService();
    private MatchClassInfoService mcis = new MatchClassInfoService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TeaManInfo> teaManInfoList = tmis.getTeaManName();
        int id = Integer.parseInt(req.getParameter("id"));
        MatchClassInfo matchClassInfo = mcis.getMatchClassById(id);
        req.setAttribute("teaManInfoList",teaManInfoList);
        req.setAttribute("matchClassInfo",matchClassInfo);
        req.getRequestDispatcher("jsp/changeMatchClassInfo.jsp").forward(req,resp);
    }
}
