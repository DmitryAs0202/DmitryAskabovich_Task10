package parsers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMParser {

  public static final String TXT_PATH = "src/booksDOMContent.txt";
  private static final String PARSING = "DOM parsing takes ";

  public static void allAuthors(String path)
      throws ParserConfigurationException, IOException, SAXException {
    long start = System.currentTimeMillis();
    File file = new File(path);
    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(file);
    NodeList authors = document.getElementsByTagName("author");
    System.out.println("----------Authors in document----------");
    for (int i = 0; i < authors.getLength(); i++) {
      System.out.println(authors.item(i).getTextContent());
    }
    long finish = System.currentTimeMillis();
    System.out.println(PARSING + (finish - start) + " ms.");
  }

  public static void numberOfBooks(String path)
      throws ParserConfigurationException, IOException, SAXException {
    long start = System.currentTimeMillis();
    File file = new File(path);
    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(path);
    NodeList books = document.getElementsByTagName("book");
    System.out.println("------------------------------------");
    System.out.println("Number of books in document: " + books.getLength());
    long finish = System.currentTimeMillis();
    System.out.println(PARSING + (finish - start) + " ms.");
  }

  public static void getBookTitleByNumber(String path, int number)
      throws ParserConfigurationException, IOException, SAXException {
    long start = System.currentTimeMillis();
    number--;
    File file = new File(path);
    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(path);
    NodeList title = document.getElementsByTagName("title");
    if (number >= title.getLength()) {
      System.out.println("NO SUCH TITLE");
      return;
    }
    System.out.println("------------------------------------");
    System.out.println(title.item(number).getTextContent());
    long finish = System.currentTimeMillis();
    System.out.println(PARSING + (finish - start) + " ms.");

  }

  public static void getBooksByAuthor(String path, String authorName)
      throws ParserConfigurationException, IOException, SAXException {
    long start = System.currentTimeMillis();
    File file = new File(path);
    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(file);
    NodeList books = document.getElementsByTagName("book");
    System.out.println("------------------------------------");
    System.out.println("Произведения автора " + authorName + ":");
    for (int i = 0; i < books.getLength(); i++) {
      Node node = books.item(i);
      Element element = (Element) node;
      String author = element.getElementsByTagName("author").item(0).getTextContent();
      if (author.equals(authorName)) {
        String title = element.getElementsByTagName("title").item(0).getTextContent();
        System.out.println(title);
      }
    }
    long finish = System.currentTimeMillis();
    System.out.println(PARSING + (finish - start) + " ms.");
  }

  public static void getAllInformation(String path)
      throws ParserConfigurationException, IOException, SAXException {
    long start = System.currentTimeMillis();
    File file = new File(path);
    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(file);
    NodeList books = document.getElementsByTagName("book");
    System.out.println("------------------------------------");
    for (int i = 0; i < books.getLength(); i++) {
      Node node = books.item(i);
      Element element = (Element) node;
      String author = element.getElementsByTagName("author").item(0).getTextContent();
      String title = element.getElementsByTagName("title").item(0).getTextContent();
      String outline = element.getElementsByTagName("shot_content").item(0).
          getTextContent();
      System.out.println("Автор: " + author);
      System.out.println("Название Книги: " + title);
      System.out.println("Краткое Содержание: " + outline);
      System.out.println();
    }
    long finish = System.currentTimeMillis();
    System.out.println(PARSING + (finish - start) + " ms.");
  }

  public static void makeTxt(String XMLPath)
      throws ParserConfigurationException, IOException, SAXException {
    long start = System.currentTimeMillis();
    File file1 = new File(XMLPath);
    File file2 = new File(TXT_PATH);
    PrintWriter pw = new PrintWriter(file2);

    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(file1);
    NodeList books = document.getElementsByTagName("book");
    System.out.println("------------------------------------");
    for (int i = 0; i < books.getLength(); i++) {
      Node node = books.item(i);
      Element element = (Element) node;
      String author = element.getElementsByTagName("author").item(0).getTextContent();
      String title = element.getElementsByTagName("title").item(0).getTextContent();
      String outline = element.getElementsByTagName("shot_content").item(0).
          getTextContent();
      pw.println("Автор: " + author);
      pw.println("Название Книги: " + title);
      pw.println("Краткое Содержание: " + outline);
      pw.println(" ");
    }
    pw.close();
    System.out.println("Текстовый файл создан, путь: " + TXT_PATH);
    long finish = System.currentTimeMillis();
    System.out.println(PARSING + (finish - start) + " ms.");
  }

  public static void getAllAuthorsAndTitles(String path)
      throws ParserConfigurationException, IOException, SAXException {
    File file = new File(path);
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(file);
    NodeList authors = document.getElementsByTagName("author");
    NodeList titles = document.getElementsByTagName("title");

    for (int i = 0; i < authors.getLength(); i++) {
      System.out.println(authors.item(i).getTextContent());
    }
    System.out.println();
    for (int i = 0; i < titles.getLength(); i++) {
      System.out.println(titles.item(i).getTextContent());
    }

  }

}
