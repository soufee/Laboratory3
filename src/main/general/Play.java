package main.general;

import main.DB.models.Question;
import main.DB.models.Questions;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shoma on 16.04.2017.
 */
public class Play {
    public static int qNumb = 0;
    public static int score = 0;
   List<Question> list = new ArrayList<Question>();
    File file =    new File("question.xml");
    Unmarshaller unmarshaller = JAXBContext.newInstance(Questions.class).createUnmarshaller();
    Questions questions = (Questions) unmarshaller.unmarshal(file);


    public Play() throws JAXBException {
    }

    Question question;
    String q;
    String ans;
    public void startGame() {

 if (qNumb>=questions.getQuestions().size())
 {
     GamePlay.flag = false;
     System.out.println("Вопросы закончились. ");
     System.out.println(" Ваши очки "+score+" ");
 } else {
     System.out.println("Введите полный текст зашифрованной загадки:");
    question = questions.getQuestions().get(qNumb);
     qNumb++;
     q = question.getQuest();
    ans = question.getAnswer();
     System.out.println(q);
 }
    }
    public void checkAnswer(String line)
    {
if (line.equalsIgnoreCase(ans))
{
    score+=question.getScore();
    System.out.println("Это правильный ответ. Ваши очки - "+score+". Следующий вопрос: ");
    startGame();
} else
    {
        System.out.println("Ответ неверный. Попробуйте еще раз:");
    }
    }

}
