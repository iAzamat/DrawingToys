import javax.swing.JOptionPane;

public class Menu {
    public void run() {
        Model m = new Model();
        String[] options = {"Разыграть игрушку", "Добавить игрушку", "Изменить вес игрушки", "Получить игрушку", "Выход"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Выберите пункт меню",
                "Окно команд",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[4]);

        if (choice == 0) {
            m.playLottery();

        } else if (choice == 1) {
            m.addToy();

        } else if (choice == 2) {
            m.changeWeightChance();

        } else if (choice == 3) {
            m.getToy();

        } else if (choice == 4) {
            System.exit(0);

        } else {
            JOptionPane.showMessageDialog(null,
                    "Что-то пошло не так",
                    "Произошла ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
