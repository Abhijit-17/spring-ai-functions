package springai.spring_ai_functions.services;

import springai.spring_ai_functions.records.Answer;
import springai.spring_ai_functions.records.Question;

public interface OpenAIService {

    Answer getAnswer(Question question);

}
