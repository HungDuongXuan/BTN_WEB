package com.dzungyb.btn_web.service;

import com.dzungyb.btn_web.entity.Contest;
import com.dzungyb.btn_web.entity.Exam;
import com.dzungyb.btn_web.entity.Question;
import com.dzungyb.btn_web.entity.UserAnswer;
import com.dzungyb.btn_web.error.ErrorException;
import com.dzungyb.btn_web.model.UserAnswerDetailModel;
import com.dzungyb.btn_web.model.UserAnswerQuestionModel;
import com.dzungyb.btn_web.model.dto.AnswerQuestionDto;
import com.dzungyb.btn_web.model.dto.UserAnswerDto;
import com.dzungyb.btn_web.repository.ContestRepository;
import com.dzungyb.btn_web.repository.ExamRepository;
import com.dzungyb.btn_web.repository.QuestionRepository;
import com.dzungyb.btn_web.repository.UserAnswerRepository;
import com.dzungyb.btn_web.utils.Valids;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.UUID;

@Component
@RequiredArgsConstructor
public class UserAnswerService {
    private final UserAnswerRepository userAnswerRep;
    private final QuestionRepository questionRep;
    private final ContestRepository contestRep;
    private final ExamRepository examRep;
    public static int id = 1;

    private UserAnswer ToUserAnswerEntity(AnswerQuestionDto userAnswerDto, int idContest) {
        return UserAnswer.create(
                id++,
                idContest,
                userAnswerDto.getIdQuestion(),
                userAnswerDto.getAnswer()
        );
    }

    public List<UserAnswer> getAll() {
        return userAnswerRep.findAll();
    }

    public UserAnswer searchById(int id) {
        return userAnswerRep.findFirstById(id);
    }

    public UserAnswerDetailModel searchDetailByIdContest(int id) throws ErrorException {
        List<UserAnswer> userAnswerList = userAnswerRep.findByIdContest(id);
        if (userAnswerList.isEmpty()) {
            System.out.println("Empty");
            throw new ErrorException("Empty");
        }
        List<Question> questionList = questionRep.findAll();
        List<UserAnswerQuestionModel> userAnswerQuestionList = new ArrayList<>();
        for (UserAnswer userAnswer : userAnswerList) {
            for (Question question : questionList) {
                if (userAnswer.getIdQuestion() == question.getId()) {
                    userAnswerQuestionList.add(new UserAnswerQuestionModel(userAnswer.getAnswer(), question));
                    break;
                }
            }
        }
        return new UserAnswerDetailModel(userAnswerList.get(0).getId(), userAnswerList.get(0).getIdContest(), userAnswerQuestionList);
    }

    public void start(UserAnswer userAnswerEntity) throws ErrorException {
        int idContest = userAnswerEntity.getIdContest();
        Contest contest = contestRep.findFirstById(idContest);

        if (contest == null) throw new ErrorException("You don't have this exam!");
        if (contest.getSubmitTime() != null) throw new ErrorException("You already finish this exam!");
        if (contest.getStartTime() != null) {
            Exam exam = examRep.findFirstById(contest.getIdExam());
            if (contest.getStartTime().plusMinutes(exam.getTimeDuration()).isBefore(LocalDateTime.now()))
                throw new ErrorException("Time out!");
        } else {
            contest.setStartTime(LocalDateTime.now());
            contestRep.save(contest);
        }
    }

    public List<UserAnswer> submit(UserAnswerDto userAnswerDto) throws ErrorException {
        int idContest = userAnswerDto.getIdContest();
        Contest contest = contestRep.findFirstById(idContest);
        if (contest == null) throw new ErrorException("You don't have this exam!");
        if (contest.getSubmitTime() != null) throw new ErrorException("You already finish this exam!");
        List<AnswerQuestionDto> answerQuestionDtoList = userAnswerDto.getAnswerQuestion();
        if (answerQuestionDtoList.isEmpty()) throw new ErrorException("Something went wrong");

        List<Question> questionList = questionRep.findByIdExam(contest.getIdExam());
        int rightAnswer = 0;
        int wrongAnswer = 0;
        int blankAnswer = 0;

        List<UserAnswer> userAnswerEntityList = new ArrayList<>();
        for (AnswerQuestionDto answerQuestion : answerQuestionDtoList) {
            if (answerQuestion.getAnswer() == null) blankAnswer++;
            else {
                for (Question question : questionList) {
                    if (question.getId() == answerQuestion.getIdQuestion()) {
                        if (question.getRightAnswer() == answerQuestion.getAnswer()) rightAnswer++;
                        else wrongAnswer++;
                        break;
                    }
                }
            }
            userAnswerEntityList.add(ToUserAnswerEntity(answerQuestion, idContest));
        }
        float score = rightAnswer * 10 / questionList.size();
        contest.setScore(score);
        contest.setSubmitTime(LocalDateTime.now());
        contest.setRightAnswer(rightAnswer);
        contest.setWrongAnswer(wrongAnswer);
        contest.setBlankAnswer(blankAnswer);

        contestRep.save(contest);
        userAnswerRep.saveAll(userAnswerEntityList);

        return userAnswerEntityList;
    }

//    public void edit(UserAnswer newUserAnswer) {
//        if (Valids.isEmpty(newUserAnswer.getId())) throw new ErrorException("Id is Empty");
//        UserAnswer userAnswer = userAnswerRep.findFirstById(newUserAnswer.getId());
//        if (!Valids.isEmpty(newUserAnswer.getIdContest())) {
//            if (!contestRep.existsById(newUserAnswer.getIdContest())) throw new ErrorException("Id Contest not found");
//            userAnswer.setIdContest(newUserAnswer.getIdContest());
//        }
//        if (!Valids.isEmpty(newUserAnswer.getIdQuestion())) {
//            if (questionRep.existsById(newUserAnswer.getIdQuestion()))
//                throw new ErrorException("Id Question not found");
//            userAnswer.setIdQuestion(newUserAnswer.getIdQuestion());
//        }
//        if (!Valids.isEmpty(newUserAnswer.getAnswer())) {
//            if (newUserAnswer.getAnswer() < 1 || newUserAnswer.getAnswer() > 4)
//                throw new ErrorException("Answer is not valid");
//            userAnswer.setAnswer(newUserAnswer.getAnswer());
//        }
//        userAnswerRep.save(userAnswer);
//    }

    public void delete(int id) throws ErrorException {
        if (!userAnswerRep.existsById(id)) throw new ErrorException("Khong tim thay du lieu phu hop");
        userAnswerRep.deleteById(id);
    }
}