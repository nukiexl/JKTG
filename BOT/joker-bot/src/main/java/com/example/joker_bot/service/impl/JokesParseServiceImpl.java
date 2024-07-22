package com.example.joker_bot.service.impl;

import com.example.joker_bot.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.joker_bot.service.JokesParseService;

import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.StringReader;
import java.io.StringWriter;

@Service
public class JokesParseServiceImpl implements JokesParseService {

    private String url = "http://rzhunemogu.ru/Rand.aspx?CType=";
    private int category;

    @Override
    public void saveJoke(int category) throws ServiceException {
        try {
            this.url = "http://rzhunemogu.ru/Rand.aspx?CType=" + Integer.toString(category);
            this.category = category;
            System.out.println("saveJoke url" + this.url);
            RestClient restClient = RestClient.create();
            String jokeResponse = restClient
                    .get()
                    .uri("http://localhost:8081/jokes/fetch")
                    .retrieve()
                    .body(String.class);

        } catch (Exception e) {
            throw new ServiceException("Ошибка сохранения шутки", e);
        }
    }

    public String fetchJoke() throws SecurityException {
        try {
            RestClient restClient = RestClient.create();
            ResponseEntity<String> responseEntity = restClient
                    .get()
                    .uri(this.url)
                    .retrieve()
                    .toEntity(String.class);

            System.out.println("fetchJoke url" + this.url);
            if (responseEntity != null && responseEntity.getBody() != null) {
                String xmlResponse = responseEntity.getBody();

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new InputSource(new StringReader(xmlResponse)));
                document.getDocumentElement().normalize();

                Element rootElement = document.getDocumentElement();
                Element idElement = document.createElement("id");
                idElement.setTextContent(String.valueOf(this.category));
                rootElement.appendChild(idElement);

                String modifiedXml = convertDocumentToString(document);

                return modifiedXml;

//                return xmlResponse;

            } else {
                // Handle the case when the response body is null
                System.out.println("Failed to retrieve the joke, response body is null");
                return "Failed to retrieve the joke, response body is null";
            }
        } catch (Exception e) {
            throw new SecurityException("Ошибка получения шутки", e);
        }
    }

    private String convertDocumentToString(Document document) throws Exception {
        // Convert Document to String
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(writer));
        return writer.getBuffer().toString().replaceAll("\n|\r", "");
    }
}