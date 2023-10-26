// $t@$h

import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.client.OpenAIConfig
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {
    println("Running GPT Query)
    val openAI = OpenAI(
        config = OpenAIConfig(
            token = "",
        )
    )

    val messages = mutableListOf<ChatMessage>()

    messages.add(
        ChatMessage(
            role = ChatRole.User,
            content = "What is your purpose?"
        )
    )

    val chatCompletionRequest = ChatCompletionRequest(
        model = ModelId("gpt-3.5-turbo"),
        messages = messages
    )

    val response = openAI.chatCompletion(chatCompletionRequest)

    val assistantResponse = response.choices.first().message.content.orEmpty()
    println("Assistant: $assistantResponse")
}
