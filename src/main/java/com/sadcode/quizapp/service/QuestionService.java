package com.sadcode.quizapp.service;

import com.sadcode.quizapp.model.QuestionModel;
import com.sadcode.quizapp.question.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<List<QuestionModel>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //        if something wrong then this empty array print
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<QuestionModel>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> addQuestion(QuestionModel questionModel) {
        try {
            questionDao.save(questionModel);
            return new ResponseEntity<>("questions save", HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>("questions save", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        try {
            if (questionDao.existsById(id)) {
                questionDao.deleteById(id);
                return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Question with id " +id+" not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<QuestionModel> updateQuestion(QuestionModel questionModel, Integer id) {
        Optional<QuestionModel> optionalQuestionModel = questionDao.findById(id);
        if (optionalQuestionModel.isPresent()) {
            QuestionModel existQuestion = optionalQuestionModel.get();
            existQuestion.setQuestion_title(questionModel.getQuestion_title());
            existQuestion.setOption1(questionModel.getOption1());
            existQuestion.setOption2(questionModel.getOption2());
            existQuestion.setOption3(questionModel.getOption3());
            existQuestion.setOption4(questionModel.getOption4());
            existQuestion.setRightAnswer(questionModel.getRightAnswer());
            existQuestion.setDifficultyLevel(questionModel.getDifficultyLevel());
            existQuestion.setCategory(questionModel.getCategory());
            return new ResponseEntity<>(questionDao.save(existQuestion), HttpStatus.CREATED);
        } else {
            throw new RuntimeException("Question with " + id + " is not found");
        }

    }


}
