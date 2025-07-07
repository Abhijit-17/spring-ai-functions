package springai.spring_ai_functions.services;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import springai.spring_ai_functions.records.Answer;
import springai.spring_ai_functions.records.Question;

@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {

    private ChatModel chatModel;

    @Override
    public Answer getAnswer(Question question) {
        // Here you would implement the logic to call the OpenAI API
        // and return an Answer based on the provided Question.
        // For now, we will return a dummy answer.
        return new Answer("This is a dummy answer to the question: " + question.question());
    }

}
