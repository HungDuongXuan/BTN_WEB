package com.dzungyb.btn_web.service;

import com.dzungyb.btn_web.entity.Contest;
import com.dzungyb.btn_web.entity.Exam;
import com.dzungyb.btn_web.entity.Question;
import com.dzungyb.btn_web.entity.User;
import com.dzungyb.btn_web.error.ErrorException;
import com.dzungyb.btn_web.model.ExamDetailModel;
import com.dzungyb.btn_web.model.dto.ExamDto;
import com.dzungyb.btn_web.model.dto.ExamQuestionDto;
import com.dzungyb.btn_web.model.dto.QuestionDto;
import com.dzungyb.btn_web.repository.ContestRepository;
import com.dzungyb.btn_web.repository.ExamRepository;
import com.dzungyb.btn_web.repository.QuestionRepository;
import com.dzungyb.btn_web.repository.UserRepository;
import com.dzungyb.btn_web.utils.Valids;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRep;
    private final QuestionRepository questionRep;
    private final UserRepository userRep;
    private final ContestRepository contestRep;

    private Exam ToExamEntity(ExamDto examDto) {
        return Exam.create(
                0,
                examDto.getName(),
                examDto.getDescription(),
                examDto.getType(),
                examDto.getStartTime(),
                examDto.getEndTime(),
                examDto.getTimeDuration()
        );
    }

    private Exam ToExamEntity(ExamQuestionDto examQuestionDto) {
        return Exam.create(
                0,
                examQuestionDto.getName(),
                examQuestionDto.getDescription(),
                examQuestionDto.getType(),
                examQuestionDto.getStartTime(),
                examQuestionDto.getEndTime(),
                examQuestionDto.getTimeDuration()
        );
    }

    private Question ToQuestionEntity(QuestionDto questionDto, int idExam) {
        return Question.create(
                0,
                idExam,
                questionDto.getContent(),
                questionDto.getAnswer1(),
                questionDto.getAnswer2(),
                questionDto.getAnswer3(),
                questionDto.getAnswer4(),
                questionDto.getRightAnswer(),
                questionDto.getExplain()
        );
    }

    public List<Exam> getAll() {
        return examRep.findAll();
    }

    public List<ExamDetailModel> getAllDetail() {
        List<Exam> examList = examRep.findAll();
        List<Question> questionList = questionRep.findAll();
        List<ExamDetailModel> examDetail = new ArrayList<>();
        for (Exam e : examList) {
            List<Question> question = new ArrayList<>();
            for (Question q : questionList) {
                if (q.getIdExam() == e.getId()) {
                    question.add(q);
                }
            }
            examDetail.add(new ExamDetailModel(e, question));
        }
        return examDetail;
    }

    public Exam searchById(int id) throws ErrorException {
        Exam exam = examRep.findFirstById(id);
        if (exam == null) throw new ErrorException("Exam is not exits");
        return exam;
    }

    public ExamDetailModel searchDetailById(int id) throws ErrorException {
        Exam exam = examRep.findFirstById(id);
        if (exam == null) throw new ErrorException("Exam is not exits");
        List<Question> question = questionRep.findByIdExam(id);
        return new ExamDetailModel(exam, question);
    }

    public void create(ExamDto examDto) throws ErrorException {
        if (Valids.isEmpty(examDto.getName())) throw new ErrorException("Ten trong");
        if (Valids.isEmpty(examDto.getDescription())) throw new ErrorException("Mo ta trong");
        if (Valids.isEmpty(examDto.getType())) throw new ErrorException("Loai trong");
        Exam newExam = ToExamEntity(examDto);
        newExam = examRep.save(newExam);
        System.out.println(newExam.getId());
    }

    public void createAll(ExamQuestionDto examQuestionDto) throws ErrorException {
        if (Valids.isEmpty(examQuestionDto.getName())) throw new ErrorException("Ten trong");
        if (Valids.isEmpty(examQuestionDto.getDescription())) throw new ErrorException("Mo ta trong");
        if (Valids.isEmpty(examQuestionDto.getType())) throw new ErrorException("Loai trong");
        if (examQuestionDto.getQuestion().isEmpty()) throw new ErrorException("Phai co it nhat cau hoi");
        List<QuestionDto> questionDtoList = examQuestionDto.getQuestion();
        for (int i = 0; i < questionDtoList.size(); i++) {
            if (Valids.isEmpty(questionDtoList.get(i).getContent()))
                throw new ErrorException(MessageFormat.format("Cau hoi {0} trong", i + 1));
            if (Valids.isEmpty(questionDtoList.get(i).getAnswer1()))
                throw new ErrorException(MessageFormat.format("Cau hoi {0} khong co dap an 1", i + 1));
            if (Valids.isEmpty(questionDtoList.get(i).getAnswer2()))
                throw new ErrorException(MessageFormat.format("Cau hoi {0} khong co dap an 2", i + 1));
            if (Valids.isEmpty(questionDtoList.get(i).getAnswer3()))
                throw new ErrorException(MessageFormat.format("Cau hoi {0} khong co dap an 3", i + 1));
            if (Valids.isEmpty(questionDtoList.get(i).getAnswer4()))
                throw new ErrorException(MessageFormat.format("Cau hoi {0} khong co dap an 4", i + 1));
            if (Valids.isEmpty(questionDtoList.get(i).getRightAnswer()))
                throw new ErrorException(MessageFormat.format("Cau hoi {0} khong co dap an dung", i + 1));
            if (questionDtoList.get(i).getRightAnswer() < 1 || questionDtoList.get(i).getRightAnswer() > 4)
                throw new ErrorException(MessageFormat.format("Cau hoi {0} dap an dung chi duoc phep tu 1 den 4", i + 1));
        }
        Exam newExam = ToExamEntity(examQuestionDto);
        newExam = examRep.save(newExam);
        int examId = newExam.getId();
        List<Question> questionEntityList = new ArrayList<>();
        for (QuestionDto questionDto : questionDtoList) {
            questionEntityList.add(ToQuestionEntity(questionDto, examId));
        }
        questionRep.saveAll(questionEntityList);
        List<User> listStudent = userRep.findAllByRoles("student");
        List<Contest> listContest = new ArrayList<>();
        for (User st : listStudent) {
            Contest nc = new Contest();
            nc.setIdExam(examId);
            nc.setIdUser(st.getId());
            listContest.add(nc);
        }
        contestRep.saveAll(listContest);
    }

    public void edit(Exam newExamEntity) throws ErrorException {
        if (Valids.isEmpty(newExamEntity.getId())) throw new ErrorException("Id Exam is Empty");
        Exam examEntity = examRep.findFirstById(newExamEntity.getId());
        if (!Valids.isEmpty(newExamEntity.getName())) examEntity.setName(newExamEntity.getName());
        if (!Valids.isEmpty(newExamEntity.getDescription())) examEntity.setDescription(newExamEntity.getDescription());
        if (!Valids.isEmpty(newExamEntity.getType())) examEntity.setType(newExamEntity.getType());
        if (!Valids.isEmpty(newExamEntity.getStartTime())) examEntity.setStartTime(newExamEntity.getStartTime());
        if (!Valids.isEmpty(newExamEntity.getEndTime())) examEntity.setEndTime(newExamEntity.getEndTime());
        if (!Valids.isEmpty(newExamEntity.getTimeDuration()))
            examEntity.setTimeDuration(newExamEntity.getTimeDuration());
        examRep.save(examEntity);
    }

    public void editAll(ExamDetailModel examDetailModel) throws ErrorException {
        if (Valids.isEmpty(examDetailModel.getId())) throw new ErrorException("Data is empty!");
        Exam preExam = examRep.findFirstById(examDetailModel.getId());
        if (preExam == null) throw new ErrorException("Data is empty!");
        if (!Valids.isEmpty(examDetailModel.getName())) preExam.setName(examDetailModel.getName());
        if (!Valids.isEmpty(examDetailModel.getDescription())) preExam.setDescription(examDetailModel.getDescription());
        if (!Valids.isEmpty(examDetailModel.getType())) preExam.setType(examDetailModel.getType());

        if (examDetailModel.getQuestion().isEmpty()) throw new ErrorException("Phai co it nhat cau hoi");
        List<Question> newQuestionList = examDetailModel.getQuestion();
        List<Question> questionList = questionRep.findByIdExam(examDetailModel.getId());
        List<Question> deleteQuestionList = new ArrayList<>();
        List<Question> saveQuestionList = new ArrayList<>();
        for (Question question : questionList) {
            boolean check = false;
            for (Question newQuestion : newQuestionList) {
                if (question.getId() == newQuestion.getId()) {
                    check = true;
                    if (!Valids.isEmpty(newQuestion.getContent())) question.setContent(newQuestion.getContent());
                    if (!Valids.isEmpty(newQuestion.getAnswer1())) question.setAnswer1(newQuestion.getAnswer1());
                    if (!Valids.isEmpty(newQuestion.getAnswer2())) question.setAnswer2(newQuestion.getAnswer2());
                    if (!Valids.isEmpty(newQuestion.getAnswer3())) question.setAnswer3(newQuestion.getAnswer3());
                    if (!Valids.isEmpty(newQuestion.getAnswer4())) question.setAnswer4(newQuestion.getAnswer4());
                    if (!Valids.isEmpty(newQuestion.getRightAnswer()) && newQuestion.getRightAnswer() >= 1 && newQuestion.getRightAnswer() <= 4)
                        question.setRightAnswer(newQuestion.getRightAnswer());
                    saveQuestionList.add(question);
                    break;
                }
            }
            if (!check) {
                deleteQuestionList.add(question);
            }
        }
        for (Question newQuestion : newQuestionList) {
            if (Valids.isEmpty(newQuestion.getId())) {
                saveQuestionList.add(newQuestion);
            }
        }
        examRep.save(preExam);
        questionRep.saveAll(saveQuestionList);
        questionRep.deleteAll(deleteQuestionList);
    }

    public void delete(int id) throws ErrorException {
        if (!examRep.existsById(id)) throw new ErrorException("Khong tim thay du lieu phu hop");
        examRep.deleteById(id);
    }
}
