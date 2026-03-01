package ua.opnu;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        // === Завдання 1: TimeSpan ===
        TimeSpan t1 = new TimeSpan();            // 0:0
        TimeSpan t2 = new TimeSpan(90);          // 1:30
        TimeSpan t3 = new TimeSpan(2, 45);       // 2:45
        TimeSpan t4 = new TimeSpan(t3);          // копія 2:45

        t1.add(1, 15);
        t2.add(30);
        t4.add(new TimeSpan(0, 15));
        t3.subtract(1, 0);

        System.out.println("TimeSpan t1: " + t1);
        System.out.println("TimeSpan t2: " + t2);
        System.out.println("TimeSpan t3: " + t3);
        System.out.println("TimeSpan t4: " + t4);

        // === Завдання 2: Person/Student/Lecturer ===
        Person p1 = new Person("Ivanov", "Ivan", 35);
        Student s1 = new Student("Petrenko", "Olena", 20, "CS-101", "12345");
        Lecturer l1 = new Lecturer("Shevchenko", "Mykola", 45, "Math", 15000);

        Person[] people = {p1, s1, l1};
        for (Person person : people) {
            System.out.println(person);
        }

        // === Завдання 3: RoShamBo ===
        GameShape player = new Rock();
        GameShape computer = generateShape();

        System.out.println("Player: " + player);
        System.out.println("Computer: " + computer);
        int winner = checkWinner(player, computer);
        if (winner == 1) System.out.println("Player wins!");
        else if (winner == -1) System.out.println("Computer wins!");
        else System.out.println("Draw!");

        // === Завдання 4: Draw ===
        Shape[] shapes = new Shape[3];
        shapes[0] = new Rectangle(5, 10);
        shapes[1] = new Ellipse(3, 6);
        shapes[2] = new Rectangle(2, 2);

        for (Shape s : shapes) s.draw();
    }

    // RoShamBo helper methods
    static GameShape generateShape() {
        Random r = new Random();
        int n = r.nextInt(3);
        if (n == 0) return new Rock();
        else if (n == 1) return new Paper();
        else return new Scissors();
    }

    static int checkWinner(GameShape player, GameShape computer) {
        if (player instanceof Rock && computer instanceof Scissors) return 1;
        if (player instanceof Scissors && computer instanceof Paper) return 1;
        if (player instanceof Paper && computer instanceof Rock) return 1;
        if (computer instanceof Rock && player instanceof Scissors) return -1;
        if (computer instanceof Scissors && player instanceof Paper) return -1;
        if (computer instanceof Paper && player instanceof Rock) return -1;
        return 0;
    }
}

// === Завдання 1 ===
class TimeSpan {
    private int hours;
    private int minutes;

    public TimeSpan() { this(0,0); }
    public TimeSpan(int minutes) { this(0, minutes); }
    public TimeSpan(int hours, int minutes) {
        if (hours < 0) hours = 0;
        if (minutes < 0) minutes = 0;
        this.hours = hours + minutes / 60;
        this.minutes = minutes % 60;
    }
    public TimeSpan(TimeSpan t) { this(t.hours, t.minutes); }

    public void add(int h, int m) {
        int total = (hours + h) * 60 + minutes + m;
        hours = total / 60; minutes = total % 60;
    }
    public void add(int m) { add(0, m); }
    public void add(TimeSpan t) { add(t.hours, t.minutes); }

    public void subtract(int h, int m) {
        int total = hours*60 + minutes - (h*60 + m);
        if (total < 0) total=0;
        hours = total/60; minutes = total%60;
    }
    public void subtract(int m) { subtract(0, m); }
    public void subtract(TimeSpan t) { subtract(t.hours, t.minutes); }

    @Override
    public String toString() { return hours + "h:" + minutes + "m"; }
}

// === Завдання 2 ===
class Person {
    private String lastName;
    private String firstName;
    private int age;
    public Person(String lastName, String firstName, int age){
        this.lastName = lastName; this.firstName = firstName; this.age = age;
    }
    public String toString() { return "Людина " + lastName + " " + firstName + ", вік: " + age; }
}
class Student extends Person {
    private String group;
    private String studentId;
    public Student(String lastName, String firstName, int age, String group, String studentId){
        super(lastName, firstName, age);
        this.group = group; this.studentId = studentId;
    }
    public String toString() {
        return "Студент групи " + group + ", " + super.toString().substring(7) + ". Номер студентського квитка: " + studentId;
    }
}
class Lecturer extends Person {
    private String department;
    private double salary;
    public Lecturer(String lastName, String firstName, int age, String department, double salary){
        super(lastName, firstName, age);
        this.department = department; this.salary = salary;
    }
    public String toString() {
        return "Викладач кафедри " + department + ", " + super.toString().substring(7) + ". Зарплата: " + salary;
    }
}

// === Завдання 3 ===
abstract class GameShape {}
class Rock extends GameShape { public String toString(){return "Rock";} }
class Paper extends GameShape { public String toString(){return "Paper";} }
class Scissors extends GameShape { public String toString(){return "Scissors";} }

// === Завдання 4 ===
abstract class Shape { abstract void draw(); }
class Rectangle extends Shape {
    private int width, height;
    public Rectangle(int w, int h){ this.width=w; this.height=h; }
    void draw(){ System.out.println("Drawing Rectangle: " + width + "x" + height); }
}
class Ellipse extends Shape {
    private int width, height;
    public Ellipse(int w, int h){ this.width=w; this.height=h; }
    void draw(){ System.out.println("Drawing Ellipse: " + width + "x" + height); }
}