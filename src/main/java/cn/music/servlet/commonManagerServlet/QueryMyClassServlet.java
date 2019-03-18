package cn.music.servlet.commonManagerServlet;

import cn.music.entity.CommonClassInfo;
import cn.music.entity.TeaManInfo;
import cn.music.service.commonManagerService.CommonClassInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QueryMyClassServlet extends HttpServlet {
    private CommonClassInfoService ccis = new CommonClassInfoService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        if(session.getAttribute("teaManInfo")==null){
            req.getRequestDispatcher("jsp/teaManAccess.jsp").forward(req,resp);
        }else {
            //得到老师姓名
            TeaManInfo teaManInfo = (TeaManInfo) session.getAttribute("teaManInfo");
            List<CommonClassInfo> classList =  ccis.getMyCommonClass(teaManInfo.getTeaManName());
            req.setAttribute("classList",classList);
            req.setAttribute("teaManId",teaManInfo.getTeaManId());
            req.getRequestDispatcher("jsp/queryMyClass.jsp").forward(req,resp);
        }

    }
}
