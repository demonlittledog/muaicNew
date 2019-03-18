package cn.music.servlet.commonManagerServlet;

import cn.music.entity.CommonTuition;
import cn.music.service.commonManagerService.CommonClassInfoService;
import cn.music.service.commonManagerService.CommonTuitionService;
import cn.music.service.commonManagerService.StudentInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by dell on 2018/11/23.
 */
public class AddStudentToCommonClassServlet extends HttpServlet{
    private CommonTuition commonTuition = new CommonTuition();
    private CommonTuitionService cts = new CommonTuitionService();
    private StudentInfoService sis = new StudentInfoService();
    private CommonClassInfoService ccis = new CommonClassInfoService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        int commonClassId = Integer.parseInt(req.getParameter("commonClassId"));
        BigDecimal commonSurplus = new BigDecimal(0);
        BigDecimal commonAllCost = new BigDecimal(0);
        commonTuition.setCommonClassId(commonClassId);
        commonTuition.setCommonSurplus(commonSurplus);
        commonTuition.setCommonAllCost(commonAllCost);
        String studentIds[] =  req.getParameterValues("chose");
        if(studentIds!=null){
            int studentNum = ccis.getStudentNum(commonClassId);
            int i;
            for(i=0;i<studentIds.length;i++){
                commonTuition.setStudentId(Integer.parseInt(studentIds[i]));
                int count = cts.addStudentToCommonClass(commonTuition);
                sis.updateStudentStatus(1,Integer.parseInt(studentIds[i]));
            }
            studentNum += i;
            ccis.updateCommonClassStudentNum(studentNum,commonClassId);
            req.setAttribute("error", "增加成功！");
            req.getRequestDispatcher("queryAddStudentInfo.do").forward(req,resp);

            System.out.println(i);
        }else {
            req.setAttribute("error", "请选择学生！");
            req.getRequestDispatcher("queryAddStudentInfo.do").forward(req,resp);
        }
    }
}
