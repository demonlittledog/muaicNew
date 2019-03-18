package cn.music.servlet.commonManagerServlet;

import cn.music.entity.CommonClassInfo;
import cn.music.entity.TeaManInfo;
import cn.music.service.commonManagerService.CommonClassInfoService;
import cn.music.service.commonManagerService.TeaCommonRecordsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TeaCommonRecordsServlet extends HttpServlet {
    private CommonClassInfoService ccis = new CommonClassInfoService();
    private TeaCommonRecordsService tcrs = new TeaCommonRecordsService();
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

            int teaManId = Integer.parseInt(req.getParameter("teaManId"));
            int commonClassId = Integer.parseInt(req.getParameter("commonClassId"));
            int count = tcrs.addTeaCommonRecords(teaManId,commonClassId);
            if(count>0){
                req.setAttribute("error","签到成功！");
                req.getRequestDispatcher("jsp/queryMyClass.jsp").forward(req,resp);
            }else {
                req.setAttribute("error","签到失败！");
                req.getRequestDispatcher("jsp/queryMyClass.jsp").forward(req,resp);
            }
        }

    }
}
