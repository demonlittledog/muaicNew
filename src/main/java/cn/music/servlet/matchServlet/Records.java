package cn.music.servlet.matchServlet;

import cn.music.service.commonManagerService.StudentInfoService;
import cn.music.service.matchService.MatchRecordsService;
import cn.music.service.matchService.MatchTuitionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;

public class Records extends HttpServlet {
    private StudentInfoService sis = new StudentInfoService();
    private MatchRecordsService mrs = new MatchRecordsService();
    private MatchTuitionService mts = new MatchTuitionService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int c = 0;
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        System.out.println(req.getParameter("matchClassId"));
        int matchClassId = Integer.parseInt(req.getParameter("matchClassId"));
        String parentsPhone = req.getParameter("parentsPhone");
        String password = req.getParameter("password");
        int count = sis.getStudentIdByPassword(parentsPhone,password);
        if(count==-1){
            writer.write("密码输入错误！");
            writer.flush();
            writer.close();
        }else {
           c = mrs.addMatchRecords(count,matchClassId,new Date(),1);
           if(c>0){
               boolean flag = mts.getMatchTuition(count,matchClassId);
               BigDecimal matchCost = mts.getMatchCost(matchClassId);
              if(flag){
                  int u = mts.updateTuition(matchCost,count,matchClassId);
                  if(u>0){
                      writer.write("打卡成功！");
                      writer.flush();
                      writer.close();
                  }else {
                      writer.write("打卡失败！");
                      writer.flush();
                      writer.close();
                  }
              }else {
                  int i =  mts.insertTuition(count,matchClassId,matchCost);
                  if(i>0){
                      writer.write("打卡成功！");
                      writer.flush();
                      writer.close();
                  }else {
                      writer.write("打卡失败！");
                      writer.flush();
                      writer.close();
                  }
              }
           }else {
               writer.write("打卡失败！");
               writer.flush();
               writer.close();
           }
        }
    }
}
