package springai.spring_ai_functions.services;
import java.util.List;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import springai.spring_ai_functions.functions.WeatherServiceFunction;
import springai.spring_ai_functions.records.Answer;
import springai.spring_ai_functions.records.Question;
import springai.spring_ai_functions.records.WeatherRequest;

@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final OpenAiChatModel openAiChatModel;

    @Value("${asb.aiapp.apiNinjasKey}")
    private String apiNinjasKey;

    @Override
    public Answer getAnswer(Question question) {
        WeatherServiceFunction weatherServiceFunction = new WeatherServiceFunction(apiNinjasKey);
        OpenAiChatOptions chatOptions = OpenAiChatOptions.builder()
                            .toolCallbacks(List.of(
                                FunctionToolCallback.builder("WeatherReport", weatherServiceFunction)
                                    .description("Fetches weather information based on latitude and longitude")
                                    .inputType(WeatherRequest.class)
                                    .build()))
                            .build();
        
        Message userMessage = new PromptTemplate(question.question()).createMessage();

        ChatResponse chatResponse = openAiChatModel.call(new Prompt(List.of(userMessage), chatOptions));
        
        return new Answer(chatResponse.getResult().getOutput().getText());
    }
}
