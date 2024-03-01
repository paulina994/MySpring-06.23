package com.example.xmlex.util;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XMLParser {

    <T> T FromFile(String filePath, Class<T> tClass) throws JAXBException, FileNotFoundException, JAXBException, FileNotFoundException;

    <T> void writeToFile(String filePath, T entity) throws JAXBException;
}