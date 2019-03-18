package cn.music.servlet.matchServlet;

import cn.music.entity.CommonClassInfo;
import cn.music.entity.MatchClassInfo;
import cn.music.service.commonManagerService.CommonClassInfoService;
import cn.music.service.matchService.MatchClassInfoService;
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
public class ChangeMatchClassStatusServlet extends HttpServlet{
    MatchClassInfoService mcis = new MatchClassInfoService();
    MatchClassInfo matchClassInfo = new MatchClassInfo();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");
        int mci = Integer.parseInt(req.getParameter("mci"));
        matchClassInfo.setMatchClassId(mci);
        String ms = req.getParameter("ms").trim();
        int matchStatus = 0;
        if(ms.equals("正常")){
            matchClassInfo.setMatchStatus(matchStatus);
            mcis.updateMatchClassStatus(matchClassInfo);
        }else{
            matchStatus = 1;
            matchClassInfo.setMatchClassId(mci);
            matchClassInfo.setMatchStatus(matchStatus);
            mcis.updateMatchClassStatus(matchClassInfo);
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("cci",mci);
        map.put("ms",matchStatus);
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
