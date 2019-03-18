package cn.music.servlet.commonManagerServlet;

import cn.music.entity.CommonClassInfo;
import cn.music.service.commonManagerService.CommonClassInfoService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2018/11/13.
 */
public class ChangeCommonClassStatusServlet extends HttpServlet{
    CommonClassInfoService ccis = new CommonClassInfoService();
    CommonClassInfo commonClassInfo = new CommonClassInfo();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");
        int cci = Integer.parseInt(req.getParameter("cci"));
        commonClassInfo.setCommonClassId(cci);
        String cs = req.getParameter("cs").trim();
        int commonStatus = 0;
        if(cs.equals("正常")){
            commonClassInfo.setCommonStatus(commonStatus);
            ccis.updateCommonClassStatus(commonClassInfo);
        }else{
            commonStatus = 1;
            commonClassInfo.setCommonClassId(cci);
            commonClassInfo.setCommonStatus(commonStatus);
            ccis.updateCommonClassStatus(commonClassInfo);
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("cci",cci);
        map.put("cs",commonStatus);
        Gson gson = new Gson();
        String result = gson.toJson(map);
        PrintWriter writer = resp.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
