package execution;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.parsers.SAXParser;
import org.xml.sax.SAXException;
import parsers.DOMParser;
import parsers.SAXParserEx;

public class Main {

  public static final String PATH = "src/books1.xml";
  public static final int FIVE = 5;
  public static final String AUTHOR = "Иван Бунин";

  public static void main(String[] args) {
    try {
      DOMParser.allAuthors(PATH);

      SAXParserEx.allAuthors(PATH);

      DOMParser.numberOfBooks(PATH);

      SAXParserEx.numberOfBooks(PATH);

      DOMParser.getBooksByAuthor(PATH, AUTHOR);

      SAXParserEx.getBooksByAuthor(PATH, AUTHOR);

      DOMParser.getBookTitleByNumber(PATH, FIVE);

      SAXParserEx.getBookTitleByNumber(PATH, FIVE);

      DOMParser.getAllInformation(PATH);

      SAXParserEx.getAllInformation(PATH);

      DOMParser.makeTxt(PATH);

      SAXParserEx.makeTxt(PATH);

      DOMParser.getAllAuthorsAndTitles(PATH);

      SAXParserEx.getThirdBookAndAuthor(PATH);


    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    }


  }

}
