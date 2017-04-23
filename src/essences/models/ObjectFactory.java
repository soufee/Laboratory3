
package essences.models;

import listeners.MySessionListener;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the essences package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Gamer_QNAME = new QName("", "Gamer");
    private final static QName _Question_QNAME = new QName("", "Question");
    private static Logger userLogger = Logger.getLogger(MySessionListener.class);
    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: essences
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Gamers }
     * 
     */
    public Gamers createGamers() {
        userLogger.debug("Making an object Gamers: ");
        return new Gamers();
    }
    public Questions createQuestions() {
        userLogger.debug("Making an object Questions: ");
        return new Questions();}

    /**
     * Create an instance of {@link Gamer }
     * 
     */
    public Gamer createGamer(String name, String password, String email) {
      Gamer gamer = new Gamer();
      gamer.setNiackname(name);
      gamer.setPassword(password);
      gamer.setEmail(email);
      gamer.setScore(0);
      gamer.setAdmin(false);
      gamer.setBlocked(false);
      userLogger.debug("Made an object Gamer:"+gamer);
        return gamer;

    }

    public Question createQuestion(String quest, String answer, String hint, int score)
    {
        Question question = new Question();
        question.setQuest(quest);
        question.setAnswer(answer);
        question.setHint(hint);
        question.setScore(score);
        userLogger.debug("Made an object Question:"+question);
        //Надо добавить рассылку емайла админу
        return question;
    }



    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Gamer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Gamer")
    public JAXBElement<Gamer> createGamer(Gamer value) {
        userLogger.debug("");
        return new JAXBElement<Gamer>(_Gamer_QNAME, Gamer.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "Question")
    public JAXBElement<Question> createQuestion(Question value) {
        userLogger.debug("");
        return new JAXBElement<Question>(_Question_QNAME, Question.class, null, value);
    }

}
