package com.sadcode.quizapp.question;

import com.sadcode.quizapp.model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<QuestionModel,Integer> {
     List<QuestionModel>findByCategory(String category);
     @Query(value = "select  * from  questions q where q.category=:category ORDER BY RAND() limit :numQ",nativeQuery = true)
     List<QuestionModel> findRandomQuestionByCategory(String category, int numQ);


}
