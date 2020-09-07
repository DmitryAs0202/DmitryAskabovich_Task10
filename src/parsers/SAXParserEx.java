package parsers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import javax.xml.parsers.SAXParser;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserEx extends DefaultHandler {

  private static final String TXT_PATH = "src/booksSAXContent.txt";
  private static final String PARSING = "SAX parsing takes ";
  private String actualTag = "";
  private static List<String> authors = new ArrayList<>();
  private static List<String> books = new ArrayList<>();
  private static List<String> titles = new ArrayList<>();
  private static List<String> content = new ArrayList<>();
  private static int bookCounter = 0;
  private static String previous = "";
  private static String currentAuthor = "";

  @Override
  public void startDocument() throws SAXException {
    super.startDocument();
  }

  @Override
  public void endDocument() throws SAXException {
    super.endDocument();
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes)
      throws SAXException {
    actualTag = qName;
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    actualTag = "";
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    if (actualTag.equals("author")) {
      String value = new String(ch, start, length);
      authors.add(value);
      if (value.equals(currentAuthor)) {
        books.add(previous);
      }

    }
    if (actualTag.equals("book")) {
      bookCounter++;
    }
    if (actualTag.equals("title")) {
      String title = new String(ch, start, length);
      previous = title;
      titles.add(title);
    }
    if (actualTag.equals("shot_content")) {
      content.add(new String(ch, start, length));
    }


  }

  public static void allAuthors(String path)
      throws ParserConfigurationException, SAXException, IOException {
    long start = System.currentTimeMillis();
    clearVariables();
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser parserSAX = factory.newSAXParser();
    SAXParserEx saxParserEx = new SAXParserEx();
    parserSAX.parse(new File(path), saxParserEx);
    System.out.println("----------Authors in document----------");
    authors.forEach(author -> System.out.println(author));
    long finish = System.currentTimeMillis();
    System.out.println(PARSING + (finish - start) + " ms.");
  }

  public static void numberOfBooks(String path)
      throws ParserConfigurationException, SAXException, IOException {
    long start = System.currentTimeMillis();
    clearVariables();
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser parserSAX = factory.newSAXParser();
    SAXParserEx saxParserEx = new SAXParserEx();
    parserSAX.parse(new File(path), saxParserEx);
    System.out.println("------------------------------------");
    System.out.println("Number of books in document: " + bookCounter);
    long finish = System.currentTimeMillis();
    System.out.println(PARSING + (finish - start) + " ms.");
  }

  public static void getBooksByAuthor(String path, String authorName)
      throws ParserConfigurationException, SAXException, IOException {
    long start = System.currentTimeMillis();
    clearVariables();
    currentAuthor = authorName;
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser parserSAX = factory.newSAXParser();
    SAXParserEx saxParserEx = new SAXParserEx();
    parserSAX.parse(new File(path), saxParserEx);
    System.out.println("------------------------------------");
    System.out.println("Произведения автора " + authorName + ":");
    books.forEach(book -> System.out.println(book));
    long finish = System.currentTimeMillis();
    System.out.println(PARSING + (finish - start) + " ms.");
  }

  public static void getBookTitleByNumber(String path, int number)
      throws ParserConfigurationException, SAXException, IOException {
    long start = System.currentTimeMillis();
    clearVariables();
    number--;
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser parserSAX = factory.newSAXParser();
    SAXParserEx saxParserEx = new SAXParserEx();
    parserSAX.parse(new File(path), saxParserEx);
    System.out.println("------------------------------------");
    if (number >= titles.size()) {
      System.out.println("NO SUCH TITLE");
      return;
    }
    System.out.println(titles.get(number));
    long finish = System.currentTimeMillis();
    System.out.println(PARSING + (finish - start) + " ms.");
  }

  public static void getAllInformation(String path)
      throws ParserConfigurationException, SAXException, IOException {
    long start = System.currentTimeMillis();
    clearVariables();
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser parserSAX = factory.newSAXParser();
    SAXParserEx saxParserEx = new SAXParserEx();
    parserSAX.parse(new File(path), saxParserEx);
    System.out.println();
    for (int i = 0; i < bookCounter; i++) {
      System.out.println("Автор: " + authors.get(i));
      System.out.println("Название Книги: " + titles.get(i));
      System.out.println("Краткое содержание: " + content.get(i));
      System.out.println();
    }
    long finish = System.currentTimeMillis();
    System.out.println(PARSING + (finish - start) + " ms.");
  }

  public static void makeTxt(String XMLPath)
      throws IOException, ParserConfigurationException, SAXException {
    long start = System.currentTimeMillis();
    File file = new File(TXT_PATH);
    PrintWriter pw = new PrintWriter(file);
    clearVariables();
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser parserSAX = factory.newSAXParser();
    SAXParserEx saxParserEx = new SAXParserEx();
    parserSAX.parse(new File(XMLPath), saxParserEx);
    for (int i = 0; i < bookCounter; i++) {
      pw.println("Автор: " + authors.get(i));
      pw.println("Название Книги: " + titles.get(i));
      pw.println("Краткое содержание: " + content.get(i));
      pw.println();
    }
    pw.close();
    long finish = System.currentTimeMillis();
    System.out.println("Текстовый файл создан, путь: " + TXT_PATH);
    System.out.println(PARSING + (finish - start) + " ms.");
  }

  public static void getThirdBookAndAuthor(String path)
      throws ParserConfigurationException, SAXException, IOException {
    clearVariables();
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser parserSAX = factory.newSAXParser();
    SAXParserEx saxParserEx = new SAXParserEx();
    parserSAX.parse(new File(path), saxParserEx);
    System.out.println();
    System.out.println(authors.get(2) + " - " + titles.get(2));
  }

  private static void clearVariables() {
    authors.clear();
    titles.clear();
    content.clear();
    books.clear();
    bookCounter = 0;
  }
}
