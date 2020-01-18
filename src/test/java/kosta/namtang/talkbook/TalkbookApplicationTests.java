package kosta.namtang.talkbook;

import kosta.namtang.talkbook.common.BookCode;
import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.model.domain.Category;
import kosta.namtang.talkbook.repository.BookRepository;
import kosta.namtang.talkbook.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.codec.EncodingException;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.transaction.Transactional;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

/**
 * Naver Book Api를 이용해 DB에 데이터를 담는 메소드
 */
@SpringBootTest
class TalkbookApplicationTests {

	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private CategoryRepository catgRepo;

	@Test
	void contextLoads() throws UnsupportedEncodingException {

		StringBuilder sb;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc;

		String clientId = "7FZwEYvppDhLr3RUcauR";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "fy7bRsApnt";// 애플리케이션 클라이언트 시크릿값";\



//        카테고리 정보
//        110	시/에세이
//        110010	한국시 1
//        110020	외국시 2
//        110030	인물 에세이  3
//        110040	여행 에세이  4
//        110050	성공 에세이  5
//        110060	독서 에세이  6
//        110070	명상 에세이  7
//        110080	그림/포토 에세이   8
//        110090	연애/사랑 에세이   9
//        110100	삶의 지혜/명언    10

//        검색 옵션
//        책 제목(d_titl), 저자명(d_auth), 목차(d_cont), ISBN(d_isbn), 출판사(d_publ)
        //bookTitle를 List로 만들어 for문을 통해 일괄 검색하기
//        List<bookApiDomain> list = new ArrayList<bookApiDomain>();
//        list.add(new bookApiDomain(URLEncoder.encode("aa", "UTF-8"),1L));

        //하나의 질의문으로 검색하기
      String text = URLEncoder.encode("aa", "UTF-8");
		String catgNum = "110100";
		Long catgNumId = 10L;

		//대상 카테고리

		try {
//			String apiURL = "https://openapi.naver.com/v1/search/book.xml?query=java&display=2&start=1";
			//기본검색
//			String apiURL = "https://openapi.naver.com/v1/search/book_adv.xml?&d_catg=110010&d_cont=2017&display=100&start=1";
//			한글깨짐
//          String apiURL = "https://openapi.naver.com/v1/search/book_adv.xml?d_titl=" + text + "&display=1&start=1";
            //상세검색

//            일괄검색을 위해 for문 사용
//            for(int a=0;a<=4;a++) {
                Category category = catgRepo.findById(catgNumId).orElse(null);

                String apiURL = "https://openapi.naver.com/v1/search/book_adv.xml?d_titl=" + text + "&display=1&start=1";

                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("X-Naver-Client-Id", clientId);
                con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if (responseCode == 200) {
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                sb = new StringBuilder();
                String line;
                String result = "";

                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                    result = result + line.trim();
                }
                br.close();
                con.disconnect();
                System.out.println(sb);


                // xml 파싱하기
                InputSource is = new InputSource(new StringReader(result));
                builder = factory.newDocumentBuilder();
                doc = builder.parse(is);
                XPathFactory xpathFactory = XPathFactory.newInstance();
                XPath xpath = xpathFactory.newXPath();
                // XPathExpression expr = xpath.compile("/response/body/items/item");
                XPathExpression expr = xpath.compile("//item");
                NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

                String author = null;
                String desc = null;
                String img = null;
                String isbn = null;
                BigDecimal price = null;
                String pubdate = null;
                String publisher = null;
                String title = null;

//                카테고리 목록 insert
//			catgRepo.save(new Category(1L,"한국시",null));
//			catgRepo.save(new Category(2L,"외국시",null));
//			catgRepo.save(new Category(3L,"인물 에세이",null));
//			catgRepo.save(new Category(4L,"여행 에세이",null));
//			catgRepo.save(new Category(5L,"성공 에세이",null));
//			catgRepo.save(new Category(6L,"독서 에세이",null));
//			catgRepo.save(new Category(7L,"명상 에세이",null));
//			catgRepo.save(new Category(8L,"그림/포토 에세이",null));
//			catgRepo.save(new Category(9L,"연애/사랑 에세이",null));
//			catgRepo.save(new Category(10L,"삶의 지혜/명언",null));


                for (int i = 0; i < nodeList.getLength(); i++) {
                    NodeList child = nodeList.item(i).getChildNodes();
                    for (int j = 0; j < child.getLength(); j++) {
                        Node node = child.item(j);

//					System.out.println("현재 노드 이름 : " + node.getNodeName());
//					System.out.println("현재 노드 타입 : " + node.getNodeType());
//					System.out.println("현재 노드 값 : " + node.getTextContent());
//					System.out.println("현재 노드 네임스페이스 : " + node.getPrefix());
//					System.out.println("현재 노드의 다음 노드 : " + node.getNextSibling());
//					System.out.println();

                        if (j == 0) {
                            title = node.getTextContent();
                            title = title.replace("<b>", "");
                            title = title.replace("</b>", "");
//						out.println(title);
                        } else if (j == 2) {
                            img = node.getTextContent();
                            out.println(img);
                        } else if (j == 3) {
                            author = node.getTextContent();
                            author = author.replace("<b>", "");
                            author = author.replace("</b>", "");
                        }
                        else if (j == 4) price = new BigDecimal(node.getTextContent());
                        else if (j == 6) {
                            publisher = node.getTextContent();
                            publisher = publisher.replace("<b>", "");
                            publisher = publisher.replace("</b>", "");
                        } else if (j == 7) pubdate = node.getTextContent();
                        else if (j == 8) isbn = node.getTextContent();
                        else if (j == 9) {
                            desc = node.getTextContent();
                            desc = desc.replace("<b>", "");
                            desc = desc.replace("</b>", "");
                        }
                    }

                    Book book = new Book(null, title, img, author, price, publisher, desc, pubdate, BookCode.Theme4.getValue(), isbn, category);
                    System.out.println(book);
				bookRepo.save(book);
                }
//            }


		} catch (Exception e) {
			out.print(e.getMessage());
		}

	}
}

