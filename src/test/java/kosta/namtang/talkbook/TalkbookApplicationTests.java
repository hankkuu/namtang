package kosta.namtang.talkbook;

import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.model.domain.Category;
import kosta.namtang.talkbook.repository.BookRepository;
import kosta.namtang.talkbook.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

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


@SpringBootTest
class TalkbookApplicationTests {

	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private CategoryRepository catgRepo;

	@Test
	void contextLoads() {

		StringBuilder sb;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc;

		String clientId = "7FZwEYvppDhLr3RUcauR";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "fy7bRsApnt";// 애플리케이션 클라이언트 시크릿값";\
//        int display = 100; // 검색결과갯수. 최대100개
		try {
			String apiURL = "https://openapi.naver.com/v1/search/book.xml?query=java&display=2&start=1";

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
//            System.out.println(sb);



			// xml 파싱하기
			InputSource is = new InputSource(new StringReader(result));
			builder = factory.newDocumentBuilder();
			doc = builder.parse(is);
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			// XPathExpression expr = xpath.compile("/response/body/items/item");
			XPathExpression expr = xpath.compile("//item");
			NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

			String author= null;
			String desc= null;
			String img= null;
			String isbn= null;
			int price = 0;
			String pubdate= null;
			String publisher= null;
			String title = null;

			Category category = catgRepo.findById(1L).orElse(null);
			System.out.println(category);

			for (int i = 0; i < nodeList.getLength(); i++) {
				NodeList child = nodeList.item(i).getChildNodes();
				for (int j = 0; j < child.getLength(); j++) {
					Node node = child.item(j);
					System.out.println("현재 노드 이름 : " + node.getNodeName());
					System.out.println("현재 노드 타입 : " + node.getNodeType());
					System.out.println("현재 노드 값 : " + node.getTextContent());
					System.out.println("현재 노드 네임스페이스 : " + node.getPrefix());
					System.out.println("현재 노드의 다음 노드 : " + node.getNextSibling());
					System.out.println();
//
//					if(i==0) {
//						title = node.getTextContent();
//					} else if(i==2) img = node.getTextContent();
//					else if(i==3) author = node.getTextContent();
//					else if(i==4) price = Integer.parseInt(node.getTextContent());
//					else if(i==6) publisher = node.getTextContent();
//					else if(i==7) pubdate = node.getTextContent();
//					else if(i==8) isbn = node.getTextContent();
//					else if(i==9) desc = node.getTextContent();
//
//					Book book = new Book(null, title, img, author, price, publisher, desc, pubdate, 0, isbn, category);
//					System.out.println(book);
//					bookRepo.save(book);

				}
			}


		} catch (Exception e) {
			System.out.printf("e");
		}

	}
}

