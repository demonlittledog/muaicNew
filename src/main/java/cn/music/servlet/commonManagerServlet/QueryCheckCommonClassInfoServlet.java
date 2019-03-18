package cn.music.servlet.commonManagerServlet;

import cn.music.entity.ClassType;
import cn.music.entity.CommonClassInfo;
import cn.music.entity.TeaManInfo;
import cn.music.service.commonManagerService.ClassTypeService;
import cn.music.service.commonManagerService.CommonClassInfoService;
import cn.music.service.commonManagerService.TeaManInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dell on 2018/11/21.
 */
public class QueryCheckCommonClassInfoServlet extends HttpServlet{
    private ClassTypeService cts = new ClassTypeService();
    private TeaManInfoService tmis = new TeaManInfoService();
    private CommonClassInfoService ccis = new CommonClassInfoService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ClassType> classTypeList =  cts.getAllClassType();
        List<TeaManInfo> teaManInfoList = tmis.getTeaManName();
        int id = Integer.parseInt(req.getParameter("id"));
        CommonClassInfo commonClassInfo = ccis.getCommonClassInfoById(id);
        req.setAttribute("classTypeList",classTypeList);
        req.setAttribute("teaManInfoList",teaManInfoList);
        req.setAttribute("commonClassInfo",commonClassInfo);
        req.getRequestDispatcher("jsp/changeCommonClassInfo.jsp").forward(req,resp);
    }
}
