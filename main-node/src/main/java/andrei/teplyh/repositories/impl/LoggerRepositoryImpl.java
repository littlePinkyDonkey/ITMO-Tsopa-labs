package andrei.teplyh.repositories.impl;

import andrei.teplyh.entities.accounts.Account;
import andrei.teplyh.entities.accounts.Role;
import andrei.teplyh.repositories.LoggerRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
public class LoggerRepositoryImpl implements LoggerRepository {
    @Override
    public void log(String fileName, Account user) {
        try {
            Document document = getDocument(fileName);
            Element root = document.getDocumentElement();

            Element newUser = document.createElement("user");

            newUser.setAttribute("id", user.getAccountId() + "");

            Element login = document.createElement("login");
            login.appendChild(document.createTextNode(user.getLogin()));

            Element password = document.createElement("password");
            password.appendChild(document.createTextNode(user.getPassword()));

            Element roles = document.createElement("roles");
            Element role = document.createElement("role");
            List<Role> userRoles = user.getRoles();
            for (Role r : userRoles) {
                role.appendChild(document.createTextNode(r.getRole()));
                roles.appendChild(role);
            }

            newUser.appendChild(login);
            newUser.appendChild(password);
            newUser.appendChild(roles);

            root.appendChild(newUser);

            saveFile(document, fileName);
        } catch (TransformerException | IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        System.out.println(user.getLogin());
    }

    private Document getDocument(String fileName) throws IOException, SAXException, ParserConfigurationException {
        File usersFile = ResourceUtils.getFile(fileName);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse(usersFile);
    }

    private void saveFile(Document document, String fileName) throws TransformerException, FileNotFoundException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(ResourceUtils.getFile(fileName));

        transformer.transform(source, result);
    }
}
