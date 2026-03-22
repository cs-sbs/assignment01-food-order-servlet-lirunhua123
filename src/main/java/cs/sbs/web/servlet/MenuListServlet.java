package cs.sbs.web.servlet;

import cs.sbs.web.model.MenuItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MenuListServlet extends HttpServlet {

    // 1. 菜单列表定义在类里，方法外
    private static final List<MenuItem> MENU_LIST = new ArrayList<>();

    // 2. 静态初始化块，也放在类里、方法外
    static {
        MENU_LIST.add(new MenuItem("香辣鸡腿堡", 18.0));
        MENU_LIST.add(new MenuItem("奥尔良烤翅", 12.0));
        MENU_LIST.add(new MenuItem("可乐(中杯)", 6.0));
        MENU_LIST.add(new MenuItem("薯条(大份)", 10.0));
    }

    // 3. 只有一个 doGet 方法，处理菜单展示
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置响应编码和格式
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 输出菜单页面
        out.println("<!DOCTYPE html>");
        out.println("<html lang='zh-CN'>");
        out.println("<head><meta charset='UTF-8'><title>菜单列表</title></head>");
        out.println("<body>");
        out.println("<h1>今日菜单</h1>");
        out.println("<table border='1' cellpadding='10'>");
        out.println("<tr><th>菜品名称</th><th>价格（元）</th></tr>");

        // 遍历菜单（注意：这里要和 MenuItem 的字段名对应）
        for (MenuItem item : MENU_LIST) {
            out.println("<tr>");
            out.println("<td>" + item.getFootname() + "</td>"); // 这里用 getFootname()，因为你实体类字段是 footname
            out.println("<td>¥" + item.getPrice() + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("<p><a href='order.html'>返回点餐页面</a></p>");
        out.println("</body></html>");
        out.close();
    }

    // 4. 获取菜品价格的方法，放在类里、方法外
    public static Double getFoodPrice(String foodName) {
        for (MenuItem item : MENU_LIST) {
            // 这里用 getFootname() 匹配实体类字段
            if (item.getFootname().equals(foodName)) {
                return item.getPrice();
            }
        }
        return 0.0;
    }
}