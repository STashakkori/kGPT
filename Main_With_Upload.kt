// $t@$h

import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.client.OpenAIConfig
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import kotlinx.coroutines.runBlocking
import com.aallam.openai.api.file.*
import okio.Path.Companion.toPath
import okio.FileSystem

fun main(args: Array<String>) = runBlocking {
    println("Running GPT Query")
    val openAI = OpenAI(
        config = OpenAIConfig(
            token = ""
        )
    )

    val filePath = "file.csv"
    val path = filePath.toPath()
    val myFileSource = FileSource(path, FileSystem.SYSTEM)

    val uploadRequest = fileUpload {
        file = myFileSource
        purpose = Purpose("fine-tune")
    }

    val messages = mutableListOf<ChatMessage>()

    messages.add(
        ChatMessage(
            role = ChatRole.User,
            content = "What do you think of my CSV file?"
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
