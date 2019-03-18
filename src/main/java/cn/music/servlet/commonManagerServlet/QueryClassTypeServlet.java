package cn.music.servlet.commonManagerServlet;

import cn.music.entity.ClassType;
import cn.music.entity.TeaManInfo;
import cn.music.service.commonManagerService.ClassTypeService;
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
public class QueryClassTypeServlet extends HttpServlet{
    private ClassTypeService cts = new ClassTypeService();
    private TeaManInfoService tmis = new TeaManInfoService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ClassType> classTypeList =  cts.getAllClassType();
        List<TeaManInfo> teaManInfoList = tmis.getTeaManName();
        req.setAttribute("classTypeList",classTypeList);
        req.setAttribute("teaManInfoList",teaManInfoList);
        req.getRequestDispatcher("jsp/addCommonClassInfo.jsp").forward(req,resp);
    }
}
