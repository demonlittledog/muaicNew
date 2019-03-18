package cn.music.servlet.commonManagerServlet;

import cn.music.entity.CommonTuition;
import cn.music.service.commonManagerService.CommonTuitionService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class PayMoneyServlet extends HttpServlet {
    CommonTuition commonTuition = new CommonTuition();
    CommonTuitionService cts = new CommonTuitionService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");

        int commonClassId = Integer.parseInt(req.getParameter("commonClassId"));
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        BigDecimal money = new BigDecimal(req.getParameter("money"));
        int count = cts.payMoney(commonClassId,studentId,money);

        commonTuition = cts.getCommonTuition(commonClassId,studentId);
        BigDecimal commonSurplus = commonTuition.getCommonSurplus();
        BigDecimal commonAllCost = commonTuition.getCommonAllCost();
        Gson gson = new Gson();
        String result = gson.toJson(commonTuition);
        PrintWriter writer = resp.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }
}
