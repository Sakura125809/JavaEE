/*
 * @Author: Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Date: 2022-08-02 14:28:32 
 * @Last Modified by:   Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Last Modified time: 2022-08-02 14:28:32 
 */
package edu.hit.msc.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import edu.hit.msc.tools.StringUtils;

@WebServlet(name = "VcodeServlet", value = "/VcodeServlet")
public class VcodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String from = request.getParameter("from");
        if (from.equals("validate")) {
            validate(request, response);
        }
    }
    private void validate(HttpServletRequest request, HttpServletResponse response)throws IOException
    {
        response.setContentType("image/jpeg");
        BufferedImage image = new BufferedImage(80,20,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        int br = (int)(Math.random() * 128);
        int bg = (int)(Math.random() * 128);
        int bb = (int)(Math.random() * 128);
        Color bgColor = new Color(br,bg,bb);
        graphics.setColor(bgColor);
        graphics.fillRect(0,0,80,20);
        int fr = (int)(Math.random() * 128) + 128;
        int fg = (int)(Math.random() * 128) + 128;
        int fb = (int)(Math.random() * 128) + 128;
        Color fColor = new Color(fr,fg,fb);
        graphics.setColor(fColor);
        String s = StringUtils.randomStr(4);
        HttpSession session = request.getSession();
        session.setAttribute("code",s);
        graphics.setFont(new Font("微软雅黑",Font.ITALIC,18));
        graphics.drawString(s,3,18);
        for(int i=0;i<5;i++)
        {
            int x = (int)(Math.random() * 80);
            int y = (int)(Math.random() * 20);
            int x1 = (int)(Math.random() * 80);
            int y1 = (int)(Math.random() * 20);
            int r = (int)(Math.random() * 128) + 128;
            int g = (int)(Math.random() * 128) + 128;
            int b = (int)(Math.random() * 128) + 128;
            Color color = new Color(r,g,b);
            graphics.setColor(color);
            graphics.drawLine(x,y,x1,y1);
        }
        graphics.dispose();
        ImageIO.write(image,"jpg",response.getOutputStream());
    }
}
