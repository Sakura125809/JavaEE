/*
 * @Author: Sakura 
 * @Date: 2022-08-02 13:38:19 
 * @Last Modified by:   Sakura 
 * @Last Modified time: 2022-08-02 13:38:19 
 */
package cn.edu.hit;

import cn.edu.hit.dao.LoginDao;
import cn.edu.hit.dao.StudentDao;
import cn.edu.hit.dao.impl.LoginDaoImpl;
import cn.edu.hit.dao.impl.StudentDaoImpl;
import cn.edu.hit.entity.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.print("请输入用户名：");
        String uid = input.nextLine();
        System.out.print("请输入密码：");
        String pwd = input.nextLine();
        LoginDao lDao = new LoginDaoImpl();
        StudentDao sDao = new StudentDaoImpl();
        if(lDao.login(uid, pwd)) {
            do {
                menu();
                if (choice == 1) {
                    List<Student> list = sDao.getAll("select * from student");
                    System.out.println("学号\t姓名\t年龄\t生日");
                    for(Student s : list) {
                        System.out.println(s.getSid() + "\t" + s.getSname() + "\t" + s.getAge() + "\t" + s.getBirthday());
                    }
                    input.nextLine();
                } else if (choice == 2) {
                    System.out.println("请输入所查询学生的学号：");
                    String sid = input.nextLine();
                    Student s = sDao.getBySid(sid);
                    if (s != null) {
                        System.out.println("所查学生信息为：\n" + s.getSname() + "\t" + s.getSname() + "\t" + s.getAge() + "\t" + s.getBirthday() + "\n");
                    } else {
                        System.out.println("未找到学生！");
                    }
                    input.nextLine();
                } else if (choice == 3) {
                    System.out.println("请输入学号：");
                    String sid = input.nextLine();
                    if(sDao.getBySid(sid) != null) {
                        System.out.println("您输入的学号已经存在！");
                        input.nextLine();
                        continue;
                    }
                    System.out.println("请输入姓名：");
                    String sname = input.nextLine();
                    System.out.println("请输入年龄：");
                    int age = input.nextInt();
                    input.nextLine();
                    System.out.println("请输入生日：");
                    String birthday = input.nextLine();
                    Student s = new Student(sid, sname, age, birthday);
                    sDao.add(s);
                    System.out.println("添加成功！\n");
                    input.nextLine();
                } else if (choice == 4) {
                    System.out.println("请输入学号：");
                    String sid = input.next();
                    if(sDao.getBySid(sid) == null) {
                        System.out.println("您输入的学号不存在");
                        input.nextLine();
                        continue;
                    }
                    Student s = sDao.getBySid(sid);
                    System.out.println("该学生信息为：\n" + s.getSid() + "\t" + s.getSname() + "\t" + s.getAge() + "\t" + s.getBirthday() + "\n");
                    input.nextLine();
                    System.out.println("请输入姓名：");
                    String sname = input.nextLine();
                    System.out.println("请输入年龄：");
                    int age = input.nextInt();
                    input.nextLine();
                    System.out.println("请输入生日：");
                    String birthday = input.nextLine();
                    Student stu = new Student(sid, sname, age, birthday);
                    sDao.modify(stu);
                    System.out.println("修改成功！\n");
                    input.nextLine();
                } else if (choice == 5) {
                    System.out.println("请输入要删除的学生的学号：");
                    String sid = input.nextLine();
                    if(sDao.getBySid(sid) == null) {
                        System.out.println("您输入的学号不存在！");
                        input.nextLine();
                        continue;
                    }
                    System.out.println("是否确定删除（Y/N）？");
                    String check = input.nextLine();
                    if (check.equalsIgnoreCase("Y")) {
                        sDao.remove(sid);
                        System.out.println("删除成功！\n");
                        input.nextLine();
                    }
                }
            }while(choice != 6);
        } else {
            System.out.println("用户名或密码错误，登录失败！");
        }
    }
    static int choice = 0;
    public static void menu() {
        try {
            new ProcessBuilder("cmd", "/C", "cls").inheritIO().start();
            Thread.sleep(1000);
        }catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println("1.查询全部学生\n2.按学号查询学生\n3.增加学生\n4.修改学生信息\n5.删除学生\n6.退出");
        System.out.println("请输入选择：");
        choice = input.nextInt();
        input.nextLine();
        try {
            new ProcessBuilder("cmd", "/C", "cls").inheritIO().start();
            Thread.sleep(1000);
        }catch(Exception e) {
            e.printStackTrace();
        }

    }
}
