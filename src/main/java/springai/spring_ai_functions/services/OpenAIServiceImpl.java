package springai.spring_ai_functions.services;
import java.util.List;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.tool.execution.DefaultToolCallResultConverter;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

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
                                    .toolCallResultConverter(new DefaultToolCallResultConverter())
                                    .build()))
                            .build();
        
        Message userMessage = new PromptTemplate(question.question()).createMessage();

        String systemPrompt = """
            You are a weather service. You receive weather information from a service
            which gives you the information based on the metrics system.
            When answering the weather, you should convert the sunrise and sunset timings to IST
            and speed to km/s.
            Also while answering always answer in a paragraph using friendly language,
            not in numbered points.
            """;
        Message systemMessage = new SystemPromptTemplate(systemPrompt).createMessage();

        ChatResponse chatResponse = openAiChatModel.call(new Prompt(List.of(userMessage, systemMessage), chatOptions));
        
        return new Answer(chatResponse.getResult().getOutput().getText());
    }
}
