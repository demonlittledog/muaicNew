package cn.music.servlet.commonManagerServlet;

import cn.music.entity.CommonClassInfo;
import cn.music.service.commonManagerService.CommonClassInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class GetMoneyServlet extends HttpServlet {
    CommonClassInfoService ccis = new  CommonClassInfoService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");
        int commonClassId = Integer.parseInt(req.getParameter("commonClassId"));
        CommonClassInfo commonClassInfo = ccis.getMoney(commonClassId);
        BigDecimal commonCost = commonClassInfo.getCommonCost();
        BigDecimal commonSum = new BigDecimal(commonClassInfo.getCommonSum());
        BigDecimal money = commonCost.multiply(commonSum);

        PrintWriter writer = resp.getWriter();
        writer.write(money.toString());
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
