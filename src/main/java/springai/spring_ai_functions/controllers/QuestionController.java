package springai.spring_ai_functions.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springai.spring_ai_functions.records.Question;
import springai.spring_ai_functions.records.Answer;
import springai.spring_ai_functions.services.OpenAIService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final OpenAIService openAIService;

    //constructor injection of OpenAIService
    public QuestionController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @GetMapping("/healthCheck")
    public String getMethodName() {
        return "OK";
    }

    @PostMapping("/ask")
    public Answer postMethodName(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }
    
    

}
