package com.matheus.xmlreader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.InputStream;

public class LerXML {
    public static void main(String[] args) {
        try {
            // Carregar o arquivo XML da pasta resources usando o ClassLoader
            ClassLoader classLoader = LerXML.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("com/matheus/resources/usuarios.xml");

            if (inputStream == null) {
                System.out.println("Arquivo XML não encontrado!");
                return;
            }

            // Criar o Document a partir do InputStream
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);

            // Normalizar a estrutura do XML
            doc.getDocumentElement().normalize();

            // Obter a lista de usuários
            NodeList listaUsuarios = doc.getElementsByTagName("usuario");

            // Percorrer a lista de usuários
            for (int i = 0; i < listaUsuarios.getLength(); i++) {
                Node node = listaUsuarios.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) node;

                    // Pegar os valores de id, nome e email
                    String id = elemento.getElementsByTagName("id").item(0).getTextContent();
                    String nome = elemento.getElementsByTagName("nome").item(0).getTextContent();
                    String email = elemento.getElementsByTagName("email").item(0).getTextContent();

                    // Exibir concatenado no console
                    System.out.println(id + " - " + nome + " - " + email);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
