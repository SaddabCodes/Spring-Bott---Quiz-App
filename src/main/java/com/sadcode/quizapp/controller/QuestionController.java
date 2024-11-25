package com.sadcode.quizapp.controller;

import com.sadcode.quizapp.model.QuestionModel;
import com.sadcode.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<QuestionModel>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<QuestionModel>> getQuestionByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/saveQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody QuestionModel questionModel) {
        return questionService.addQuestion(questionModel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        return questionService.deleteQuestion(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<QuestionModel> updateQuestion(@RequestBody QuestionModel questionModel, @PathVariable Integer id) {
        return questionService.updateQuestion(questionModel, id);
    }


}
