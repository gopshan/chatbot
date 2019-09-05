package com.example.chatbot;

import com.example.chatbot.controller.OptionRepository;
import com.example.chatbot.controller.QuestionController;
import com.example.chatbot.controller.QuestionRepository;
import com.example.chatbot.model.Options;
import com.example.chatbot.model.Question;
import com.example.chatbot.service.QuestionService;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class QuestionControllerTest {

    /* @BeforeClass
    public static void beforeClass() {
        System.out.println("Executing test cases for Question Controller");
        System.out.println("---------------------------------------------");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("---------------------------------------------");
    }
*/
    @Test
    public void getAllQuestionsTest() throws FileNotFoundException {
        System.out.println("Test for getAllQuestions");
        System.out.println();
        final JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("src/main/java/question.json");
        QuestionController qc = new QuestionController();
        QuestionRepository qr = Mockito.mock(QuestionRepository.class);
        Options op1 = new Options();
        op1.setOption_no("1");
        op1.setOptionName("Press 1 for Modular");
        Options op2 = new Options();
        op2.setOption_no("2");
        op2.setOptionName("Press 2 for Semi-Modular");
        Options op3 = new Options();
        op3.setOption_no("3");
        op3.setOptionName("Press 3 for Pre-Configured");
        Question q = new Question(1,"What monitor type you want?");
        q.setOptions(Arrays.asList(op1,op2,op3));
        Mockito.when(qr.findAll()).thenReturn(Arrays.asList(q));
        qc.setDAO(qr);

        ResponseEntity<List<Question>> output = qc.getAllQuestions();
        if (output.getStatusCode()==HttpStatus.OK){
            List<Question> list = output.getBody();
            assertEquals(list.get(0),q);
        }



        //ResponseEntity<List<Question>> result = ;
        //assertEquals(result,output);
    }

    @Test
    public void findOneQuestionTestSuccessful() {
        System.out.println("Successful Test for findOneQuestion");
        System.out.println();
        QuestionController qc = new QuestionController();
        QuestionRepository qr = Mockito.mock(QuestionRepository.class);

        Question q = new Question(1,"What monitor type you want?");
        // Mockito.when(qr.findById(1)).thenReturn((Optional.ofNullable(q)));
         Mockito.when(qr.findById(1)).thenReturn((Optional.ofNullable(q)));
        qc.setDAO(qr);
        ResponseEntity<Optional<Question>> output = qc.findOneQuestion(1);

        if (output.getStatusCode()==HttpStatus.OK)
            assertEquals(output.getBody(), Optional.of(q));
    }

    @Test
    public void findOneQuestionTestFail() {
        System.out.println("Failure Test for findOneQuestion");
        System.out.println();
        QuestionController qc = new QuestionController();
        QuestionRepository qr = Mockito.mock(QuestionRepository.class);
        Question q = new Question(11,"What monitor type you want?");
        Mockito.when(qr.findById(11)).thenReturn(null);
        qc.setDAO(qr);
        ResponseEntity<Optional<Question>> output = qc.findOneQuestion(11);
        if (output.getStatusCode()==HttpStatus.OK){
            assertEquals(null,output.getBody());
        }
    }
    @Test
    public void findNextQuestion1TestValidQidValidOid() {
        System.out.println("Test for find next question when both question id and option id are valid");
        System.out.println();
        QuestionController qc = new QuestionController();
        OptionRepository or = Mockito.mock(OptionRepository.class);
        QuestionService qs = Mockito.mock(QuestionService.class);
        Question q = new Question(1,"What monitor type you want?");
        Mockito.when(qs.validateOption(1,"1")).thenReturn(true);
        Mockito.when(or.find("1", 1)).thenReturn(0);

        qc.setOptionRepository(or);
        qc.setQuestionService(qs);

        ResponseEntity<Optional<Question>> output = qc.findNextQuestion1(1,"1");
        assertEquals(HttpStatus.FOUND, output.getStatusCode());
    }

    @Test
    public void findNextQuestion1TestValidQidInvalidOid() {
        System.out.println("Test for find next question when question id is valid and option id is invalid");
        System.out.println();
        QuestionController qc = new QuestionController();
        OptionRepository or = Mockito.mock(OptionRepository.class);
        QuestionRepository qr = Mockito.mock(QuestionRepository.class);
        QuestionService qs = Mockito.mock(QuestionService.class);
        Question q = new Question(1,"What monitor type you want?");
        Mockito.when(qs.validateOption(1,"12")).thenReturn(false);

        qc.setOptionRepository(or);
        qc.setQuestionService(qs);

        ResponseEntity<Optional<Question>> output = qc.findNextQuestion1(1,"12");
        assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
    }

    @Test
    public void findNextQuestion1TestInvalidQid() {
        System.out.println("Test for find next question when question id is invalid");
        System.out.println();
        QuestionController qc = new QuestionController();
        OptionRepository or = Mockito.mock(OptionRepository.class);
        QuestionRepository qr = Mockito.mock(QuestionRepository.class);
        QuestionService qs = Mockito.mock(QuestionService.class);
        Question q = new Question(100,"What monitor type you want?");
        Mockito.when(qs.validateOption(100,"1")).thenReturn(false);
        Mockito.when(or.find("1", 100)).thenReturn(0);

        qc.setOptionRepository(or);
        qc.setQuestionService(qs);

        ResponseEntity<Optional<Question>> output = qc.findNextQuestion1(100,"12");
        assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
    }
    @Test
     public void findNextQuestion2TestAllParametersValid() {
         System.out.println("Test for find next question when all input parameters are valid");
         System.out.println();
         QuestionController qc = new QuestionController();
         OptionRepository or = Mockito.mock(OptionRepository.class);
         QuestionRepository qr = Mockito.mock(QuestionRepository.class);
         QuestionService qs = Mockito.mock(QuestionService.class);

         Question q = new Question(1,"What monitor type you want?");

         Mockito.when(or.find("1",1)).thenReturn(0);
         Mockito.when(qs.validateOption(0,"2")).thenReturn(true);
         Mockito.when(or.find("2",0)).thenReturn(200000);
         Mockito.when(qr.findById(200000)).thenReturn(Optional.of(q));

         qc.setOptionRepository(or);
         qc.setQuestionService(qs);
         qc.setDAO(qr);

         ResponseEntity<Optional<Question>> output = qc.findNextQuestion2(1,"1","2");
         assertEquals(HttpStatus.OK, output.getStatusCode());
         assertEquals(Optional.of(q),output.getBody());


     }

    @Test
    public void findNextQuestion2TestQidIsInvalid() {
        System.out.println("Test for find next question when question id is invalid");
        System.out.println();
        QuestionController qc = new QuestionController();
        OptionRepository or = Mockito.mock(OptionRepository.class);
        QuestionRepository qr = Mockito.mock(QuestionRepository.class);
        QuestionService qs = Mockito.mock(QuestionService.class);
        Question q = new Question(100,"What monitor type you want?");

        Mockito.when(or.find("1",100)).thenReturn(0);
        Mockito.when(qs.validateOption(0,"2")).thenReturn(false);

        qc.setOptionRepository(or);
        qc.setQuestionService(qs);
        qc.setDAO(qr);

        ResponseEntity<Optional<Question>> output = qc.findNextQuestion2(100,"1","2");
        assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());


    }

    @Test
    public void findNextQuestion2TestQidIsValidOid1IsInvalid() {
        System.out.println("Test for find the next question when question id is valid but first option id is invalid");
        System.out.println();
        QuestionController qc = new QuestionController();
        OptionRepository or = Mockito.mock(OptionRepository.class);
        QuestionRepository qr = Mockito.mock(QuestionRepository.class);
        QuestionService qs = Mockito.mock(QuestionService.class);
        Question q = new Question(1,"What monitor type you want?");

        Mockito.when(or.find("1",100)).thenReturn(0);
        Mockito.when(qs.validateOption(0,"2")).thenReturn(false);

        qc.setOptionRepository(or);
        qc.setQuestionService(qs);
        qc.setDAO(qr);

        ResponseEntity<Optional<Question>> output = qc.findNextQuestion2(100,"1","2");
        assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());

    }

    @Test
    public void findNextQuestion2TestQidIsValidOid1IsValidOid2IsInvalid() {
        System.out.println("Test for find next question when question id is valid, first option id is valid but second option id is invalid");
        System.out.println();
        QuestionController qc = new QuestionController();
        OptionRepository or = Mockito.mock(OptionRepository.class);
        QuestionRepository qr = Mockito.mock(QuestionRepository.class);
        QuestionService qs = Mockito.mock(QuestionService.class);
        Question q = new Question(1,"What monitor type you want?");
        // Mockito.when(or.find("2",1)).thenReturn();
        Mockito.when(qs.validateOption(0,"100")).thenReturn(false);

        qc.setOptionRepository(or);
        qc.setQuestionService(qs);
        qc.setDAO(qr);

        ResponseEntity<Optional<Question>> output = qc.findNextQuestion2(1,"100","1");
        assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());

    }
    @Test
    public void findMonitorNameTestAllParametersValid() {
        System.out.println("Test for find monitor when all input parameters are valid");
        System.out.println();
        QuestionController qc = new QuestionController();
        OptionRepository or = Mockito.mock(OptionRepository.class);
        QuestionRepository qr = Mockito.mock(QuestionRepository.class);
        QuestionService qs = Mockito.mock(QuestionService.class);
        //  Mockito.when(qs.validateOption(1,"2")).thenReturn(true);
        // Mockito.when(or.find("2", 1)).thenReturn(0);
        qc.setOptionRepository(or);
        qc.setQuestionService(qs);
        ResponseEntity<Optional<Question>> output = qc.findNextQuestion2(1,"1","2");
        assertEquals(HttpStatus.FOUND, output.getStatusCode());


    }

    @Test
    public void findMonitorNameTestQidIsInvalid() {
        System.out.println("Test for find monitor when question id is invalid");
        System.out.println();
        QuestionController qc = new QuestionController();
        OptionRepository or = Mockito.mock(OptionRepository.class);
        QuestionRepository qr = Mockito.mock(QuestionRepository.class);
        QuestionService qs = Mockito.mock(QuestionService.class);
        //  Mockito.when(qs.validateOption(1,"2")).thenReturn(true);
        // Mockito.when(or.find("2", 1)).thenReturn(0);
        qc.setOptionRepository(or);
        qc.setQuestionService(qs);
        ResponseEntity<Optional<Question>> output = qc.findNextQuestion2(1,"1","2");
        assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());


    }

    @Test
    public void findMonitorNameTestQidIsValidOid1IsInvalid() {
        System.out.println("Test for find monitor when only question id is valid but first option id is invalid");
        System.out.println();
        QuestionController qc = new QuestionController();
        OptionRepository or = Mockito.mock(OptionRepository.class);
        QuestionRepository qr = Mockito.mock(QuestionRepository.class);
        QuestionService qs = Mockito.mock(QuestionService.class);
        //  Mockito.when(qs.validateOption(1,"2")).thenReturn(true);
        // Mockito.when(or.find("2", 1)).thenReturn(0);
        qc.setOptionRepository(or);
        qc.setQuestionService(qs);
        ResponseEntity<Optional<Question>> output = qc.findNextQuestion2(1,"1","2");
        assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());


    }

    @Test
    public void findMonitorNameTestQidIsValidOid1IsValidOid2IsInvalid() {
        System.out.println("Test for find next question when question id is valid, first option id is valid but second option id is invalid");
        System.out.println();
        QuestionController qc = new QuestionController();
        OptionRepository or = Mockito.mock(OptionRepository.class);
        QuestionRepository qr = Mockito.mock(QuestionRepository.class);
        QuestionService qs = Mockito.mock(QuestionService.class);
        //  Mockito.when(qs.validateOption(1,"2")).thenReturn(true);
        // Mockito.when(or.find("2", 1)).thenReturn(0);
        qc.setOptionRepository(or);
        qc.setQuestionService(qs);
        ResponseEntity<Optional<Question>> output = qc.findNextQuestion2(1,"1","2");
        assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());


    }

    @Test
    public void findMonitorNameTestQidIsValidOid1IsValidOid2IsValidOid3IsInvalid() {
        System.out.println("Test for find next question when question id, first and second option id are valid but third option id is invalid");
        System.out.println();
        QuestionController qc = new QuestionController();
        OptionRepository or = Mockito.mock(OptionRepository.class);
        QuestionRepository qr = Mockito.mock(QuestionRepository.class);
        QuestionService qs = Mockito.mock(QuestionService.class);
        //  Mockito.when(qs.validateOption(1,"2")).thenReturn(true);
        // Mockito.when(or.find("2", 1)).thenReturn(0);
        qc.setOptionRepository(or);
        qc.setQuestionService(qs);
        ResponseEntity<Optional<Question>> output = qc.findNextQuestion2(1,"1","2");
        assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());


    }

}


