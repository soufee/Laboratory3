package main.general;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Shoma on 16.04.2017.
 */
public class GamePlay {
    public static final String help = "play - начало игры. \n exit - выход из игры\n ? - помощь";
    public static boolean flag = true;
    public static void main(String[] args) throws IOException, JAXBException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Вас приветствует игра 'Угадай пословицу'.\n" +
                " В аббревиатурах, которые вы будете получать, зашифрованы известные русские пословицы и поговорки.\n"+
                "Ваша задача - отгадать эти фразы и ввести их\n"+
                help);

       Play play = new Play();

        while (flag)
        {
            String line = bufferedReader.readLine();
            switch (line){
                case("?"):
                    System.out.println(help);
                    break;
                case ("play"):
                    play.startGame();
                    break;
                case ("exit"):
                    flag = false;
                    return;
                default: play.checkAnswer(line);
            }

        }


    }
}
