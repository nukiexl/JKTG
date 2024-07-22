package com.example.joker_parser.service;

import com.example.joker_parser.mapper.JokeMapper;
import com.example.joker_parser.model.Joke;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.List;

@Service
public class JokeService {

    @Autowired
    private JokeMapper jokeMapper;

    public void fetchAndSaveJoke() {
        try {

            RestClient restClient = RestClient.create();

            ResponseEntity<String> responseEntity = restClient
                    .get()
                    .uri("http://localhost:8080/jokebot/fetchJoke")
                    .retrieve()
                    .toEntity(String.class);

            if (responseEntity != null && responseEntity.getBody() != null) {
                String xmlResponse = responseEntity.getBody();

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new InputSource(new StringReader(xmlResponse)));
                document.getDocumentElement().normalize();

                NodeList idList = document.getElementsByTagName("id");
                if (idList.getLength() > 0) {
                    Node idNode = idList.item(0);
                    int categoryId = Integer.parseInt(idNode.getTextContent().trim());

                    NodeList contentList = document.getElementsByTagName("content");
                    for (int i = 0; i < contentList.getLength(); i++){
                        Node node = contentList.item(i);

                        if (node.getNodeType() == Node.ELEMENT_NODE){
                            Element element = (Element) node;
                            String joke_text = element.getTextContent();

                            Joke joke = new Joke();
                            joke.setjoke_text(joke_text);
                            joke.setdate_created(LocalDate.now());
                            joke.setid_category(categoryId);

                            jokeMapper.insertJoke(joke);
                        }
                    }
                } else {
                    System.out.println("No id element");
                }
            } else {
                System.out.println("Ошибка получения шутки, response body is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Joke getRandomJoke() {
        return jokeMapper.getRandomJoke();
    }

    public Joke getRandomJokeByCategory(int category) {
        return jokeMapper.getRandomJokeByCategory(category);
    }

    public List<Joke> getAllJokes() {
        return jokeMapper.getAllJokes();
    }
}
