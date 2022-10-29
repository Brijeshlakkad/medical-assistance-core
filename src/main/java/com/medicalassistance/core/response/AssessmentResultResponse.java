package com.medicalassistance.core.response;

import com.medicalassistance.core.entity.AttemptedQuestion;

import java.util.List;

public class AssessmentResultResponse {
    private List<AttemptedQuestion> attemptedQuestions;

    public List<AttemptedQuestion> getAttemptedQuestions() {
        return attemptedQuestions;
    }

    public void setAttemptedQuestions(List<AttemptedQuestion> attemptedQuestions) {
        this.attemptedQuestions = attemptedQuestions;
    }

    public boolean addAttemptedQuestion(AttemptedQuestion attemptedQuestion) {
        return this.attemptedQuestions.add(attemptedQuestion);
    }
}
