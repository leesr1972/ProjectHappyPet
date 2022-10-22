package sky.pro.java.course6.projecthappypet.botModel.buttonsMenu;

public enum CynologistMenuEnum {
    ADVICE("Советы кинолога"),
    CYNOLOGIST("Рекомендации по проверенным кинологам");
    final String info;

    CynologistMenuEnum(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
