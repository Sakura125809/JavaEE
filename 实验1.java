/*
 * @Author: Sakura 
 * @Date: 2022-08-02 12:50:06 
 * @Last Modified by:   Sakura 
 * @Last Modified time: 2022-08-02 12:50:06 
 */
import java.util.Scanner;
public class Test {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.printf("请输入学生数：");
        int sNum = s.nextInt();
        Student[] ss = new Student[sNum];
        for(int i = 0; i < sNum; ++i) {
            System.out.printf("请输入第 %d 名学生的姓名：", i + 1);
            String name = s.next();
            ss[i] = new Student(name);
        }
        System.out.printf("请输入课程数：");
        int cNum = s.nextInt();
        Course[] cc = new Course[cNum];
        for(int i = 0; i < cNum; ++i) {
            String name = s.next();
            System.out.printf("请输入第 %d 门课程的名字：", i + 1);
            double credit = s.nextDouble();
            cc[i] = new Course(name, credit);
        }
        int num = sNum * cNum;
        SelectCoure[] sc = new SelectCoure[num];
        for(int i = 0; i < num; ++i) {
            System.out.printf("请输入学生姓名：");
            String name = s.next();
            System.out.printf("请输入课程名：");
            String course = s.next();
            System.out.printf("请输入成绩：");
            double score = s.nextDouble();
            sc[i] = new SelectCoure(name, course, score);
            getStudentBySname(ss, name).addCourse(score, getCreditsByCname(cc, course));
        }
        try {
            new ProcessBuilder("cmd", "/C", "cls").inheritIO().start();
            Thread.sleep(1000);
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("姓名\t课程名\t成绩");
        for(int i = 0; i < num; ++i) {
            System.out.printf("%s\t%s\t%.1f\n", sc[i].getSname(), sc[i].getCname(), sc[i].getScore());
        }
        System.out.println("--------------------------------------");
        System.out.println("姓名\t选课数\t平均分\t总学分");
        for(int i = 0; i < sNum; ++i) {
            System.out.printf("%s\t%d\t%.1f\t%.1f\n", ss[i].getSname(), ss[i].getNum(), ss[i].getAvgScore(), ss[i].getTotalCredits());
        }
    }
    private static double getCreditsByCname(Course[] cc, String cname) {
        for(int i = 0; i < cc.length; ++i) {
            if(cname.equals(cc[i].getCname())) {
                return cc[i].getCredits();
            }
        }
        return 0;
    }
    private static Student getStudentBySname(Student[] ss, String sname) {
        for(int i = 0; i < ss.length; ++i) {
            if(sname.equals(ss[i].getSname())) {
                return ss[i];
            }
        }
        return null;
    }
}
class Student {
    private String sname;
    private int num;
    private double avgScore;
    private double totalScore;
    private double totalCredits;
    protected Student(String sname) {
        this.sname = sname;
    }
    public String getSname() {
        return sname;
    }
    public int getNum() {
        return num;
    }
    public double getAvgScore() {
        return avgScore;
    }
    public double getTotalScore() {
        return totalScore;
    }
    public double getTotalCredits() {
        return totalCredits;
    }
    public void addCourse(double score, double credits) {
        ++num;
        totalScore += score;
        if(score >= 60) {
            totalCredits += credits;
        }
        avgScore = totalScore / num;
    }
}
class Course {
    private String cname;
    private double credits;
    protected Course(String cname, double credits) {
        this.cname = cname;
        this.credits = credits;
    }
    public String getCname() {
        return cname;
    }
    public double getCredits() {
        return credits;
    }
}
class SelectCoure {
    private String sname;
    private String cname;
    private double score;
    protected SelectCoure(String sname, String cname, double score) {
        this.sname = sname;
        this.cname = cname;
        this.score = score;
    }
    public String getSname() {
        return sname;
    }
    public String getCname() {
        return cname;
    }
    public double getScore() {
        return score;
    }
}