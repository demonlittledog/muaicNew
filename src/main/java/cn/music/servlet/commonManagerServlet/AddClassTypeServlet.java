package cn.music.servlet.commonManagerServlet;

import cn.music.service.commonManagerService.ClassTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 2018/11/12.
 */
public class AddClassTypeServlet extends HttpServlet{
    private ClassTypeService cts = new ClassTypeService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String classTypeName = req.getParameter("classTypeName").trim();
        String ctn = cts.getClassTypeNameByClassTypeName(classTypeName);
        if(null==ctn||ctn.trim().length()==0){
            int count = cts.addClassType(classTypeName);
            if(count>0){
                req.setAttribute("error","增加成功！");
                req.getRequestDispatcher("queryClassType.do").forward(req,resp);
            }else {
                req.setAttribute("error","增加失败！");
                req.getRequestDispatcher("queryClassType.do").forward(req,resp);
            }
        }else {
            req.setAttribute("error","类型已经存在！");
            req.getRequestDispatcher("queryClassType.do").forward(req,resp);
        }
    }
}
