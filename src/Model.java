import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class Model implements ProgrammFunction {
    private final String storagePath = "src\\Files\\Storage.txt";
    private final String prisesPath = "src\\Files\\Prises.txt";

    @Override
    public void playLottery() {
        Random random = new Random();
        double forIdUser = random.nextDouble() * 1563;

        int idUser = (int) forIdUser;

        String str;
        double totalDropRate = 0;
        try {
            File path2 = new File(prisesPath);
            BufferedWriter bw = new BufferedWriter(new FileWriter(path2, true));

            File path = new File(storagePath);
            BufferedReader br = new BufferedReader(new FileReader(path));
            ArrayList<String> priorityArray = new ArrayList<>();
            while ((str = br.readLine()) != null) {
                priorityArray.add(str);
            }


            for (int j = 0; j < priorityArray.size(); j++) {
                String[] secondArray = new String[4];
                secondArray = priorityArray.get(j).split(";");

                if (Integer.parseInt(secondArray[3]) == 0) {
                    priorityArray.remove(j);
                }
                totalDropRate += Double.parseDouble(secondArray[2]);
            }

            double randomValue = random.nextDouble() * totalDropRate;
            System.out.println(randomValue);
            double currentSum = 0;

            for (int k = 0; k < priorityArray.size(); k++) {
                String[] secondArray = new String[4];
                secondArray = priorityArray.get(k).split(";");
                currentSum += Double.parseDouble(secondArray[2]);

                if (Integer.parseInt(secondArray[3]) != 0) {
                    if (randomValue <= currentSum) {

                        priorityArray.set(k,
                                (secondArray[0] + ";"
                                        + secondArray[1] + ";"
                                        + (Integer.parseInt(secondArray[2]) - 1) + ";"
                                        + secondArray[3]));

                        JOptionPane.showMessageDialog(null,
                                "Вы выиграли: " + secondArray[1] + "\nваш код выигрыша: " + idUser,
                                "Окно выигрыша",
                                JOptionPane.INFORMATION_MESSAGE);

                        bw.write(secondArray[0] + ";" + secondArray[1] + ";" + idUser);

                        bw.newLine();

                        bw.close();

                        br.close();

                        break;


                    }
                }
            }

            BufferedWriter bwp = new BufferedWriter(new FileWriter(path));

            String replace = "";

            for (int p = 0; p < priorityArray.size(); p++) {
                replace += priorityArray.get(p) + "\n";
            }

            System.out.println(replace);
            bwp.write(replace);
            bwp.close();

        } catch (IOException e) {
            System.out.println(e);
        }


    }


    @Override
    public void changeWeightChance() {
        String str;
        try {
            File path = new File(storagePath);
            BufferedReader br = new BufferedReader(new FileReader(path));
            ArrayList<String> priorityArray = new ArrayList<>();
            while ((str = br.readLine()) != null) {
                priorityArray.add(str);
            }
            br.close();

            Object[] options = priorityArray.toArray();
            Object value = JOptionPane.showInputDialog(null,
                    "Выберете игрушку, вес которой необходимо изменить",
                    "Изменение веса игрушки",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            int j = priorityArray.indexOf(value);
            String[] secondArray = new String[4];
            secondArray = priorityArray.get(j).split(";");
            int weigthChance = Integer.parseInt(JOptionPane.showInputDialog(null,
                    priorityArray.get(j) + "\nВведите новую величину веса в %: ",
                    "Изменение веса игрушки",
                    JOptionPane.INFORMATION_MESSAGE));
            String res = secondArray[0] + ";" + secondArray[1] + ";" + secondArray[2] + ";" + weigthChance;
            priorityArray.set(j, res);

            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (int k = 0; k < priorityArray.size(); k++) {
                bw.write(priorityArray.get(k));
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void addToy() {

        Toy toy = new Toy(Integer.parseInt(JOptionPane.showInputDialog(null,
                "Введите id: ",
                "Добавление игрушки",
                JOptionPane.QUESTION_MESSAGE)),
                JOptionPane.showInputDialog(null,
                        "Введите наименование: ",
                        "Добавление игрушки",
                        JOptionPane.QUESTION_MESSAGE),
                Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Введите количество:",
                        "Добавление игрушек",
                        JOptionPane.QUESTION_MESSAGE)),
                Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Введите вес в %: ",
                        "Добавление игрушки",
                        JOptionPane.QUESTION_MESSAGE)));
        try {
            File path = new File(storagePath);

            if (!path.exists()) {
                path.createNewFile();
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));

            bw.write(toy.getAllInfo());

            bw.newLine();

            JOptionPane.showMessageDialog(null,
                    "Игрушка: \nid: "
                            + toy.getId() + "\nназвание: "
                            + toy.getToyName() + "\nколичество: "
                            + toy.getCountOfToy() + "\nвес: "
                            + toy.getWeightChance() + "\nуспешно добавлена",
                    "Сообщение",
                    JOptionPane.INFORMATION_MESSAGE);

            bw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e, "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void getToy() {
        int idUser = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Введите Id участника: ",
                "Выдача игрушки",
                JOptionPane.INFORMATION_MESSAGE));
        String str;
        try {
            File path = new File(prisesPath);
            BufferedReader br = new BufferedReader(new FileReader(path));
            ArrayList<String> priorityArray = new ArrayList<>();
            while ((str = br.readLine()) != null) {
                priorityArray.add(str);

            }
            br.close();

            for (int j = 0; j < priorityArray.size(); j++) {
                String[] secondArray = new String[3];
                secondArray = priorityArray.get(j).split(";");
                if (idUser == Integer.parseInt(secondArray[2])) {
                    JOptionPane.showMessageDialog(null,
                            "Поздравляем ваш выигрыш\n" + secondArray[0] + "," + secondArray[1],
                            "Выдача игрушки",
                            JOptionPane.INFORMATION_MESSAGE);

                    priorityArray.remove(j);
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (int k = 0; k < priorityArray.size(); k++) {
                bw.write(priorityArray.get(k));
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
