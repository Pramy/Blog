package com.pramy.controller;
  
import java.awt.*;  
import java.awt.geom.*;  
import java.awt.image.*;  
import java.io.*;  
import java.util.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
import javax.imageio.ImageIO;  
  
public class VerificationCodeServlet extends HttpServlet {  
   
    /*该方法主要作用是获得随机生成的颜色*/   
    public Color getRandColor(int s,int e){  
        Random random=new Random ();  
        if(s>255) s=255;  
        if(e>255) e=255;  
        int r,g,b;  
        r=s+random.nextInt(e-s);    //随机生成RGB颜色中的r值  
        g=s+random.nextInt(e-s);    //随机生成RGB颜色中的g值  
        b=s+random.nextInt(e-s);    //随机生成RGB颜色中的b值  
        return new Color(r,g,b);  
    }  
    int i=0;
    @Override  
    public void service(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
        //设置不缓存图片  
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "No-cache");  
        response.setDateHeader("Expires", 0);  
        //指定生成的响应图片,一定不能缺少这句话,否则错误.  
        response.setContentType("image/jpeg");  
        int width=100,height=28;     //指定生成验证码的宽度和高度  
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); //创建BufferedImage对象,其作用相当于一图片  
        Graphics g=image.getGraphics();     //创建Graphics对象,其作用相当于画笔  
        Graphics2D g2d=(Graphics2D)g;       //创建Grapchics2D对象  
        Random random=new Random();  
        g.setColor(getRandColor(200,250));  
        g.fillRect(0, 0, width, height);    //绘制背景  
        g.setColor(getRandColor(180,200));  
          
        //绘制100条颜色和位置全部为随机产生的线条,该线条为2f  
        for(int i=0;i<100;i++){  
            int x=random.nextInt(width-1);  
            int y=random.nextInt(height-1);  
            int x1=random.nextInt(6)+1;  
            int y1=random.nextInt(12)+1;  
            BasicStroke bs=new BasicStroke(2f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL); //定制线条样式  
            Line2D line=new Line2D.Double(x,y,x+x1,y+y1);  
            g2d.setStroke(bs);  
            g2d.draw(line);     //绘制直线  
        }  
        //输出由英文，数字，和中文随机组成的验证文字，具体的组合方式根据生成随机数确定。  
        String randomCode="";  
        String ctmp="";  
        int itmp=0;  
        //制定输出的验证码为四位  
        for(int i=0;i<4;i++){  
            switch(random.nextInt(3)){  
                case 1:     //生成A-Z的字母  
                     itmp=random.nextInt(26)+65;  
                     ctmp=String.valueOf((char)itmp);  
                     break;  
                case 2:
                	itmp=random.nextInt(26)+65;  
                    ctmp=String.valueOf((char)itmp);  
                     break;  
                default:  
                     itmp=random.nextInt(10)+48;  
                     ctmp=String.valueOf((char)itmp);  
                     break;  
            }  
            randomCode+=ctmp;  
            Color color=new Color(20+random.nextInt(110),20+random.nextInt(110),random.nextInt(110));  
            g.setColor(color);  
            //将生成的随机数进行随机缩放并旋转制定角度 
            /*将文字旋转制定角度*/  
            Graphics2D g2d_word=(Graphics2D)g;  
            AffineTransform trans=new AffineTransform();  
            trans.rotate((45)*3.14/180,15*i+8,7);  
            /*缩放文字*/  
            float scaleSize=random.nextFloat()+0.8f;  
            if(scaleSize>1f) scaleSize=1f;  
            trans.scale(scaleSize, scaleSize);  
            g2d_word.setTransform(trans);  
            g.drawString(ctmp, 15*i+18, 14);  
        }  
        HttpSession session=request.getSession();  
        session.setAttribute("VerificationCode", randomCode);
        g.dispose();    //释放g所占用的系统资源  
        ImageIO.write(image,"JPEG",response.getOutputStream()); //输出图片  
    }  
}  