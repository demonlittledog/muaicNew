package cn.music.servlet.commonManagerServlet;

import cn.music.dao.baseDao.BaseDao;
import cn.music.entity.CommonRecords;
import cn.music.entity.CommonTuition;
import cn.music.service.commonManagerService.CommonClassInfoService;
import cn.music.service.commonManagerService.CommonRecordsService;
import cn.music.service.commonManagerService.CommonTuitionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/11/8.
 */
public class StudentFillServlet extends HttpServlet{
    private CommonRecordsService crs = new CommonRecordsService();
    private CommonRecords commonRecords = new CommonRecords();
    private CommonTuitionService cts = new CommonTuitionService();
    private CommonTuition commonTuition = new CommonTuition();
    private CommonClassInfoService ccis = new CommonClassInfoService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        String parentsPhone = req.getParameter("parentsPhone");
        String commonClassName = req.getParameter("commonClassName");
        List<Map<String,Object>> list = crs.getParentsPhoneAndCommonClassName(parentsPhone,commonClassName);
        int studentId = -1;
        int commonClassId = -1;
        BigDecimal commonCost = null;
        BigDecimal commonSurplus = null;
        if(null == list || list.size() ==0 ){
            out.print("<script>alert('家长手机号或普通班级名称错误！');location.href='jsp/studentFill.jsp';</script>");
        }else{
            int count = ccis.getCommonStatus(commonClassName);
            if(count==1){
                for (Map<String,Object> map:list){
                    studentId = Integer.parseInt(map.get("studentId").toString());
                    commonClassId = Integer.parseInt(map.get("commonClassId").toString());
                    commonCost = (BigDecimal)map.get("commonCost");
                    commonSurplus = (BigDecimal)map.get("commonSurplus");
                }
                commonRecords.setStudentId(studentId);
                commonRecords.setCommonClassId(commonClassId);
                commonRecords.setCommonCardTime(new Date());
                commonRecords.setCommonStatus(2);
                int count1 = crs.addCommonRecords(commonRecords);

                BigDecimal newCommonSurplus = commonSurplus.subtract(commonCost);
                commonTuition.setCommonSurplus(newCommonSurplus);
                commonTuition.setStudentId(studentId);
                commonTuition.setCommonClassId(commonClassId);
                int count2 = cts.updateCommonTuition(commonTuition);

                if(count1>0 && count2>0){
                    out.print("<script>alert('学生补签成功！');location.href='jsp/studentFill.jsp';</script>");
                }else{
                    out.print("<script>alert('学生补签失败！');location.href='jsp/studentFill.jsp';</script>");
                }
            }else{
                out.print("<script>alert('"+commonClassName+"已经冻结不能补签！');location.href='jsp/studentFill.jsp';</script>");
            }
        }
    }
}
