abstract class Toys {

    int id;
    String toyName;
    int countOfToy;
    int weightChance;

    public Toys(int id, String toyName, int countOfToy, int weightChance) {
        this.id = id;
        this.toyName = toyName;
        this.countOfToy = countOfToy;
        this.weightChance = weightChance;
    }

    public String getToyName() {
        return toyName;
    }

    public int getCountOfToy() {
        return countOfToy;
    }

    public int getId() {
        return id;
    }

    public int getWeightChance() {
        return weightChance;
    }

    public String getAllInfo() {
        return id + ";" + toyName + ";" + countOfToy + ";" + weightChance;
    }

    public void setWeightChance(int weightChance) {
        this.weightChance = weightChance;
    }

    public void setCountOfToy(int countOfToy) {
        this.countOfToy = countOfToy;
    }
}
