package com.example.registeration2;

public class Course {

    int courseID; //강의 고유 번호
    String courseUniversity; //해당 년도
    int courseYear; //해당학기
    String courseTerm; //강의영역
    String courseArea;//해당학과
    String courseMajor;
    String courseGrade;//해당학년
    String courseTitle;//강의제목
    int courseCredit;//강의 학점
    int courseDivide;//강의 분반
    int coursePersonnel;//강의 제한 인원
    String courseProfessor; //강의 교슈
    String courseTime; // 강의 시간대
    String courseRoom;//강의실
    int courseRival; //강의 경쟁자 수


    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseUniversity() {
        return courseUniversity;
    }

    public void setCourseUniversity(String courseUniversity) {
        this.courseUniversity = courseUniversity;
    }

    public int getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }

    public String getCourseTerm() {
        return courseTerm;
    }

    public void setCourseTerm(String courseTerm) {
        this.courseTerm = courseTerm;
    }

    public String getCourseArea() {
        return courseArea;
    }

    public void setCourseArea(String courseArea) {
        this.courseArea = courseArea;
    }
    public String getCourseMajor() {
        return courseMajor;
    }

    public void setCourseMajor(String courseMajor) {
        this.courseMajor = courseMajor;
    }

    public String getCourseGrade() {
        return courseGrade;
    }

    public void setCourseGrade(String courseGrade) {
        this.courseGrade = courseGrade;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(int courseCredit) {
        this.courseCredit = courseCredit;
    }

    public int getCourseDivide() {
        return courseDivide;
    }

    public void setCourseDivide(int courseDivide) {
        this.courseDivide = courseDivide;
    }

    public int getCoursePersonnel() {
        return coursePersonnel;
    }

    public void setCoursePersonnel(int coursePersonnel) {
        this.coursePersonnel = coursePersonnel;
    }

    public String getCourseProfessor() {
        return courseProfessor;
    }

    public void setCourseProfessor(String courseProfessor) {
        this.courseProfessor = courseProfessor;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseRoom() {
        return courseRoom;
    }

    public void setCourseRoom(String courseRoom) {
        this.courseRoom = courseRoom;
    }



    public Course(int courseID, String courseTitle, int courseDivide,String courseGrade, int coursePersonnel, int courseRival) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.courseDivide = courseDivide;
        this.courseGrade = courseGrade;
        this.coursePersonnel = coursePersonnel;
        this.courseRival = courseRival;
    }

    public Course(int courseID, String courseTitle, int courseDivide,String courseGrade, int coursePersonnel, int courseRival, int courseCredit) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.courseDivide = courseDivide;
        this.courseGrade = courseGrade;
        this.coursePersonnel = coursePersonnel;
        this.courseRival = courseRival;
        this.courseCredit = courseCredit;
    }


    public Course(int courseID, String courseGrade, String courseTitle, String courseProfessor,int courseCredit, int courseDivide, int coursePersonnel, String courseTime) {
        this.courseID = courseID;
        this.courseGrade = courseGrade;
        this.courseTitle = courseTitle;
        this.courseProfessor = courseProfessor;
        this.courseCredit = courseCredit;
        this.courseDivide = courseDivide;
        this.coursePersonnel = coursePersonnel;
        this.courseTime = courseTime;
    }

    public int getCourseRival() {
        return courseRival;
    }

    public void setCourseRival(int courseRival) {
        this.courseRival = courseRival;
    }

    public Course(int courseID, String courseUniversity, int courseYear, String courseTerm, String courseArea, String courseMajor, String courseGrade, String courseTitle, int courseCredit, int courseDivide, int coursePersonnel, String courseProfessor, String courseTime, String courseRoom)
    {
        this.courseID = courseID;
        this.courseUniversity = courseUniversity;
        this.courseYear = courseYear;
        this.courseTerm = courseTerm;
        this.courseArea = courseArea;
        this.courseMajor = courseMajor;
        this.courseGrade = courseGrade;
        this.courseTitle = courseTitle;
        this.courseCredit = courseCredit;
        this.courseDivide = courseDivide;
        this.coursePersonnel = coursePersonnel;
        this.courseProfessor = courseProfessor;
        this.courseTime = courseTime;
        this.courseRoom = courseRoom;
    }
}
