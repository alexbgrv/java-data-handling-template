package com.epam.izh.rd.online.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        File count = new File("src/main/resources/" + path);
        int countOfFiles = 0;
        if (count.isDirectory()) {
            for (File file : count.listFiles()) {
                countOfFiles += countFilesInDirectory(path + "/" + file.getName());
            }
        } else {
            countOfFiles++;
        }
        return countOfFiles;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        File count = new File("src/main/resources/" + path);
        int countOfFiles = 0;
        if (count.isDirectory()) {
            for (File file : count.listFiles()) {
                countOfFiles += countDirsInDirectory(path + "/" + file.getName());
            }
            countOfFiles++;
        }
        return countOfFiles;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File regularFile = new File(from);
        File fileForCopy = new File(to);
        File copyFileDirectory = new File(fileForCopy.getParent());
        if (!copyFileDirectory.isDirectory()) {
            copyFileDirectory.mkdirs();
        }
        try {
            Files.copy(regularFile.toPath(), fileForCopy.toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {
        File fileForCreate = new File(getClass().getResource("/").getPath() + "/" + path);
        if (!fileForCreate.exists()) fileForCreate.mkdir();
        File file = new File(fileForCreate + "/" + name);
        try {
            return file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        StringBuilder FileText;
        try (BufferedReader readFile = new BufferedReader(new FileReader("src/main/resources/" + fileName))) {
            FileText = new StringBuilder(readFile.readLine());
        } catch (Exception e) {
            return e.getMessage();
        }
        return FileText.toString();
    }
}
