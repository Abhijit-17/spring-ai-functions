package springai.spring_ai_functions.services;

import springai.spring_ai_functions.records.Answer;
import springai.spring_ai_functions.records.Question;
import springai.spring_ai_functions.records.WeatherRequest;
import springai.spring_ai_functions.records.WeatherResponse;

public interface OpenAIService {

    Answer getAnswer(Question question);

}
