package main.utils;

import main.DB.models.Question;
import org.junit.*;

import java.io.File;



/**
 * Created by Shoma on 15.04.2017.
 */
public class JaxbParserTest {
    private Parser parser;
    private File file;

    @Before
    public void setUp() throws Exception {
        parser = new JaxBParser();
        file = new File("question.xml");
    }

    @Test
    public void testGetObject() throws Exception {
        Question question = (Question) parser.getObject(file, Question.class);
        System.out.println(question);
    }

    @Test
    public void testSaveObject() throws Exception {
        Question question = new Question();

        question.setQuest("Кто в лесу самый сильный?");
        question.setAnswer("Медведь");
        question.setScore(15);

        parser.saveObject(file,question);
    }
}
