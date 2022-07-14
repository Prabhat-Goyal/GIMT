package com.service.gimt;

public class ResultSData {
    private String exam,pdf;

    public String getExam() {
        return exam;
    }

    public void getExam(String exam) {
        this.exam = exam;
    }

    public ResultSData() {
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public ResultSData(String exam) {
        this.exam = exam;

    }
}
